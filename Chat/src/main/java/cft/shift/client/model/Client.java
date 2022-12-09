package cft.shift.client.model;

import cft.shift.client.view.View;
import cft.shift.message.Message;
import cft.shift.message.MessageType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.ZonedDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Client {

    private View view;
    private Socket socket;
    private ExecutorService serverListener;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean isUserNameWasValidated;
    private boolean isConnectionAlive;
    private String host;
    private int port;
    private String userName;
    private final Object lock = new Object();



    private void setConnection(String host, int port) {
        socket = new Socket();
        this.host = host;
        this.port = port;

        try {
            socket.connect(new InetSocketAddress(host, port));
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            isConnectionAlive = true;
            if (isUserNameWasValidated) {
                registration(userName);
            } else {
                view.requestName();
            }
        } catch (IOException e) {
            log.error("Ошибка при установке соединения : " + e.getMessage());

            if (isConnectionCloseRequired()) {
                closeConnection();
            }
            view.connectionFailureMessage(isUserNameWasValidated);
        }
    }



    public void registration(String userName) {
        try {
            this.userName = userName;
            initServerListener();
            sendRequest(new Message(MessageType.REGISTRATION, userName));
        } catch (IOException e) {
            processConnectionProblems(e);
        }
    }

    private void sendRequest(Message message) throws IOException {
        outputStream.writeObject(message);
    }


    public void setChatView(View view) {
        this.view = view;
    }

    public void startChatView() {
        view.launch();
    }

    public void connect(String host, int port) {
        setConnection(host, port);
    }

    public void reconnect() {
        setConnection(host, port);
    }





    private void initServerListener() {
        serverListener = Executors.newSingleThreadExecutor();
        serverListener.submit(new ServerListener(inputStream, this, view));
    }

    void processSuccessAuthentication() {
        isUserNameWasValidated = true;
        view.validationMessage(userName);
    }


    public void notValidation() {
        if (isConnectionCloseRequired()) {
            closeConnection();
        }
    }

    public void sendChatMessage(String text) {
        try {
            sendRequest(new Message(MessageType.DEFAULT_MESSAGE, text, userName, ZonedDateTime.now()));
        } catch (IOException e) {
            processConnectionProblems(e);
        }
    }

    void processConnectionProblems(Exception e) {
        if (isConnectionCloseRequired()) {
            //log.error("Соединение с сервером потеряно : ", e);
            System.out.println("Соединение с сервером потеряно : "+ e);
            closeConnection();
            view.offerReconnection();
        }
    }



    private boolean isConnectionCloseRequired() {
        synchronized (lock) {
            if (isConnectionAlive) {
                isConnectionAlive = false;
                return true;
            }
            return false;
        }
    }

    private void closeConnection() {
        if (serverListener != null) {
            serverListener.shutdownNow();
        }
        try {
            socket.close();
        } catch (IOException e) {
            //log.error("Ошибка при закрытии сетевого соединения : ", e);
            System.out.println("Ошибка при закрытии сетевого соединения : " + e);
        }
    }

    private void sendDisconnectRequest() {
        try {
            isUserNameWasValidated = false;
            sendRequest(new Message(MessageType.DELETE_USER));
        } catch (IOException e) {
            //log.error("Ошибка при отправке запроса о завершении соединения на сервер : ", e);
            System.out.println("Ошибка при отправке запроса о завершении соединения на сервер : "+ e);
        }
    }

    public void clientCloseRequest() {
        disconnect();
        view.dispose();
    }

    public void disconnect() {
        if (isConnectionCloseRequired()) {
            sendDisconnectRequest();
            closeConnection();
        }
    }
}