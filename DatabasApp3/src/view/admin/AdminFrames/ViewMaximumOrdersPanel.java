package view.admin.AdminFrames;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewMaximumOrdersPanel extends JPanel
{
    private Controller controller;
    private ViewMaximumOrdersFrame viewMaximumOrdersFrame;
    private JList<String> listMaximumOrders = new JList<>();
    private JScrollPane scrollPane;

    private JButton btnExit;

    public ViewMaximumOrdersPanel(Controller controller, ViewMaximumOrdersFrame viewMaximumOrdersFrame) {
        this.controller = controller;
        this.viewMaximumOrdersFrame = viewMaximumOrdersFrame;

        initializeComponents();
        updateMaximumOrderList();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {

        // listMaximumOrders = new JList<>(Controller.getInstance().getMaximumOrders().toArray(new String[0]));

        listMaximumOrders.setListData(Controller.getInstance().getMaximumOrders().toArray(new String[0]));


        listMaximumOrders.setSize(new Dimension(800, 400));
        listMaximumOrders.setPreferredSize(new Dimension(800, 400));
        listMaximumOrders.setMinimumSize(new Dimension(800, 400));

        scrollPane = new JScrollPane(listMaximumOrders, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(new Dimension(800, 300));
        scrollPane.setPreferredSize(new Dimension(800, 300));
        scrollPane.setMinimumSize(new Dimension(800, 300));

        btnExit = new JButton("Exit");
        btnExit.setSize(new Dimension(100, 25));
        btnExit.setPreferredSize(new Dimension(100, 25));
        btnExit.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnExit.setOpaque(true);
        btnExit.setBorderPainted(false);

    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());

        setPreferredSize(new Dimension(800, 500));
        setMaximumSize(new Dimension(800, 500));
        setMinimumSize(new Dimension(800, 500));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(scrollPane, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(btnExit, gbc);
    }

    private void registerListeners() {
        btnExit.addActionListener(new ViewMaximumOrdersPanel.BtnExitListener());
    }

    public void updateMaximumOrderList()
    {

    }

    private class BtnExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewMaximumOrdersFrame.dispose();
        }
    }
}
