package cft.shift.model;


import cft.shift.controller.ViewController;
import cft.shift.message.Message;
import cft.shift.message.MessageType;
import lombok.extern.slf4j.Slf4j;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;

@Slf4j
public class ServerListener implements Runnable {
    private final ObjectInputStream inputStream;
    private final Client chatClient;
    private final MessageFormatter formatter;
    private final ViewController viewController;

    ServerListener(ObjectInputStream inputStream, Client chatClient, ViewController viewController) {
        this.inputStream = inputStream;
        this.chatClient = chatClient;
        this.viewController = viewController;
        this.formatter = new MessageFormatter();

    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Message response = receiveMessageFromServer();
                processServerResponse(response);
            }
        } catch (EOFException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            chatClient.processConnectionProblems(e);
            log.info("Ошибка " + e.getMessage());
        } catch (Throwable e) {
            log.info("error " + e.getMessage());

        }
    }

    private Message receiveMessageFromServer() throws IOException, ClassNotFoundException {
        Object obj = inputStream.readObject();
        Message message = Client.mapper.readValue((String) obj, Message.class);
        log.info(message.toString() + " : " + message.getUserName() + " ," + message.getData()
                + ", " + message.getType());
        return message;
    }

    private void processServerResponse(Message response) {
        MessageType type = response.getType();
        switch (type) {
            case NAME_ACCEPTED -> chatClient.processSuccessAuthentication();
            case NAME_DECLINED ->  viewController.onNameDeclined();
            case DEFAULT_MESSAGE -> viewController.onSend(formatter.formatChatMessage(response));
            case NEW_USER -> viewController.onEvent(response.getUserName());
            case DISCONNECT -> viewController.onDisconnected(response.getUserName());
            default -> log.error("Неверный тип ответа, полученный от сервера - {} . ", type);
        }
    }
}
