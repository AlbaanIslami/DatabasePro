package view.main;

import controller.Controller;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StorePanel extends JPanel {
    private MainPanel mainPanel;
    private JList<Product> listProducts;
    private JScrollPane scrollPane;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnUpdate;

    public StorePanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        txtSearch = new JTextField();
        txtSearch.setSize(new Dimension(400, 25));
        txtSearch.setPreferredSize(new Dimension(400, 25));
        txtSearch.setMinimumSize(new Dimension(400, 25));
        txtSearch.setToolTipText("Search for products by code, name or supplier...");

        listProducts = new JList<>();
        listProducts.setSize(new Dimension(500, 300));
        listProducts.setPreferredSize(new Dimension(500, 300));
        listProducts.setMinimumSize(new Dimension(500, 300));
        listProducts.setListData(mainPanel.getController().getProducts().toArray(new Product[0]));

        scrollPane = new JScrollPane(listProducts, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(new Dimension(520, 300));
        scrollPane.setPreferredSize(new Dimension(520, 300));
        scrollPane.setMinimumSize(new Dimension(520, 300));

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
        btnUpdate.addActionListener(l -> Controller.getInstance().fetchStoreData());

    }

    private void registerListeners() {
        btnUpdate.addActionListener(new BtnUpdateActionListener());
        btnSearch.addActionListener(new BtnSearchActionListener());
    }

    private class BtnSearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchedCode = txtSearch.getText();
            String searchedSupplier = txtSearch.getText();
            String searchedProduct = txtSearch.getText();

            if (isParsable(searchedCode)) {
                searchedProduct = null;
                searchedSupplier = null;
            } else {
                searchedCode = null;
            }


        }
    }

    public static boolean isParsable(String searchedCode) {
        try {
            Integer.parseInt(searchedCode);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 600));
        setMaximumSize(new Dimension(600, 600));
        setMinimumSize(new Dimension(600, 600));

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
        gbc.gridwidth = 2;
        add(btnUpdate, gbc);

    }




    private class BtnUpdateActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            listProducts.setListData(mainPanel.getController().getProducts().toArray(new Product[0]));

        }
    }
    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
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
}

