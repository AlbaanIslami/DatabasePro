package view.admin.AdminFrames;

import controller.Controller;
import model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandleOrdersPanel extends JPanel {
    private Controller controller;
    private HandleOrdersFrame handleOrdersFrame;

    private JLabel lblOrders;
    private JList<Order> listOrders;
    private JScrollPane scrollPane;

    private JButton btnConfirmOrder;
    private JButton btnCancelOrder;

    private JButton btnExit;


    public HandleOrdersPanel(Controller controller, HandleOrdersFrame handleOrdersFrame) {
        this.handleOrdersFrame = handleOrdersFrame;
        this.controller = controller;

        initializeComponents();
        initializeGUI();
        registerListener();

    }

    private void initializeComponents() {
        lblOrders = new JLabel("Orders:");

        listOrders = new JList(Controller.getInstance().getOrders().toArray());
        listOrders.setPreferredSize(new Dimension(500, 200));
        listOrders.setMinimumSize(new Dimension(500, 200));

        scrollPane = new JScrollPane(listOrders, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(new Dimension(300, 300));
        scrollPane.setPreferredSize(new Dimension(300, 300));
        scrollPane.setMinimumSize(new Dimension(300, 300));

        btnConfirmOrder = new JButton("Confirm order");
        btnConfirmOrder.setSize(new Dimension(125, 25));
        btnConfirmOrder.setPreferredSize(new Dimension(125, 25));
        btnConfirmOrder.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnConfirmOrder.setOpaque(true);
        btnConfirmOrder.setBorderPainted(false);
        btnConfirmOrder.addActionListener(p -> {
            if (listOrders.getSelectedValue() == null)
                return;

            Order o = listOrders.getSelectedValue();
            o.confirmOrder();

            this.handleOrdersFrame.dispose();
        });

        btnCancelOrder = new JButton("Cancel order");
        btnCancelOrder.setSize(new Dimension(125, 25));
        btnCancelOrder.setPreferredSize(new Dimension(125, 25));
        btnCancelOrder.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnCancelOrder.setOpaque(true);
        btnCancelOrder.setBorderPainted(false);
        btnCancelOrder.addActionListener(p -> {
            if (listOrders.getSelectedValue() == null)
                return;

            Order o = listOrders.getSelectedValue();


            int b = JOptionPane.showConfirmDialog(null, "Are you sure you want to Cancel that order?", "Are you sure?", JOptionPane.YES_NO_OPTION);
            if (b == 0) {
                o.cancelOrder();
                Controller.getInstance().getOrders().remove(o);
                JOptionPane.showMessageDialog(null, "The order has been cancelled!");
            } else {
                JOptionPane.showMessageDialog(null, "The order has not been cancelled!");

            }
            this.handleOrdersFrame.dispose();
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
        setPreferredSize(new Dimension(600, 450));
        setMaximumSize(new Dimension(600, 450));
        setMinimumSize(new Dimension(600, 450));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblOrders, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(listOrders, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(btnConfirmOrder, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnCancelOrder, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(btnExit, gbc);
    }

    private void registerListener() {
        btnExit.addActionListener(new BtnExitListener());
    }


    private class BtnExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            handleOrdersFrame.dispose();
        }
    }
}
