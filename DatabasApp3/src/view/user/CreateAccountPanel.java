package view.user;

import controller.Controller;
import controller.DBController;
import model.Countries;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class CreateAccountPanel extends JPanel {
    private Controller controller;
    private DBController databaseController;
    private JLabel lblUserName;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblEmail;
    private JLabel lblAddress;
    private JLabel lblCity;
    private JLabel lblCountry;
    private JLabel lblPhone;
    private JLabel lblPassword;

    private JTextField txtUserName;
    private JTextField userFirstName;
    private JTextField txtLastName;
    private JTextField txtEmail;
    private JTextField txtAddress;
    private JTextField txtCity;
    private JComboBox<Countries> cmbBoxCountry;
    private JTextField txtPhone;
    private JPasswordField txtPassword;


    private JButton btnRegister;
    private Pattern pattern;

    public CreateAccountPanel(Controller controller, DBController databaseController) {
        this.controller = controller;
        this.databaseController = databaseController;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        lblUserName = new JLabel("Username: ");
        lblUserName.setMinimumSize(new Dimension(80, 20));
        lblUserName.setPreferredSize(new Dimension(80, 20));

        txtUserName = new JTextField();
        txtUserName.setMinimumSize(new Dimension(80, 20));
        txtUserName.setPreferredSize(new Dimension(80, 20));

        lblFirstName = new JLabel("First name: ");
        lblFirstName.setMinimumSize(new Dimension(80, 20));
        lblFirstName.setPreferredSize(new Dimension(80, 20));

        lblLastName = new JLabel("Last name: ");
        lblLastName.setMinimumSize(new Dimension(80, 20));
        lblLastName.setPreferredSize(new Dimension(80, 20));

        lblEmail = new JLabel("Email: ");
        lblEmail.setMinimumSize(new Dimension(80, 20));
        lblEmail.setPreferredSize(new Dimension(80, 20));

        lblAddress = new JLabel("Address: ");
        lblAddress.setMinimumSize(new Dimension(80, 20));
        lblAddress.setPreferredSize(new Dimension(80, 20));

        lblCity = new JLabel("City: ");
        lblCity.setMinimumSize(new Dimension(80, 20));
        lblCity.setPreferredSize(new Dimension(80, 20));

        lblCountry = new JLabel("Country: ");
        lblCountry.setMinimumSize(new Dimension(80, 20));
        lblCountry.setPreferredSize(new Dimension(80, 20));

        lblPhone = new JLabel("Phone-Nr: ");
        lblPhone.setMinimumSize(new Dimension(80, 20));
        lblPhone.setPreferredSize(new Dimension(80, 20));


        userFirstName = new JTextField();
        userFirstName.setMinimumSize(new Dimension(80, 20));
        userFirstName.setPreferredSize(new Dimension(80, 20));

        txtLastName = new JTextField();
        txtLastName.setMinimumSize(new Dimension(80, 20));
        txtLastName.setPreferredSize(new Dimension(80, 20));

        txtEmail = new JTextField();
        txtEmail.setMinimumSize(new Dimension(80, 20));
        txtEmail.setPreferredSize(new Dimension(80, 20));

        txtAddress = new JTextField();
        txtAddress.setMinimumSize(new Dimension(80, 20));
        txtAddress.setPreferredSize(new Dimension(80, 20));

        txtCity = new JTextField();
        txtCity.setMinimumSize(new Dimension(80, 20));
        txtCity.setPreferredSize(new Dimension(80, 20));

        cmbBoxCountry = new JComboBox<Countries>(controller.getCountries());

        txtPhone = new JTextField();
        txtPhone.setMinimumSize(new Dimension(80, 20));
        txtPhone.setPreferredSize(new Dimension(80, 20));

        lblPassword = new JLabel("Password: ");
        lblPassword.setMinimumSize(new Dimension(80, 20));
        lblPassword.setPreferredSize(new Dimension(80, 20));

        txtPassword = new JPasswordField();
        txtPassword.setMinimumSize(new Dimension(80, 20));
        txtPassword.setPreferredSize(new Dimension(80, 20));


        btnRegister = new JButton("Register account");
        btnRegister.addActionListener(new RegisterListener());

    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(390, 500));
        setMaximumSize(new Dimension(390, 500));
        setMinimumSize(new Dimension(390, 500));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblFirstName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(userFirstName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblLastName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtLastName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblAddress, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtAddress, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblCity, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtCity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(lblCountry, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(cmbBoxCountry, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(lblPhone, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(txtPhone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(lblUserName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        add(txtUserName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(lblPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(btnRegister, gbc);

    }


    private class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String email = txtEmail.getText();
            String phone = txtPhone.getText();

            //Check if all the other fields is filled
            int countryIndex = cmbBoxCountry.getSelectedIndex();
            String userName = txtUserName.getText();
            String firstName = userFirstName.getText();
            String lastName = txtLastName.getText();
            String address = txtAddress.getText();
            String city = txtCity.getText();
            String country = String.valueOf(cmbBoxCountry.getItemAt(countryIndex));
            String password = new String(txtPassword.getPassword());
            if (!firstName.isEmpty() && !lastName.isEmpty() && !address.isEmpty() && !city.isEmpty() && !password.isEmpty() && !country.isEmpty() && !userName.isEmpty()) {
                if (!databaseController.nameIsTaken(userName)) {
                    User user = new User(
                            userName,
                            firstName,
                            lastName,
                            country,
                            city,
                            address,
                            password,
                            email,
                            phone);
                    controller.createNormalUser(user);
                }else{
                    JOptionPane.showMessageDialog(null, "Username already taken");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill the fields");
            }

        }


        private String getPassword(char[] password) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < password.length; i++) {
                sb.append(password[i]);
            }
            String pw = sb.toString();
            return pw;
        }
    }
}

