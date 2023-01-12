package view.main;

import controller.Controller;
import controller.DBController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private Controller controller;
    private DBController dbController;
    private MainPanel mainPanel;

    public MainFrame(Controller controller) {
        this.controller = controller;
        this.dbController = DBController.getInstance();
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {

        setTitle("Online Store");
        setSize(new Dimension(600, 600));
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 600));
        setPreferredSize(new Dimension(600, 600));
        setVisible(true);
        setResizable(false);
        setLayout(new GridBagLayout());

        mainPanel = new MainPanel(controller);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(mainPanel, gbc);

        pack();
        setLocation(new Point(500, 100));
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public DBController getDbController() {
        return dbController;
    }

    public void setDbController(DBController dbController) {
        this.dbController = dbController;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
