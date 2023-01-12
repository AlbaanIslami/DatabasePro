package view.user;

import controller.Controller;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingCartPanel extends JPanel {
    private Controller controller;
    private ShoppingCartFrame shoppingCartFrame;

    private JList<Product> listOrderContent;
    private JScrollPane scrollPane;

    private JButton btnExit;
    private JButton btnPlaceOrder;
    private JLabel lblPrice;
    private JLabel lblOrder;


    public ShoppingCartPanel(Controller controller, ShoppingCartFrame shoppingCartFrame) {
        this.controller = controller;
        this.shoppingCartFrame = shoppingCartFrame;
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {

        listOrderContent = new JList(Controller.getInstance().getUser().getCart().getItems().toArray());

        listOrderContent.setSize(new Dimension(500, 300));
        listOrderContent.setPreferredSize(new Dimension(500, 300));
        listOrderContent.setMinimumSize(new Dimension(500, 300));

        scrollPane = new JScrollPane(listOrderContent, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(new Dimension(520, 200));
        scrollPane.setPreferredSize(new Dimension(520, 200));
        scrollPane.setMinimumSize(new Dimension(520, 200));

        lblPrice = new JLabel(String.format("Price: %s:-", calcPrice()));

        lblOrder = new JLabel("Your order");

        btnExit = new JButton("Exit");
        btnExit.setSize(new Dimension(100, 25));
        btnExit.setPreferredSize(new Dimension(100, 25));
        btnExit.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnExit.setOpaque(true);
        btnExit.setBorderPainted(false);


        btnPlaceOrder = new JButton("Place Order");
        btnPlaceOrder.setSize(new Dimension(100, 25));
        btnPlaceOrder.setPreferredSize(new Dimension(100, 25));
        btnPlaceOrder.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnPlaceOrder.setOpaque(true);
        btnPlaceOrder.setBorderPainted(false);
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
        add(lblOrder, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(btnPlaceOrder, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblPrice, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(lblOrder, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(btnExit, gbc);
    }

    private void registerListeners() {
        btnPlaceOrder.addActionListener(new ShoppingCartPanel.btnPlaceOrderListener());
        btnExit.addActionListener(new BtnExitListener());
    }

    private class BtnExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            shoppingCartFrame.dispose();
        }
    }


    public int calcPrice() {
        int val = 0;
        for (Product item : Controller.getInstance().getUser().getCart().getItems()) {
            val += item.getProductPrice() * item.getProductQuantity();
        }
        return val;
    }

    public class btnPlaceOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Controller.getInstance().placeOrder();
            shoppingCartFrame.dispose();
        }
    }
}
