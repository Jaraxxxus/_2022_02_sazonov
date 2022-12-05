package cft.shift;


import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
public class ClientHandler implements Runnable {

    private final Socket clientSocket;


    private final Server server;

    private String authorizedUserName;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private boolean isUserNameAuthorized;

    public ClientHandler(Socket clientSocket, Server server) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (clientSocket) {
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            authorizeUser();
            if (isUserNameAuthorized) {
                addUserToChat();
            } else {
                sendMessage(new Message(MessageType.NAME_DECLINED));
            }
        } catch (IOException | ClassNotFoundException e) {
            log.error("Ошибка при работе с клиентом " + clientSocket.getRemoteSocketAddress() +
                    ". Сокет с клиентом будет закрыт.", e);
        } finally {
            if (isUserNameAuthorized) {
                removeAuthorizedUser();
            }
        }
    }

    private void authorizeUser() throws IOException, ClassNotFoundException {
        Message request = getMessage();
        MessageType type = request.getType();
        log.info("Получен запрос из сокета клиента {} с типом {} .",
                clientSocket.getRemoteSocketAddress(), type);
        if (type == MessageType.AUTHORIZATION) {
            String userName = request.getUserName();
            log.info("Получен запрос на добавление пользователя с ником {}.",
                    userName);
            checkUserName(userName);
        } else {
            log.error("Неверный тип запроса, полученный с адреса {} - {} . Ожидаемый тип запроса - {} . " +
                            "Соединение будет закрыто.", clientSocket.getRemoteSocketAddress(), type,
                    MessageType.AUTHORIZATION);
        }
    }

    private void checkUserName(String userName) {
        if (server.isNameAvailable(userName)) {
            authorizedUserName = userName;
            isUserNameAuthorized = true;
            log.info("Сервер авторизовал пользователя с ником {}.", userName);
        } else {
            log.info("Сервер не авторизовал пользователя с ником {}. Такой пользователь уже есть в чате.",
                    userName);
        }
    }

    private void addUserToChat() throws IOException, ClassNotFoundException {
        sendMessage(new Message(MessageType.NAME_ACCEPTED));
        server.newUser(authorizedUserName, this);
        server.sendBroadcastMessage(new Message(MessageType.NEW_USER, null, authorizedUserName));
        sendListOfOnlineUsersToNewUser();
        startClientListener();
    }

    private void sendListOfOnlineUsersToNewUser() throws IOException {
        for (String userName : server.getAllUsers()) {
            if (!userName.equals(authorizedUserName)) {
                Message message = new Message(MessageType.NEW_USER,null, userName);
                sendMessage(message);
            }
        }
        log.info("Информация о пользователях онлайн отправлена пользователю {} .", authorizedUserName);
    }

    private void startClientListener() throws IOException, ClassNotFoundException {
        while (!Thread.currentThread().isInterrupted()) {
            Message request = getMessage();
            MessageType type = request.getType();
            if (type == MessageType.DEFAULT_MESSAGE) {
                server.sendBroadcastMessage(new Message(MessageType.DEFAULT_MESSAGE,
                        request.getData(), authorizedUserName, request.getDateTime()));
            } else if (type == MessageType.DISCONNECT) {
                log.info("Получен запрос на закрытие соединения: пользователь - {} .Обработчик будет остановлен",
                        authorizedUserName);
                break;
            } else {
                log.error("Неверный тип запроса, полученный с адреса {} - {} . Ожидаемые типы запроса - {}, {}.",
                        clientSocket.getRemoteSocketAddress(), type,
                        MessageType.DEFAULT_MESSAGE, MessageType.DISCONNECT);
            }
        }
    }

    private void removeAuthorizedUser() {
        server.deleteUser(authorizedUserName);
        log.info("Соединение с пользователем {} закрыто.", authorizedUserName);
        isUserNameAuthorized = false;
        server.sendBroadcastMessage(new Message(MessageType.DELETE_USER, authorizedUserName));
    }

    void sendMessage(Message message) throws IOException {
        outputStream.writeObject(message);
    }

    private Message getMessage() throws IOException, ClassNotFoundException {
        return (Message) inputStream.readObject();
    }

    String getAuthorizedUserName() {
        return authorizedUserName;
    }
}