package cft.shift.client.controller;

public interface Controller {

    void connect(String serverAddress, int serverPort);

    void reconnect();

    void validate(String newUserName);

    void notValidation();

    void sendMessage(String message);

    void disconnect();

    void clientCloseRequest();
}