package view.admin.AdminFrames;

import controller.Controller;
import controller.DBController;

import javax.swing.*;
import java.awt.*;

public class DeleteProductFrame extends JFrame {
    private Controller controller;
    private DBController dbController;
    private DeleteProductPanel deleteProductPanel;

    public DeleteProductFrame(Controller controller, DBController dbController) {
        this.controller = controller;
        this.dbController = dbController;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Delete Product");
        setSize(new Dimension(500, 300));
        setMinimumSize(new Dimension(500, 300));
        setPreferredSize(new Dimension(500, 300));
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 2, 1, 0));

        deleteProductPanel = new DeleteProductPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(deleteProductPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }
}


