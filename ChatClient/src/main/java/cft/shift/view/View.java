package cft.shift.view;

public interface View {

    void launch();

    void dispose();

    void requestName();

    void nameDeclined();

    void validationMessage(String authorizedUserName);

    void sendMessage(String message);

    void newUserEvent(String userName);

    void disconnectEvent(String userName);

    void connectionFailureMessage(boolean isUserNameWasValidated);

    void offerReconnection();

    void setError(String errorText);

}

