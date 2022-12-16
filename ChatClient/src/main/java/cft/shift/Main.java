package cft.shift;


import cft.shift.controller.ChatController;
import cft.shift.controller.ModelControllerInterface;
import cft.shift.controller.ViewController;
import cft.shift.model.Client;
import cft.shift.view.UIChat;
import cft.shift.view.View;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        ModelControllerInterface controller = new ChatController(client);
        View chatView = new UIChat(controller);
        ViewController viewController = new ViewController(chatView);
        //client.setChatView(chatView);
        client.setChatView(viewController);
        SwingUtilities.invokeLater(client::startChatView);
    }

}