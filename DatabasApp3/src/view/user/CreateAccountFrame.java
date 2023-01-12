package view.user;


import controller.*;


import javax.swing.*;
import java.awt.*;

public class CreateAccountFrame extends JFrame {
    private Controller controller;
    private DBController databaseController;
    private CreateAccountPanel accountPanel;

    public CreateAccountFrame(Controller controller, DBController databaseController) {
        this.controller = controller;
        this.databaseController = databaseController;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        //Default JFrame initializations
        setTitle("Create account");
        setSize(new Dimension(500,550));
        setMinimumSize(new Dimension(500, 550));
        setPreferredSize(new Dimension(500,550));
        setVisible(true);
        setResizable(false);
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1,2, 1, 0));

        accountPanel = new CreateAccountPanel(controller, databaseController);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(accountPanel,gbc);

        pack();
        setLocation(new Point(300, 100));
    }
}

