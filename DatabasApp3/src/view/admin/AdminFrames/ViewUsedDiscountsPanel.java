package view.admin.AdminFrames;


import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewUsedDiscountsPanel extends JPanel {
    private Controller controller;
    private ViewUsedDiscountsFrame viewUsedDiscountsFrame;
    private JList<String> listUsedDiscounts;
    private JScrollPane scrollPane;

    private JButton btnExit;

    public ViewUsedDiscountsPanel(Controller controller, ViewUsedDiscountsFrame viewUsedDiscountsFrame) {
        this.controller = controller;
        this.viewUsedDiscountsFrame = viewUsedDiscountsFrame;

        initializeComponents();
        updateUsedDiscountList();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents() {

        listUsedDiscounts = new JList<>(Controller.getInstance().getDiscountHistory().toArray(new String[0]));


        listUsedDiscounts.setSize(new Dimension(800, 400));
        listUsedDiscounts.setPreferredSize(new Dimension(800, 400));
        listUsedDiscounts.setMinimumSize(new Dimension(800, 400));

        scrollPane = new JScrollPane(listUsedDiscounts, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
        btnExit.addActionListener(new BtnExitListener());
    }

    public void updateUsedDiscountList()
    {

    }

    private class BtnExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewUsedDiscountsFrame.dispose();
        }
    }
}

