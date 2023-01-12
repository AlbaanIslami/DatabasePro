package view.user;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserOptionsPanel extends JPanel {
    private UserMainPanel userMainPanel;

    private JButton btnShoppingCart;

    private JButton btnOrderHistoryCart;


    public UserOptionsPanel(UserMainPanel userMainPanel) {
        this.userMainPanel = userMainPanel;

        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {
        btnShoppingCart = new JButton("Shopping Cart");
        btnShoppingCart.setSize(new Dimension(150, 25));
        btnShoppingCart.setPreferredSize(new Dimension(150, 25));
        btnShoppingCart.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnShoppingCart.setOpaque(true);
        btnShoppingCart.setBorderPainted(false);

        btnOrderHistoryCart = new JButton("Order History");
        btnOrderHistoryCart.setSize(new Dimension(150, 25));
        btnOrderHistoryCart.setPreferredSize(new Dimension(150, 25));
        btnOrderHistoryCart.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnOrderHistoryCart.setOpaque(true);
        btnOrderHistoryCart.setBorderPainted(false);
        btnOrderHistoryCart.addActionListener(p -> userMainPanel.openOrderHistory());
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 50));
        setMaximumSize(new Dimension(600, 50));
        setMinimumSize(new Dimension(600, 50));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(btnShoppingCart, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        add(btnOrderHistoryCart, gbc);

    }

    private void registerListeners() {
        btnShoppingCart.addActionListener(new BtnShoppingCart());
    }

    private class BtnShoppingCart implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            userMainPanel.openShoppingCart();
        }
    }

    public void updateShoppingCartBtn(int productsAdded) {
        btnShoppingCart.setText("Shopping Cart (" + productsAdded + ")");
    }

    public void getOrderedProducts(int productID, int nbrOfItems) {
    }
}
