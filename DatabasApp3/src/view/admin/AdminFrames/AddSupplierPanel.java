package view.admin.AdminFrames;


import controller.Controller;
import model.Countries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSupplierPanel extends JPanel {
    private Controller controller;
    private AddSupplierFrame addSupplierFrame;

    private JLabel lblSupplierName;
    private JLabel lblSupplierPhone;
    private JLabel lblSupplierAddress;
    private JLabel lblSupplierCity;
    private JLabel lblSupplierCountry;


    private JTextField txtSupplierName;
    private JTextField txtSupplierPhone;
    private JTextField txtSupplierAddress;
    private JTextField txtSupplierCity;
    private JComboBox<Countries> cmbBoxCountries;


    private JButton btnAddSupplier;
    private JButton btnExit;

    public AddSupplierPanel(Controller controller, AddSupplierFrame addSupplierFrame) {
        this.controller = controller;
        this.addSupplierFrame = addSupplierFrame;

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblSupplierName = new JLabel("name: ");
        lblSupplierName.setMinimumSize(new Dimension(120, 20));
        lblSupplierName.setPreferredSize(new Dimension(120, 20));

        txtSupplierName = new JTextField();
        txtSupplierName.setMinimumSize(new Dimension(120, 20));
        txtSupplierName.setPreferredSize(new Dimension(120, 20));
        txtSupplierName.setOpaque(true);
        txtSupplierName.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        lblSupplierPhone = new JLabel("Phone: ");
        lblSupplierPhone.setMinimumSize(new Dimension(120, 20));
        lblSupplierPhone.setPreferredSize(new Dimension(120, 20));

        txtSupplierPhone = new JTextField();
        txtSupplierPhone.setMinimumSize(new Dimension(120, 20));
        txtSupplierPhone.setPreferredSize(new Dimension(120, 20));

        txtSupplierPhone.setOpaque(true);
        txtSupplierPhone.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        lblSupplierAddress = new JLabel("Address: ");
        lblSupplierAddress.setMinimumSize(new Dimension(120, 20));
        lblSupplierAddress.setPreferredSize(new Dimension(120, 20));

        txtSupplierAddress = new JTextField();
        txtSupplierAddress.setMinimumSize(new Dimension(120, 20));
        txtSupplierAddress.setPreferredSize(new Dimension(120, 20));
        txtSupplierAddress.setOpaque(true);
        txtSupplierAddress.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        lblSupplierCity = new JLabel("City: ");
        lblSupplierCity.setMinimumSize(new Dimension(120, 20));
        lblSupplierCity.setPreferredSize(new Dimension(120, 20));

        txtSupplierCity = new JTextField();
        txtSupplierCity.setMinimumSize(new Dimension(120, 20));
        txtSupplierCity.setPreferredSize(new Dimension(120, 20));
        txtSupplierCity.setOpaque(true);
        txtSupplierCity.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        lblSupplierCountry = new JLabel("Country: ");
        lblSupplierCountry.setMinimumSize(new Dimension(120, 20));
        lblSupplierCountry.setPreferredSize(new Dimension(120, 20));

        cmbBoxCountries = new JComboBox<>(controller.getCountries());
        cmbBoxCountries.setMinimumSize(new Dimension(120, 20));
        cmbBoxCountries.setPreferredSize(new Dimension(120, 20));

        cmbBoxCountries.setOpaque(true);
        cmbBoxCountries.setFont(new Font("Helvetica", Font.BOLD, 12));

        btnAddSupplier = new JButton("Add supplier");
        btnAddSupplier.setSize(new Dimension(150, 25));
        btnAddSupplier.setPreferredSize(new Dimension(150, 25));
        btnAddSupplier.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnAddSupplier.setOpaque(true);
        btnAddSupplier.setBorderPainted(false);

        btnAddSupplier.addActionListener(l -> {
            String supplierName = txtSupplierName.getText();
            String supplierAddress = String.format("%s, %s, %S", txtSupplierAddress.getText(), txtSupplierCity.getText(), ((Countries) cmbBoxCountries.getSelectedItem()).name().toLowerCase());
            String supplierPhone = txtSupplierPhone.getText();

            if (!supplierName.isEmpty() && !supplierPhone.isEmpty() && !txtSupplierAddress.getText().isEmpty() && !txtSupplierCity.getText().isEmpty()) {
                boolean done = controller.newSupplier(
                        supplierName,
                        supplierAddress,
                        supplierPhone);

                if (!done) {
                    JOptionPane.showMessageDialog(null, "There is already a supplier with that name!");
                    return;
                }
                this.addSupplierFrame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Enter all details!");
            }
        });

        btnExit = new JButton("Exit");
        btnExit.setSize(new Dimension(200, 25));
        btnExit.setPreferredSize(new Dimension(100, 25));
        btnExit.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnExit.setOpaque(true);
        btnExit.setBorderPainted(false);

    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 400));
        setMaximumSize(new Dimension(500, 400));
        setMinimumSize(new Dimension(500, 400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblSupplierName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtSupplierName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblSupplierPhone, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtSupplierPhone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblSupplierAddress, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtSupplierAddress, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblSupplierCity, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txtSupplierCity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblSupplierCountry, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(cmbBoxCountries, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(btnAddSupplier, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(btnExit, gbc);
    }

    private void registerListeners() {
        btnExit.addActionListener(new BtnExitListener());
    }

    private class BtnExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addSupplierFrame.dispose();
        }
    }

}
