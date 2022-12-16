package cft.shift.view;

import cft.shift.controller.ModelControllerInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ConnectFailureFrame implements GUIFrame {

    private final JDialog connectionFailureDialog;
    private final JButton reconnectButton;
    private final JButton exitButton;
    private final JLabel connectionFailureLabel;

    private final UIChat UIChat;
    private final ModelControllerInterface controller;

    ConnectFailureFrame(UIChat UIChat, ModelControllerInterface controller) {
        this.UIChat = UIChat;
        this.controller = controller;
        connectionFailureDialog = new JDialog(UIChat.getMainFrame());
        connectionFailureLabel = new JLabel();
        reconnectButton = new JButton();
        exitButton = new JButton();
    }

    void connectionError() {
        launch();
        connectionFailureLabel.setText("Server connection error.");

        reconnectButton.addActionListener(e -> {
            connectionFailureDialog.dispose();
            JoinFrame joinFrame = UIChat.createServerConnectionDialog();
            joinFrame.launch();
            joinFrame.show();
        });
    }

    void initConnectionLost() {
        launch();
        connectionFailureLabel.setText("Connection is lost!");

        reconnectButton.addActionListener(e -> {
            connectionFailureDialog.dispose();
            controller.reconnect();
        });
    }

    @Override
    public void show() {
        connectionFailureDialog.setVisible(true);
    }


    @Override
    public void launch() {
        Container contentPane = connectionFailureDialog.getContentPane();

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(connectionFailureLabel, GroupLayout.DEFAULT_SIZE,
                                        379, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(reconnectButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE,
                                        129, GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(connectionFailureLabel, GroupLayout.PREFERRED_SIZE,
                                        53, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(reconnectButton, GroupLayout.PREFERRED_SIZE,
                                                84, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exitButton, GroupLayout.PREFERRED_SIZE,
                                                84, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(65, Short.MAX_VALUE))
        );

        reconnectButton.setText("Try again reconnect");

        exitButton.setText("Disconnect");
        exitButton.addActionListener(e -> {
            connectionFailureDialog.dispose();
            UIChat.disconnect();
        });

        connectionFailureLabel.setHorizontalAlignment(SwingConstants.CENTER);

        connectionFailureDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                controller.disconnect();
            }
        });

        UIChat.prepareDialogFrame(connectionFailureDialog);
    }


}