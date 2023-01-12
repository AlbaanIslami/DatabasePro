package view.admin.AdminFrames;

import controller.Controller;
import controller.DBController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class HandleProductFrame extends JFrame {
    private Controller controller;
    private DBController dbController;
    private HandleProductPanel handleProductPanel;

    public HandleProductFrame(Controller controller, DBController dbController)  {
        this.controller = controller;
        this.dbController = dbController;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents(){
        //Default JFrame initializations
        setTitle("Handle product");
        setSize(new Dimension(500, 500));
        setMinimumSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(500, 500));
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 2, 1, 0));

        handleProductPanel = new HandleProductPanel(controller, this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(handleProductPanel, gbc);

        pack();
        setLocation(new Point(300, 100));
    }
}
