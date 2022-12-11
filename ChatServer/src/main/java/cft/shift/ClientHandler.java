package cft.shift;


import cft.shift.message.Message;
import cft.shift.message.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Slf4j
public class ClientHandler implements Runnable {

    private final Socket clientSocket;

    ObjectMapper mapper;
    private final Server server;

    private String userName;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private boolean isNameAccepted;

    public ClientHandler(Socket clientSocket, Server server) {
        this.server = server;
        this.clientSocket = clientSocket;
        mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }

    @Override
    public void run() {
        try (clientSocket) {

            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            registrationUser();
            if (isNameAccepted) {
                addUserToChat();
            } else {
                sendMessage(new Message(MessageType.NAME_DECLINED));
            }
        } catch (IOException | ClassNotFoundException e) {
            log.error("Ошибка при работе с клиентом " + clientSocket.getRemoteSocketAddress() +
                    ". Сокет с клиентом будет закрыт.", e);
        } finally {
            if (isNameAccepted) {
                deleteUser();
            }
        }
    }


    private void addUserToChat() throws IOException, ClassNotFoundException {
        sendMessage(new Message(MessageType.NAME_ACCEPTED));
        server.newUser(userName, this);
        server.sendBroadcastMessage(new Message(MessageType.NEW_USER, userName));
        sendCurUserList();
        startClientListener();
    }

    private void startClientListener() throws IOException, ClassNotFoundException {
        while (!Thread.currentThread().isInterrupted()) {
            Message request = getMessage();
            MessageType type = request.getType();
            if (type == MessageType.DEFAULT_MESSAGE) {
                server.sendBroadcastMessage(new Message(MessageType.DEFAULT_MESSAGE,
                        request.getData(), userName, request.getDateTime()));
            } else if (type == MessageType.DISCONNECT) {
                log.info("Получен запрос на закрытие соединения: пользователь - {} .Обработчик будет остановлен",
                        userName);
                break;
            } else {
                log.error("Неверный тип запроса, полученный с адреса {} - {} . Ожидаемые типы запроса - {}, {}.",
                        clientSocket.getRemoteSocketAddress(), type,
                        MessageType.DEFAULT_MESSAGE, MessageType.DISCONNECT);
            }
        }
    }

    private void deleteUser() {
        server.deleteUser(userName);
        log.info("Соединение с пользователем {} закрыто.", userName);
        isNameAccepted = false;
        server.sendBroadcastMessage(new Message(MessageType.DISCONNECT, userName));
    }

    private void registrationUser() throws IOException, ClassNotFoundException {
        Message request = getMessage();
        MessageType type = request.getType();
        log.info("Получен запрос из сокета клиента {} с типом {} .",
                clientSocket.getRemoteSocketAddress(), type);
        if (type == MessageType.REGISTRATION) {
            String userName = request.getUserName();
            log.info("Получен запрос на добавление пользователя с ником {}.",
                    userName);
            checkUserName(userName);
        } else {
            log.error("Неверный тип запроса, полученный с адреса {} - {} . Ожидаемый тип запроса - {} . " +
                            "Соединение будет закрыто.", clientSocket.getRemoteSocketAddress(), type,
                    MessageType.REGISTRATION);
        }
    }

    private void checkUserName(String userName) {
        if (server.checkName(userName)) {
            this.userName = userName;
            isNameAccepted = true;
            log.info("Сервер авторизовал пользователя с ником {}.", userName);
        } else {
            log.info("Сервер не авторизовал пользователя с ником {}. Такой пользователь уже есть в чате.",
                    userName);
        }
    }


    private void sendCurUserList() throws IOException {
        for (String name : server.getAllUsers()) {
            if (!name.equals(this.userName)) {
                Message message = new Message(MessageType.NEW_USER, null, name, null);
                sendMessage(message);
            }
        }
        log.info("Информация о пользователях онлайн отправлена пользователю {} .", userName);
    }


    private Message getMessage() throws IOException, ClassNotFoundException {
        Object obj = inputStream.readObject();
        Message message = mapper.readValue((String) obj, Message.class);
        log.info(message.toString());
        return message;
    }

    void sendMessage(Message message) throws IOException {
        outputStream.writeObject(mapper.writeValueAsString(message));
    }


    String getUserName() {
        return userName;
    }
}