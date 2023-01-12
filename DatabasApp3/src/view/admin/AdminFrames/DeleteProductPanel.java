package view.admin.AdminFrames;

import controller.Controller;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProductPanel extends JPanel {
    private Controller controller;
    private DeleteProductFrame deleteProductFrame;
    private JLabel lblProductToDelete;
    private JComboBox<Product> cmbBoxProducts;
    private JButton btnDeleteProduct;
    private JButton btnExit;


    public DeleteProductPanel(Controller controller, DeleteProductFrame deleteProductFrame) {
        this.controller = controller;
        this.deleteProductFrame = deleteProductFrame;
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        lblProductToDelete = new JLabel("Select product to delete");
        lblProductToDelete.setMinimumSize(new Dimension(200, 20));
        lblProductToDelete.setPreferredSize(new Dimension(200, 20));

        cmbBoxProducts = new JComboBox<>(controller.getProducts().toArray(new Product[0]));

        cmbBoxProducts.setMinimumSize(new Dimension(140, 20));
        cmbBoxProducts.setPreferredSize(new Dimension(140, 20));

        cmbBoxProducts.setOpaque(true);
        cmbBoxProducts.setFont(new Font("Helvetica", Font.BOLD, 12));

        btnDeleteProduct = new JButton("Delete product");
        btnDeleteProduct.setSize(new Dimension(150, 25));
        btnDeleteProduct.setPreferredSize(new Dimension(150, 25));
        btnDeleteProduct.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnDeleteProduct.setOpaque(true);
        btnDeleteProduct.setBorderPainted(false);
        btnDeleteProduct.addActionListener(l -> {
            boolean done = controller.deleteProduct((Product) cmbBoxProducts.getSelectedItem());
            if (done)
                JOptionPane.showMessageDialog(null, "You removed the product: " + cmbBoxProducts.getSelectedItem());
            else
                JOptionPane.showMessageDialog(null, "The product " + ((Product) cmbBoxProducts.getSelectedItem()).getProductName() + " could not be removed since it has been ordered before.");

            deleteProductFrame.dispose();
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
        setPreferredSize(new Dimension(500, 300));
        setMaximumSize(new Dimension(500, 300));
        setMinimumSize(new Dimension(500, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblProductToDelete, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(cmbBoxProducts, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnDeleteProduct, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnExit, gbc);
    }

    private void registerListeners() {
        btnExit.addActionListener(new BtnExitListener());
    }

    private class BtnExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteProductFrame.dispose();
        }
    }

}

