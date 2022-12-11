package cft.shift;


import cft.shift.view.View;
import cft.shift.controller.Controller;

import cft.shift.controller.ChatController;
import cft.shift.model.Client;
import cft.shift.view.UIChat;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        Controller controller = new ChatController(client);
        View chatView = new UIChat(controller);
        client.setChatView(chatView);
        SwingUtilities.invokeLater(client::startChatView);
    }
}