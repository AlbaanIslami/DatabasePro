package view.user;


import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class OrderHistoryFrame extends JFrame {
    private Controller controller;
    private OrderHistoryPanel orderHistoryPanel;

    public OrderHistoryFrame(Controller controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Order history");
        setSize(new Dimension(700, 500));
        setMinimumSize(new Dimension(700, 500));
        setPreferredSize(new Dimension(700, 500));
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 2, 1, 0));

        orderHistoryPanel = new OrderHistoryPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(orderHistoryPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }
}
