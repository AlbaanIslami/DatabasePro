package view.user;


import controller.*;

import javax.swing.*;
import java.awt.*;

public class ShoppingCartFrame extends JFrame {
    private Controller controller;
    private ShoppingCartPanel shoppingCartPanel;

    public ShoppingCartFrame(Controller controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Cart");
        setSize(new Dimension(500,500));
        setMinimumSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(500,500));
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        shoppingCartPanel = new ShoppingCartPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(shoppingCartPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }
}
