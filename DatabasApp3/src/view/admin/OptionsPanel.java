package view.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsPanel extends JPanel {
    private AdminMainPanel adminMainPanel;

    private String[] options;
    private JComboBox<String> cmbBoxOptions;
    private JButton btnGo;

    public OptionsPanel(AdminMainPanel adminMainPanel){
        this.adminMainPanel = adminMainPanel;
        initializeComponents();
        initializeGUI();
        registerListeners();
    }

    private void initializeComponents(){
        options = new String[]{"Add supplier", "Add discount", "Add product", "Delete product", "Handle product",
                "Handle orders", "Discount history", "Trending products"};

        cmbBoxOptions = new JComboBox<>();
        for(int i = 0; i < options.length; i++){
            cmbBoxOptions.addItem(options[i]);
        }
        cmbBoxOptions.setOpaque(true);
        cmbBoxOptions.setFont(new Font("Helvetica", Font.BOLD, 12));

        btnGo = new JButton("Go");
        btnGo.setSize(new Dimension(150, 25));
        btnGo.setPreferredSize(new Dimension(150, 25));
        btnGo.setFont(new Font("Helvetica", Font.PLAIN, 12));
        btnGo.setOpaque(true);
        btnGo.setBorderPainted(false);
    }

    private void initializeGUI(){
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600,75));
        setMaximumSize(new Dimension(600,75));
        setMinimumSize(new Dimension(600,75));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(btnGo, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        add(cmbBoxOptions, gbc);
    }

    private void registerListeners() {
        btnGo.addActionListener(new BtnGoListener());
    }

    private class BtnGoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(cmbBoxOptions.getSelectedIndex() == 0){
                adminMainPanel.openAddSupplierFrame();
            }
            if(cmbBoxOptions.getSelectedIndex() == 1){
                adminMainPanel.openAddDiscountFrame();
            }
            if(cmbBoxOptions.getSelectedIndex() == 2){
                adminMainPanel.openAddProductFrame();
            }
            if(cmbBoxOptions.getSelectedIndex() == 3){
                adminMainPanel.openDeleteProductFrame();
            }
            if(cmbBoxOptions.getSelectedIndex() == 4){
                adminMainPanel.openHandleProductFrame();
            }
            if(cmbBoxOptions.getSelectedIndex() == 5){
                adminMainPanel.openHandleOrdersFrame();
            }
            if(cmbBoxOptions.getSelectedIndex() == 6){
                adminMainPanel.openViewUsedDiscountsFrame();
            }
            if (cmbBoxOptions.getSelectedIndex() == 7){
                adminMainPanel.openViewMaximumOrderFrame();
            }
        }
    }
}
