package view.user;


import view.main.MainPanel;

import javax.swing.*;
import java.awt.*;

public class UserMainPanel extends JPanel {
    private MainPanel mainPanel;
    private UserOptionsPanel optionsPanel;
    private UserStorePanel userStorePanel;

    public UserMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        this.optionsPanel = new UserOptionsPanel(this);
        this.userStorePanel = new UserStorePanel(this);
    }

    private void initializeGUI() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 600));
        setMaximumSize(new Dimension(600, 600));
        setMinimumSize(new Dimension(600, 600));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(optionsPanel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(userStorePanel, gbc);
    }


    public void openShoppingCart() {
        mainPanel.openShoppingcart();
    }
    public void openOrderHistory() {
        mainPanel.openOrderHistory();
    }

    public boolean checkQuantity(int nbrOfItems, int productID) {
        return mainPanel.checkQuantity(nbrOfItems, productID);
    }

    public void updateShoppingCartBtn(int productsAdded) {
        optionsPanel.updateShoppingCartBtn(productsAdded);
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public UserOptionsPanel getOptionsPanel() {
        return optionsPanel;
    }

    public void setOptionsPanel(UserOptionsPanel optionsPanel) {
        this.optionsPanel = optionsPanel;
    }

    public UserStorePanel getUserStorePanel() {
        return userStorePanel;
    }

    public void setUserStorePanel(UserStorePanel userStorePanel) {
        this.userStorePanel = userStorePanel;
    }
}
