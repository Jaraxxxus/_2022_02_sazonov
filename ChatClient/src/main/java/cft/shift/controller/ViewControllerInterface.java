package cft.shift.controller;

public interface ViewControllerInterface {
    void onDisconnected(String userName);

    void onSend(String message);

    void onNameDeclined();

    void onEvent(String userName);

    void onRequestName();

    void onFailureConnect(boolean isUserNameWasValidated);

    void onStartChat();

    void onValidate(String userName);

    void onSetError(String errorText);

    void onOfferReconnect();

    void onDispose();
}
