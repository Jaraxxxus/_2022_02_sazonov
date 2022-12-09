package cft.shift.client.view;

import cft.shift.client.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UIChat implements View {

    private final Controller controller;
    private final JFrame mainFrame;
    private final JLabel nameLabel;
    private final JLabel onlineLabel;
    private final JScrollPane onlineScrollPane;
    private final JList<String> onlineList;
    private final DefaultListModel<String> usersOnlineListModel;
    private final JScrollPane scrollPane;
    private final JTextArea textArea;
    private final JScrollPane sendScrollPane;
    private final JTextArea sendTextArea;
    private final JButton sendButton;
    private final JButton joinButton;
    private final JButton disconnectButton;
    private final JLabel userNameLabel;


    private GroupLayout groupLayout;

    public UIChat(Controller controller) {
        this.controller = controller;

        mainFrame = new JFrame();
        mainFrame.setBackground(Color.black);
        nameLabel = new JLabel();
        onlineLabel = new JLabel();
        onlineScrollPane = new JScrollPane();
        usersOnlineListModel = new DefaultListModel<>();
        onlineList = new JList<>(usersOnlineListModel);
        scrollPane = new JScrollPane();
        textArea = new JTextArea();
        sendScrollPane = new JScrollPane();
        sendTextArea = new JTextArea();
        sendButton = new JButton();
        joinButton = new JButton();
        userNameLabel = new JLabel();
        disconnectButton = new JButton();
    }

    @Override
    public void launch() {

        Container container = mainFrame.getContentPane();
        groupLayout = new GroupLayout(container);
        container.setLayout(groupLayout);

        initGroups();



        nameLabel.setText("Name: ");
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);


        onlineLabel.setText("Online users");
        onlineLabel.setHorizontalAlignment(SwingConstants.CENTER);


        onlineScrollPane.setViewportView(onlineList);


        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);


        sendScrollPane.setViewportView(sendTextArea);

        joinButton.setText("Join");
        joinButton.addActionListener(e -> {
            JoinFrame joinFrame = createServerConnectionDialog();
            joinFrame.launch();
            joinFrame.show();
        });

        disconnectButton.setText("Disconnect");
        disconnectButton.addActionListener(e -> disconnect());
        disconnectButton.setEnabled(false);

        sendButton.setText("Send");
        sendButton.addActionListener(e -> {
            String messageText = sendTextArea.getText();
            if (!messageText.trim().isEmpty()) {
                controller.sendMessage(sendTextArea.getText());
                sendTextArea.setText(null);
            }
        });

        sendTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    sendButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                controller.clientCloseRequest();
            }
        });

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(mainFrame.getOwner());
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        JoinFrame joinFrame = createServerConnectionDialog();
        joinFrame.launch();
        joinFrame.show();

    }

    void prepareDialogFrame(JDialog dialog) {
        dialog.pack();
        dialog.setResizable(false);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(dialog.getOwner());
    }

    JFrame getMainFrame() {
        return mainFrame;
    }

    JoinFrame createServerConnectionDialog() {
        return new JoinFrame(this, controller);
    }

    @Override
    public void requestName() {
        UserValidationFrame userValidationFrame = new UserValidationFrame(this, controller);
        userValidationFrame.launch();
        userValidationFrame.show();
    }

    @Override
    public void nameDeclined() {
        ErrorWindow errorWindow = new ErrorWindow(this, "это имя уже занято.");
        errorWindow.show();
        requestName();
    }

    @Override
    public void validationMessage(String authorizedUserName) {
        userNameLabel.setText(authorizedUserName);
        joinButton.setEnabled(false);
        disconnectButton.setEnabled(true);
    }

    @Override
    public void sendMessage(String message) {
        textArea.append(message + System.lineSeparator());
        textArea.setCaretPosition(textArea.getDocument().getLength());

    }

    @Override
    public void newUserEvent(String userName) {
        usersOnlineListModel.addElement(userName);
        sendMessage("User " + userName + " joined the chat.");
    }

    @Override
    public void disconnectEvent(String userName) {
        usersOnlineListModel.removeElement(userName);
        sendMessage("User " + userName + " left the chat.");
    }

    @Override
    public void dispose() {
        mainFrame.dispose();
        System.exit(0);
    }

    @Override
    public void connectionFailureMessage(boolean isUserNameWasAuthorized) {
        if (isUserNameWasAuthorized) {
            offerReconnection();
        } else {
            ConnectFailureFrame connectFailureFrame = new ConnectFailureFrame(this, controller);
            connectFailureFrame.connectionError();
            connectFailureFrame.show();
        }
    }

    @Override
    public void offerReconnection() {
        cleanGUI();
        ConnectFailureFrame connectFailureFrame = new ConnectFailureFrame(this, controller);
        connectFailureFrame.initConnectionLostVersion();
        connectFailureFrame.show();
    }

    void disconnect() {
        cleanGUI();
        controller.disconnect();
    }

    private void cleanGUI() {
        userNameLabel.setText(null);
        textArea.setText(null);
        sendTextArea.setText(null);
        usersOnlineListModel.removeAllElements();
        joinButton.setEnabled(true);
        disconnectButton.setEnabled(false);
    }

    private void initGroups() {

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
                .addGroup(groupLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(onlineScrollPane, GroupLayout.PREFERRED_SIZE,
                                137, GroupLayout.PREFERRED_SIZE)
                        .addComponent(onlineLabel, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addGroup(groupLayout.createParallelGroup()
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup()
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(sendScrollPane,
                                                        GroupLayout.PREFERRED_SIZE, 278,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(24, 24, 24)
                                                .addComponent(sendButton,
                                                        GroupLayout.PREFERRED_SIZE, 167,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollPane,
                                                GroupLayout.PREFERRED_SIZE, 469,
                                                GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE,
                                        95, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userNameLabel, GroupLayout.PREFERRED_SIZE,
                                        85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(joinButton)
                                .addGap(30, 30, 30)
                                .addComponent(disconnectButton)
                                .addGap(18, 18, 18)

                                ))
                ) );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameLabel,
                                                GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userNameLabel,
                                                GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(disconnectButton)
                                        .addComponent(joinButton, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(onlineLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(groupLayout.createParallelGroup()
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(scrollPane,
                                                        GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addGroup(groupLayout.createParallelGroup()
                                                        .addComponent(sendButton,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                                Short.MAX_VALUE)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(sendScrollPane,
                                                                        GroupLayout.PREFERRED_SIZE, 81,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addComponent(onlineScrollPane, GroupLayout.DEFAULT_SIZE,
                                                494, Short.MAX_VALUE))
                                .addGap(27, 27, 27))
        );
    }

}
