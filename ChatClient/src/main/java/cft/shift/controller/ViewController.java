package cft.shift.controller;

import cft.shift.view.View;

public class ViewController implements ViewControllerInterface {
    private final View view;
    public ViewController(View view) {
        this.view = view;
    }

    @Override
    public void onDisconnected(String userName) {
        view.disconnectEvent(userName);
    }

    @Override
    public void onSend(String message) {
        view.sendMessage(message);
    }

    @Override
    public void onNameDeclined() {
        view.nameDeclined();
    }

    @Override
    public void onEvent(String userName) {
        view.newUserEvent(userName);
    }

    @Override
    public void onRequestName() {
        view.requestName();
    }

    @Override
    public void onFailureConnect(boolean isUserNameWasValidated) {
        view.connectionFailureMessage(isUserNameWasValidated);
    }

    @Override
    public void onStartChat() {
        view.launch();
    }

    @Override
    public void onValidate(String userName) {
        view.validationMessage(userName);
    }

    @Override
    public void onSetError(String errorText) {
        view.setError(errorText);
    }

    @Override
    public void onOfferReconnect() {
        view.offerReconnection();
    }

    @Override
    public void onDispose() {
        view.dispose();
    }
}
