package cft.shift.client.controller;

//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;

import cft.shift.client.model.Client;


//@Slf4j
//@AllArgsConstructor
public class ChatController implements Controller {

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
    public void notValidation() {
        client.notValidation();
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