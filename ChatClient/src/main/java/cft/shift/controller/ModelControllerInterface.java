package cft.shift.controller;

public interface ModelControllerInterface {

    void connect(String serverAddress, int serverPort);

    void reconnect();

    void validate(String newUserName);

    void cancelValidation();

    void sendMessage(String message);

    void disconnect();

    void clientCloseRequest();
}