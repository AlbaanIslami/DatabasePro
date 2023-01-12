package view.admin.AdminFrames;


import controller.Controller;
import controller.DBController;

import javax.swing.*;
import java.awt.*;

public class AddSupplierFrame extends JFrame {
    private Controller controller;
    private DBController databaseController;
    private AddSupplierPanel addSupplierPanel;

    public AddSupplierFrame(Controller controller, DBController databaseController) {
        this.controller = controller;
        this.databaseController = databaseController;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        setTitle("Add Supplier");
        setSize(new Dimension(500, 400));
        setMinimumSize(new Dimension(500, 400));
        setPreferredSize(new Dimension(500, 400));
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 2, 1, 0));

        addSupplierPanel = new AddSupplierPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(addSupplierPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }
}
