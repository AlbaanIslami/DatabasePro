package view.admin.AdminFrames;

import controller.Controller;
import model.Discount;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandleProductPanel extends JPanel {
    private Controller controller;
    private HandleProductFrame handleProductFrame;

    private JLabel lblProductToEdit;
    private JLabel lblNewQuantity;
    private JLabel lblUnusedDiscounts;

    private JTextField txtNewQuantity;
    private JComboBox<Product> cmbBoxProducts;
    private JComboBox<Discount> cmbBoxUnusedDiscounts;

    private JButton btnEditQuantity;
    private JButton btnAddUnusedDiscount;
    private JButton btnExit;


    public HandleProductPanel(Controller controller, HandleProductFrame handleProductFrame) {
        this.controller = controller;
        this.handleProductFrame = handleProductFrame;

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblProductToEdit = new JLabel("Select a product to edit");
        lblProductToEdit.setMinimumSize(new Dimension(140, 20));
        lblProductToEdit.setPreferredSize(new Dimension(140, 20));

        cmbBoxProducts = new JComboBox<>(Controller.getInstance().getProducts().toArray(new Product[0]));

        cmbBoxProducts.setOpaque(true);
        cmbBoxProducts.setFont(new Font("Helvetica", Font.BOLD, 12));

        lblNewQuantity = new JLabel("New Quantity: ");
        lblNewQuantity.setMinimumSize(new Dimension(120, 20));
        lblNewQuantity.setPreferredSize(new Dimension(120, 20));

        lblUnusedDiscounts = new JLabel("Discounts:");
        lblUnusedDiscounts.setMinimumSize(new Dimension(120, 20));
        lblUnusedDiscounts.setPreferredSize(new Dimension(120, 20));

        cmbBoxUnusedDiscounts = new JComboBox<>(Controller.getInstance().getDiscounts().toArray(new Discount[0]));
        cmbBoxUnusedDiscounts.setForeground(Color.LIGHT_GRAY);
        cmbBoxUnusedDiscounts.setOpaque(true);
        cmbBoxUnusedDiscounts.setFont(new Font("Helvetica", Font.BOLD, 12));


        txtNewQuantity = new JTextField();
        txtNewQuantity.setSize(new Dimension(50, 20));
        txtNewQuantity.setPreferredSize(new Dimension(50, 20));
        txtNewQuantity.setOpaque(true);
        txtNewQuantity.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        btnEditQuantity = new JButton("Set quantity");
        btnEditQuantity.addActionListener(p -> {
            if (cmbBoxProducts.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "You need to select an item first!");
                return;
            }
            if (txtNewQuantity.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please specify the new Quantity");
                return;
            }
            Product product = (Product) cmbBoxProducts.getSelectedItem();

            Controller.getInstance().updateQuantityForProduct(product, Integer.parseInt(txtNewQuantity.getText()));

        });
        btnEditQuantity.setSize(new Dimension(100, 25));
        btnEditQuantity.setPreferredSize(new Dimension(100, 25));
        btnEditQuantity.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnEditQuantity.setOpaque(true);
        btnEditQuantity.setBorderPainted(false);

        btnAddUnusedDiscount = new JButton("Set changes");
        btnAddUnusedDiscount.setSize(new Dimension(200, 25));
        btnAddUnusedDiscount.setPreferredSize(new Dimension(200, 25));
        btnAddUnusedDiscount.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnAddUnusedDiscount.setOpaque(true);
        btnAddUnusedDiscount.setBorderPainted(false);
        btnAddUnusedDiscount.addActionListener(p -> {
            if (cmbBoxProducts.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "You need to select an item first!");
                return;
            }
            if (cmbBoxUnusedDiscounts.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Please specify the discount");
                return;
            }
            Product product = (Product) cmbBoxProducts.getSelectedItem();
            Discount discount = (Discount) cmbBoxUnusedDiscounts.getSelectedItem();
            discount.addDiscount(product);
            Controller.getInstance().updateProductList();

        });

        btnExit = new JButton("Exit");
        btnExit.setSize(new Dimension(100, 25));
        btnExit.setPreferredSize(new Dimension(100, 25));
        btnExit.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnExit.setOpaque(true);
        btnExit.setBorderPainted(false);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(500, 500));
        setMaximumSize(new Dimension(500, 500));
        setMinimumSize(new Dimension(500, 500));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblProductToEdit, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(cmbBoxProducts, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(lblNewQuantity, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtNewQuantity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnEditQuantity, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(lblUnusedDiscounts, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(cmbBoxUnusedDiscounts, gbc);


        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(btnAddUnusedDiscount, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(btnExit, gbc);
    }

    private void registerListeners() {
        btnExit.addActionListener(new BtnExitListener());
    }

    private class BtnExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleProductFrame.dispose();
        }
    }
}

