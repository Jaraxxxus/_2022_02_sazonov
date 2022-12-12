package cft.shift.view;

//import org.apache.commons.lang3.StringUtils;

import cft.shift.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class JoinFrame implements GUIFrame {

    private final JDialog connectionDialog;
    private final JPanel dialogPanel;
    private final JPanel contentPanel;
    private final JLabel requestLabel;
    private final JLabel addressLabel;
    private final JLabel portLabel;
    private final JTextField addressTextField;
    private final JTextField portTextField;
    private final JButton okButton;
    private final JButton cancelButton;
    private final UIChat UIChat;
    private final Controller controller;

    JoinFrame(UIChat UIChat, Controller controller) {
        this.UIChat = UIChat;
        this.controller = controller;
        connectionDialog = new JDialog(UIChat.getMainFrame());
        dialogPanel = new JPanel();
        contentPanel = new JPanel();
        requestLabel = new JLabel();
        addressLabel = new JLabel();
        addressTextField = new JTextField();
        portLabel = new JLabel();
        portTextField = new JTextField();
        okButton = new JButton();
        cancelButton = new JButton();
    }

    @Override
    public void show() {
        connectionDialog.setVisible(true);
    }


    @Override
    public void launch() {
        Container contentPane = connectionDialog.getContentPane();
        contentPane.setLayout(new BorderLayout());

        GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
                contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(contentPanelLayout.createParallelGroup()
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                .addComponent(okButton, GroupLayout.PREFERRED_SIZE,
                                                        123, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE,
                                                        124, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 54, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING,
                                                contentPanelLayout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(requestLabel,
                                                                GroupLayout.PREFERRED_SIZE, 286,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING,
                                                contentPanelLayout.createSequentialGroup()
                                                        .addGroup(contentPanelLayout.createParallelGroup()
                                                                .addComponent(addressLabel)
                                                                .addComponent(portLabel))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(contentPanelLayout.createParallelGroup()
                                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                                        .addComponent(addressTextField,
                                                                                GroupLayout.PREFERRED_SIZE, 211,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(32, 54, Short.MAX_VALUE))
                                                                .addGroup(contentPanelLayout.createSequentialGroup()
                                                                        .addComponent(portTextField,
                                                                                GroupLayout.PREFERRED_SIZE, 77,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(0, 0, Short.MAX_VALUE)))))
                                .addContainerGap())
        );

        contentPanelLayout.setVerticalGroup(
                contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(requestLabel, GroupLayout.PREFERRED_SIZE,
                                        51, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(addressTextField, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addressLabel))
                                .addGap(27, 27, 27)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(portTextField, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(portLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(cancelButton))
                                .addGap(16, 16, 16))
        );

        dialogPanel.setBorder(new EmptyBorder(12, 12, 12, 12));
        dialogPanel.setLayout(new BorderLayout());

        requestLabel.setText("Input the server address and port:");
        requestLabel.setHorizontalAlignment(SwingConstants.CENTER);

        addressLabel.setText("Address:");
        portLabel.setText("Port:");

        dialogPanel.add(contentPanel, BorderLayout.SOUTH);
        contentPane.add(dialogPanel, BorderLayout.CENTER);

        okButton.setText("OK");
        okButton.addActionListener(createActionListenerForOkButton());

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(e -> connectionDialog.dispose());

        connectionDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        UIChat.prepareDialogFrame(connectionDialog);
    }

    private ActionListener createActionListenerForOkButton() {
        return e -> {
            String serverAddress = addressTextField.getText();
            if (serverAddress.trim().isEmpty()) {
                showErrorFrame("Enter the server address");
                return;
            }
            try {
                int serverPort = Integer.parseInt(portTextField.getText());
                connectionDialog.dispose();
                controller.connect(serverAddress, serverPort);
            } catch (NumberFormatException ex) {
                showErrorFrame("Incorrect port");
            }
        };
    }

    private void showErrorFrame(String message) {
        ErrorFrame errorWindow = new ErrorFrame(UIChat, message);
        errorWindow.show();
    }

}