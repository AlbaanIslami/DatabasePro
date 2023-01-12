package view.user;

import controller.Controller;
import model.Product;

import javax.swing.*;
import java.awt.*;

public class UserStorePanel extends JPanel {
    private UserMainPanel userMainPanel;
    private JList listProducts;
    private JScrollPane scrollPane;

    int productsAdded;

    private JTextField txtSearch;

    private JButton btnSearch;
    private JButton btnUpdate;
    private JButton btnAddProduct;


    public UserStorePanel(UserMainPanel userMainPanel) {
        this.userMainPanel = userMainPanel;
        productsAdded = 0;

        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        txtSearch = new JTextField();
        txtSearch.setForeground(Color.BLACK);
        txtSearch.setSize(new Dimension(400, 25));
        txtSearch.setPreferredSize(new Dimension(400, 25));
        txtSearch.setMinimumSize(new Dimension(400, 25));
        txtSearch.setToolTipText("Search for products by code, name or supplier...");

        listProducts = new JList<>(Controller.getInstance().getProducts().toArray());
        listProducts.setSize(new Dimension(900, 300));
        listProducts.setPreferredSize(new Dimension(900, 300));
        listProducts.setMinimumSize(new Dimension(900, 300));

        scrollPane = new JScrollPane(listProducts, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(new Dimension(500, 300));
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setMinimumSize(new Dimension(500, 300));

        btnSearch = new JButton("Search");
        btnSearch.setSize(new Dimension(100, 25));
        btnSearch.setPreferredSize(new Dimension(100, 25));
        btnSearch.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnSearch.setOpaque(true);
        btnSearch.setBorderPainted(false);
        btnSearch.addActionListener(l -> {
            Controller.getInstance().productSearch(txtSearch.getText());
        });

        btnUpdate = new JButton("Update List");
        btnUpdate.setSize(new Dimension(200, 25));
        btnUpdate.setPreferredSize(new Dimension(100, 25));
        btnUpdate.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnUpdate.setOpaque(true);
        btnUpdate.setBorderPainted(false);
        btnUpdate.addActionListener(p -> Controller.getInstance().updateProductList());

        btnAddProduct = new JButton("Add product");
        btnAddProduct.setSize(new Dimension(200, 25));
        btnAddProduct.setPreferredSize(new Dimension(100, 25));
        btnAddProduct.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnAddProduct.setOpaque(true);
        btnAddProduct.setBorderPainted(false);
        btnAddProduct.addActionListener(p -> {
            if (listProducts.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(null, "You need to select a product first!");
                return;
            }

            Product product = (Product) listProducts.getSelectedValue();
            int amount = Integer.parseInt(JOptionPane.showInputDialog("How many of this products do you want to add"));
            if (amount > product.getProductQuantity()) {
                JOptionPane.showMessageDialog(null, "There is not enough of that product in stock!");
                return;
            }
            Product userProduct = product.newUserProduct(amount);

            Controller.getInstance().getUser().getCart().addProductToOrder(userProduct);
        });
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 500));
        setMaximumSize(new Dimension(600, 500));
        setMinimumSize(new Dimension(600, 500));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0, 0, 0, 0);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(txtSearch, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        add(btnSearch, gbc);

        gbc.insets = new Insets(10, 0, 10, 0);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(btnUpdate, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        add(btnAddProduct, gbc);
    }


    public UserMainPanel getUserMainPanel() {
        return userMainPanel;
    }

    public void setUserMainPanel(UserMainPanel userMainPanel) {
        this.userMainPanel = userMainPanel;
    }

    public JList getListProducts() {
        return listProducts;
    }

    public void setListProducts(JList listProducts) {
        this.listProducts = listProducts;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public int getProductsAdded() {
        return productsAdded;
    }

    public void setProductsAdded(int productsAdded) {
        this.productsAdded = productsAdded;
    }

    public JTextField getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(JTextField txtSearch) {
        this.txtSearch = txtSearch;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(JButton btnSearch) {
        this.btnSearch = btnSearch;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public void setBtnUpdate(JButton btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    public JButton getBtnAddProduct() {
        return btnAddProduct;
    }

    public void setBtnAddProduct(JButton btnAddProduct) {
        this.btnAddProduct = btnAddProduct;
    }


    public static boolean isParsable(String searchedCode) {
        try {
            Integer.parseInt(searchedCode);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
