package cft.shift.model;

import cft.shift.controller.ViewController;
import cft.shift.message.Message;
import cft.shift.message.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

    public static ObjectMapper mapper;
    ViewController viewController;
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


    public Client() {
        mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }


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
                viewController.onRequestName();
            }
        } catch (IOException e) {
            log.error("Ошибка при установке соединения : " + e.getMessage());

            if (isConnectionCloseRequired()) {
                closeConnection();
            }
            viewController.onFailureConnect(isUserNameWasValidated);
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
        outputStream.writeObject(mapper.writeValueAsString(message));
    }


    public void setChatView(ViewController viewController) {
        this.viewController = viewController;
        //this.view = view;
    }

    public void startChatView() {
        viewController.onStartChat();
    }

    public void connect(String host, int port) {
        setConnection(host, port);
    }

    public void reconnect() {
        setConnection(host, port);
    }


    private void initServerListener() {
        serverListener = Executors.newSingleThreadExecutor();
        serverListener.submit(new ServerListener(inputStream, this, viewController));

    }

    void processSuccessAuthentication() {
        isUserNameWasValidated = true;
        viewController.onValidate(userName);
    }


    public void cancelValidation() {
        if (isConnectionCloseRequired()) {
            closeConnection();
        }
    }

    public void sendChatMessage(String text) {
        if (!isConnectionAlive) {
            log.error("Попытка отправить сообщение без соединения с сервером");
            viewController.onSetError("Connect to the server first!");
            return;
        }
        try {
            sendRequest(new Message(MessageType.DEFAULT_MESSAGE, text, userName, ZonedDateTime.now()));
        } catch (IOException e) {
            processConnectionProblems(e);
        }
    }

    void processConnectionProblems(Throwable e) {
        if (isConnectionCloseRequired()) {
            log.error("Соединение с сервером потеряно : ", e);
            closeConnection();
            viewController.onOfferReconnect();
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
            log.error("Ошибка при закрытии сетевого соединения : ", e);
        }
    }

    private void sendDisconnectRequest() {
        try {
            isUserNameWasValidated = false;
            sendRequest(new Message(MessageType.DISCONNECT));
        } catch (IOException e) {
            log.error("Ошибка при отправке запроса о завершении соединения на сервер : ", e);
        }
    }

    public void clientCloseRequest() {
        disconnect();
        viewController.onDispose();
    }

    public void disconnect() {
        if (isConnectionCloseRequired()) {
            sendDisconnectRequest();
            closeConnection();
        }
    }
}