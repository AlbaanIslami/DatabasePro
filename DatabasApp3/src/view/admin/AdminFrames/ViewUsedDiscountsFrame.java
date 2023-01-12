package view.admin.AdminFrames;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class ViewUsedDiscountsFrame extends JFrame {
    private Controller controller;
    private ViewUsedDiscountsPanel viewUsedDiscountsPanel;

    public ViewUsedDiscountsFrame(Controller controller) {
        this.controller = controller;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        setTitle("Discount history");
        setSize(new Dimension(900, 500));
        setMinimumSize(new Dimension(900, 500));
        setPreferredSize(new Dimension(900, 500));
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 2, 1, 0));

        viewUsedDiscountsPanel = new ViewUsedDiscountsPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(viewUsedDiscountsPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }

    public void updateUsedDiscounts() {
        viewUsedDiscountsPanel.updateUsedDiscountList();
    }
}
