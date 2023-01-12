package view.admin;

import view.main.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AdminMainPanel extends JPanel {
    private final MainPanel mainPanel;
    private OptionsPanel optionsPanel;
    private AdminStorePanel adminStorePanel;

    public AdminMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;

        initializeComponents();
        initializeGUI();
    }

    private void initializeComponents() {
        this.optionsPanel = new OptionsPanel(this);
        this.adminStorePanel = new AdminStorePanel(this);
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
        add(adminStorePanel, gbc);
    }

    public void openAddSupplierFrame() {
        this.mainPanel.openAddSupplierFrame();
    }
    public void openAddProductFrame() {
        this.mainPanel.openAddProductFrame();
    }
    public void openAddDiscountFrame() {
        this.mainPanel.openAddDiscountFrame();
    }
    public void openDeleteProductFrame() {
        this.mainPanel.openDeleteProductFrame();
    }
    public void openHandleProductFrame(){
        mainPanel.openHandleProductFrame();
    }
    public void openHandleOrdersFrame() {
        mainPanel.openHandleOrdersFrame();
    }
    public void openViewUsedDiscountsFrame() {
        mainPanel.openViewUsedDiscountsFrame();
    }
    public void openViewMaximumOrderFrame(){
        mainPanel.openViewMaximumOrderList();
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public OptionsPanel getOptionsPanel() {
        return optionsPanel;
    }

    public void setOptionsPanel(OptionsPanel optionsPanel) {
        this.optionsPanel = optionsPanel;
    }

    public AdminStorePanel getAdminStorePanel() {
        return adminStorePanel;
    }

    public void setAdminStorePanel(AdminStorePanel adminStorePanel) {
        this.adminStorePanel = adminStorePanel;
    }
}