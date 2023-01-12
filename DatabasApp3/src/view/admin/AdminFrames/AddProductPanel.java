package view.admin.AdminFrames;

import controller.Controller;
import model.Supplier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductPanel extends JPanel {
    private Controller controller;
    private AddProductFrame addProductFrame;

    private JLabel lblProductName;
    private JLabel lblProductCode;
    private JLabel lblProductQuantity;
    private JLabel lblProductBasePrice;
    private JLabel lblProductSupplier;
    private JLabel lblDiscounts;

    private JTextField txtProductName;
    private JTextField txtProductCode;
    private JTextField txtProductQuantity;
    private JTextField txtProductBasePrice;
    private JComboBox<Supplier> cmbBoxSupplier;

    private JButton btnAddProduct;
    private JButton btnExit;

    public AddProductPanel(Controller controller, AddProductFrame addProductFrame) {
        this.controller = controller;
        this.addProductFrame = addProductFrame;

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblProductName = new JLabel("Product name: ");
        lblProductName.setMinimumSize(new Dimension(120, 20));
        lblProductName.setPreferredSize(new Dimension(120, 20));

        txtProductName = new JTextField();
        txtProductName.setMinimumSize(new Dimension(120, 20));
        txtProductName.setPreferredSize(new Dimension(120, 20));

        txtProductName.setOpaque(true);
        txtProductName.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        lblProductCode = new JLabel("Product Code: ");
        lblProductCode.setMinimumSize(new Dimension(120, 20));
        lblProductCode.setPreferredSize(new Dimension(120, 20));

        txtProductCode = new JTextField();
        txtProductCode.setMinimumSize(new Dimension(120, 20));
        txtProductCode.setPreferredSize(new Dimension(120, 20));


        txtProductCode.setOpaque(true);
        txtProductCode.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        lblProductQuantity = new JLabel("Product quantity: ");
        lblProductQuantity.setMinimumSize(new Dimension(120, 20));
        lblProductQuantity.setPreferredSize(new Dimension(120, 20));


        txtProductQuantity = new JTextField();
        txtProductQuantity.setMinimumSize(new Dimension(120, 20));
        txtProductQuantity.setPreferredSize(new Dimension(120, 20));

        txtProductQuantity.setOpaque(true);
        txtProductQuantity.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        lblProductBasePrice = new JLabel("Product base price: ");
        lblProductBasePrice.setMinimumSize(new Dimension(120, 20));
        lblProductBasePrice.setPreferredSize(new Dimension(120, 20));

        txtProductBasePrice = new JTextField();
        txtProductBasePrice.setMinimumSize(new Dimension(120, 20));
        txtProductBasePrice.setPreferredSize(new Dimension(120, 20));

        txtProductBasePrice.setOpaque(true);
        txtProductBasePrice.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        lblProductSupplier = new JLabel("Product supplier: ");
        lblProductSupplier.setMinimumSize(new Dimension(120, 20));
        lblProductSupplier.setPreferredSize(new Dimension(120, 20));

        cmbBoxSupplier = new JComboBox<>(controller.getSuppliers().toArray(new Supplier[0]));

        cmbBoxSupplier.setMinimumSize(new Dimension(120, 20));
        cmbBoxSupplier.setPreferredSize(new Dimension(120, 20));

        cmbBoxSupplier.setOpaque(true);
        cmbBoxSupplier.setFont(new Font("Helvetica", Font.BOLD, 12));

        lblDiscounts = new JLabel("Discount type: ");
        lblDiscounts.setMinimumSize(new Dimension(120, 20));
        lblDiscounts.setPreferredSize(new Dimension(120, 20));

        btnAddProduct = new JButton("Add product");
        btnAddProduct.setSize(new Dimension(200, 25));
        btnAddProduct.setPreferredSize(new Dimension(100, 25));
        btnAddProduct.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnAddProduct.setOpaque(true);
        btnAddProduct.setBorderPainted(false);

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
        add(lblProductName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtProductName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblProductQuantity, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtProductQuantity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblProductBasePrice, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtProductBasePrice, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblProductSupplier, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(cmbBoxSupplier, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(lblProductCode, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(txtProductCode, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(btnAddProduct, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(btnExit, gbc);
    }

    private void registerListeners() {
        btnAddProduct.addActionListener(new BtnAddProductListener());
        btnExit.addActionListener(new BtnExitListener());
    }

    private class BtnExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            addProductFrame.dispose();
        }
    }

    private class BtnAddProductListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String productName = txtProductName.getText();
            int productQuantity = Integer.parseInt(txtProductQuantity.getText());
            int productPrice = Integer.parseInt(txtProductBasePrice.getText());
            int indexSupplier = cmbBoxSupplier.getSelectedIndex();
            String productSupplier = String.valueOf(cmbBoxSupplier.getItemAt(indexSupplier));
            String productCode = txtProductCode.getText();

            if (!productName.isEmpty() && !productCode.isEmpty() && productQuantity > -1 && productPrice > -1) {
                boolean done = controller.newProduct(productName, productQuantity, productPrice, productSupplier, productCode);
                if (!done) {
                    JOptionPane.showMessageDialog(null, "There is already a product with that code!");
                    return;
                }

                controller.updateProductList();
                addProductFrame.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Enter all details!");
            }
        }
    }
}

