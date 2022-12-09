package cft.shift.client;

//import lombok.extern.slf4j.Slf4j;
import cft.shift.client.view.View;
import cft.shift.client.controller.Controller;

import cft.shift.client.controller.ChatController;
import cft.shift.client.model.Client;
import cft.shift.client.view.UIChat;

import javax.swing.*;

//@Slf4j
public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        Controller controller = new ChatController(client);
        View chatView = new UIChat(controller);
        client.setChatView(chatView);
        SwingUtilities.invokeLater(client::startChatView);
    }
}