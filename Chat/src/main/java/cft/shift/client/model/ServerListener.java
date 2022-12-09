package cft.shift.client.model;


import cft.shift.client.view.View;
import cft.shift.message.Message;
import cft.shift.message.MessageType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;

@Slf4j
public class ServerListener implements Runnable {

    private final ObjectInputStream inputStream;
    private final Client chatClient;
    private final View view;
    private final MessageFormatter formatter;

    ServerListener(ObjectInputStream inputStream, Client chatClient, View view) {
        this.inputStream = inputStream;
        this.chatClient = chatClient;
        this.view = view;
        this.formatter = new MessageFormatter();
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Message response = receiveMessageFromServer();
                processServerResponse(response);
            }
        } catch (IOException | ClassNotFoundException e) {
            chatClient.processConnectionProblems(e);
        }
    }

    private Message receiveMessageFromServer() throws IOException, ClassNotFoundException {
        return (Message) inputStream.readObject();
    }

    private void processServerResponse(Message response) {
        MessageType type = response.getType();
        switch (type) {
            case NAME_ACCEPTED -> chatClient.processSuccessAuthentication();
            case REGISTRATION -> view.nameDeclined();
            case DEFAULT_MESSAGE -> view.sendMessage(formatter.formatChatMessage(response));
            case NEW_USER -> view.newUserEvent(response.getUserName());
            case DISCONNECT -> view.disconnectEvent(response.getUserName());
            default -> log.error("Неверный тип ответа, полученный от сервера - {} . ",type);
        }
    }
}
