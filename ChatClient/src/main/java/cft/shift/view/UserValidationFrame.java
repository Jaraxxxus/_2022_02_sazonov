package cft.shift.view;

import cft.shift.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class UserValidationFrame implements GUIFrame {

    private final UIChat UIChat;
    private final Controller controller;
    private final JDialog userAuthorizationDialog;
    private final JPanel dialogPane;
    private final JPanel contentPanel;
    private final JPanel buttonBar;
    private final JLabel userNameRequestLabel;
    private final JTextField userNameTextField;

    private final JButton okButton;
    private final JButton cancelButton;

    UserValidationFrame(UIChat UIChat, Controller controller) {
        this.UIChat = UIChat;
        this.controller = controller;
        userAuthorizationDialog = new JDialog(UIChat.getMainFrame());
        dialogPane = new JPanel();

        contentPanel = new JPanel();

        userNameRequestLabel = new JLabel();

        userNameTextField = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

    }


    public void launch() {
        Container contentPane = userAuthorizationDialog.getContentPane();
        contentPane.setLayout(new BorderLayout());

        GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
                contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING,
                                                false)
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(userNameTextField))
                                        .addComponent(userNameRequestLabel, GroupLayout.PREFERRED_SIZE, 236,
                                                GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(82, Short.MAX_VALUE))
        );
        contentPanelLayout.setVerticalGroup(
                contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(userNameRequestLabel, GroupLayout.PREFERRED_SIZE, 40,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(76, Short.MAX_VALUE))
        );

        dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        dialogPane.setLayout(new BorderLayout());
        dialogPane.add(contentPanel, BorderLayout.CENTER);

        userNameRequestLabel.setText("Enter your name: ");
        userNameRequestLabel.setHorizontalAlignment(SwingConstants.CENTER);

        buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
        buttonBar.setLayout(new GridBagLayout());
        ((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[]{0, 85, 80};
        ((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[]{1.0, 0.0, 0.0};

        okButton.setText("OK");
        buttonBar.add(okButton, new GridBagConstraints(1, 0, 1,
                1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 5), 0, 0));

        cancelButton.setText("Cancel");
        buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1,
                1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

        okButton.addActionListener(createActionListenerForOkButton());

        cancelButton.addActionListener(e -> {

            userAuthorizationDialog.dispose();

            controller.cancelValidation();
        });

        userAuthorizationDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                controller.disconnect();
            }
        });

        dialogPane.add(buttonBar, BorderLayout.SOUTH);

        contentPane.add(dialogPane, BorderLayout.CENTER);

        UIChat.prepareDialogFrame(userAuthorizationDialog);
    }

    @Override
    public void show() {
        userAuthorizationDialog.setVisible(true);
    }

    private ActionListener createActionListenerForOkButton() {
        return e -> {
            String userName = userNameTextField.getText();
            if (userName.isEmpty()) {
                ErrorFrame errorWindow = new ErrorFrame(UIChat, "Name is empty");
                errorWindow.show();
                return;
            }
            userAuthorizationDialog.dispose();
            controller.validate(userName);
        };
    }
}