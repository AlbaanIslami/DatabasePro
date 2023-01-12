package view.admin.AdminFrames;

import controller.Controller;
import controller.DBController;

import javax.swing.*;
import java.awt.*;

public class AddDiscountFrame extends JFrame {
    private Controller controller;
    private DBController dbController;
    private AddDiscountPanel addDiscountPanel;

    public AddDiscountFrame(Controller controller, DBController dbController) {
        this.controller = controller;
        this.dbController = dbController;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        setTitle("Add discount");
        setSize(new Dimension(500, 400));
        setMinimumSize(new Dimension(500, 400));
        setPreferredSize(new Dimension(500, 400));
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 2, 1, 0));

        addDiscountPanel = new AddDiscountPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addDiscountPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }
}
