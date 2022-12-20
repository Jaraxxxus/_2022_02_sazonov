package cft.shift.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ErrorFrame implements GUIFrame {

    private final JDialog errorDialog;
    private final JPanel dialogPane;
    private final JPanel contentPanel;
    private final JButton okButton;
    private final JLabel errorLabel;
    private final UIChat UIChat;

    ErrorFrame(UIChat UIChat, String errorText) {
        this.UIChat = UIChat;
        errorDialog = new JDialog(UIChat.getMainFrame());
        contentPanel = new JPanel();
        dialogPane = new JPanel();
        errorLabel = new JLabel();
        okButton = new JButton();
        launch();
        errorLabel.setText(errorText);
    }

    @Override
    public void show() {
        errorDialog.setVisible(true);
    }

    @Override
    public void launch() {
        Container contentPane = errorDialog.getContentPane();
        contentPane.setLayout(new BorderLayout());

        dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        dialogPane.setLayout(new BorderLayout());

        GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
                contentPanelLayout.createParallelGroup().addGroup(contentPanelLayout.createSequentialGroup()
                                .addGap(54, 54, 54).addComponent(errorLabel, GroupLayout.PREFERRED_SIZE,
                                        282, GroupLayout.PREFERRED_SIZE).addContainerGap(38, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                .addContainerGap(125, Short.MAX_VALUE).addComponent(okButton, GroupLayout.PREFERRED_SIZE,
                                        126, GroupLayout.PREFERRED_SIZE).addGap(123, 123, 123))
        );
        contentPanelLayout.setVerticalGroup(
                contentPanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                .addContainerGap(73, Short.MAX_VALUE)
                                .addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 60,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(okButton, GroupLayout.PREFERRED_SIZE, 54,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45))
        );

        dialogPane.add(contentPanel, BorderLayout.CENTER);
        contentPane.add(dialogPane, BorderLayout.CENTER);

        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        okButton.setText("OK");
        okButton.addActionListener(e -> errorDialog.dispose());

        errorDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        UIChat.prepareDialogFrame(errorDialog);
    }


}