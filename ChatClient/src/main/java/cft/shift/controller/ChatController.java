package cft.shift.controller;

import cft.shift.model.Client;


public class ChatController implements ModelControllerInterface {

    private final Client client;

    public ChatController(Client client) {
        this.client = client;
    }

    @Override
    public void connect(String serverAddress, int serverPort) {
        client.connect(serverAddress, serverPort);
    }

    @Override
    public void reconnect() {
        client.reconnect();
    }

    @Override
    public void validate(String newUserName) {
        client.registration(newUserName);
    }

    @Override
    public void cancelValidation() {
        client.cancelValidation();
    }

    @Override
    public void sendMessage(String message) {
        client.sendChatMessage(message);
    }

    @Override
    public void disconnect() {
        client.disconnect();
    }

    @Override
    public void clientCloseRequest() {
        client.clientCloseRequest();
    }
}