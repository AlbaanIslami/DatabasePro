package view.main;


import controller.Controller;
import controller.DBController;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LogInPanel extends JPanel {
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JButton btnLogIn;
    private MainPanel mainPanel;
    private JLabel lblNoAccount;
    private Controller controller;


    public LogInPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.controller = mainPanel.getController();
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblUsername = new JLabel("Username: ");
        txtUsername = new JTextField();
        txtUsername.setMinimumSize(new Dimension(80, 20));
        txtUsername.setPreferredSize(new Dimension(80, 20));

        lblPassword = new JLabel("Password: ");
        txtPassword = new JPasswordField();
        txtPassword.setMinimumSize(new Dimension(80, 20));
        txtPassword.setPreferredSize(new Dimension(80, 20));

        btnLogIn = new JButton("Log in");
        btnLogIn.setMinimumSize(new Dimension(60, 20));
        btnLogIn.setPreferredSize(new Dimension(60, 20));
        btnLogIn.setFont(new Font("Helvetica", Font.PLAIN, 9));
        btnLogIn.setOpaque(true);
        btnLogIn.setBorderPainted(false);
        btnLogIn.addActionListener((al) -> {
            String userName = txtUsername.getText();
            String charPass = new String(txtPassword.getPassword());

            if (userName.equalsIgnoreCase(User.aName) && charPass.equalsIgnoreCase(User.aPass)) {
                User u = new User();
                controller.loginUser(u);
                return;
            }
            boolean userExists = DBController.getInstance().nameIsTaken(userName);

            if (!userExists) {
                JOptionPane.showMessageDialog(null, "Username / Password was wrong");
                return;
            }

            User u = DBController.getInstance().authenticateUser(userName, charPass);
            if (u == null) {
                JOptionPane.showMessageDialog(null, "Username / Password was wrong");
                return;
            }
            controller.loginUser(u);

        });

        lblNoAccount = new JLabel("Create account!");
        lblNoAccount.setMinimumSize(new Dimension(100, 30));
        lblNoAccount.setPreferredSize(new Dimension(100, 30));

    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 100));
        setMaximumSize(new Dimension(600, 100));
        setMinimumSize(new Dimension(600, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblUsername, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtUsername, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(lblPassword, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        add(txtPassword, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        add(btnLogIn, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        add(lblNoAccount, gbc);
    }

    private void registerListeners() {
        lblNoAccount.addMouseListener(new LabelListener());
    }

    public String getUsernameLogin() {
        return txtUsername.getText();
    }

    public String getPasswordLogin() {
        char[] charPass = txtPassword.getPassword();
        return String.valueOf(charPass);
    }


    private class LabelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            mainPanel.openCreateAccountWindow();
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            lblNoAccount.setText("Create account!");
            lblNoAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }

}

