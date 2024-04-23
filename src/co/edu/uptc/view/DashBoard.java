package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DashBoard extends JFrame {
    private FormPanel formPanel;
    private TablePanel tablePanel;
    private SellPanel sellPanel;
    private HeaderPanel headerPanel;
    private BillPanel billPanel;
    private ClosePanel closePanel;
    private AddButtonPanel addButtonPanel;
    private AddProductDialog addProductDialog;

    public DashBoard(ActionListener actionListener) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        load(actionListener);
        pack();
        setResizable(true);
        setVisible(true);
    }

    public void load(ActionListener actionListener) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        addProductDialog = new AddProductDialog(this,actionListener);

        headerPanel = new HeaderPanel(actionListener);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(headerPanel, gbc);

        addButtonPanel = new AddButtonPanel(actionListener);
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        add(addButtonPanel, gbc);

        formPanel = new FormPanel(actionListener);
        gbc.gridy = 2;
        gbc.weighty = 0.5;
        add(formPanel, gbc);

        tablePanel = new TablePanel(actionListener);
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        add(tablePanel, gbc);

        sellPanel = new SellPanel(actionListener);
        gbc.gridy = 4;
        gbc.weighty = 0.1;
        add(sellPanel, gbc);
    }


    public void addRow(Object[] row) {
        tablePanel.addRRow(row);
    }

    public void setDescription(String description) {
        formPanel.setDescriptionField(description);
    }

    public void setPrice(String price) {
        formPanel.setPriceField(price);
    }

    public String getIdProduct() {
        return formPanel.getIdProductField().getText();
    }

    public void setIdProduct(String text) {
        formPanel.getIdProductField().setText(text);
    }

    public int getAmount() {
        return Integer.parseInt(formPanel.getAmountField().getText());
    }

    public void setAmount(String text) {
        formPanel.getAmountField().setText(text);
    }

    public void verify() throws Exception {
        formPanel.verify();
    }

    public String getSaleId() {
        return formPanel.getIdSaleField().getText();
    }

    public void setSaleId(String id) {
        formPanel.getIdSaleField().setText(id);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public void setTotalField(String text) {
        sellPanel.setTotalField(text);
    }

    public Object[] tableDelete() {
        return tablePanel.delete();
    }

    public void clean() {
        tablePanel.clean();
        tablePanel.repaint();
        tablePanel.revalidate();
    }

    public void sell(ActionListener actionListener) {
        billPanel = new BillPanel(actionListener);
    }

    public void close(ActionListener actionListener) {
        closePanel = new ClosePanel(actionListener);
    }

    public BillPanel getBillPanel() {
        return billPanel;
    }


    public ClosePanel getClosePanel() {
        return closePanel;
    }


    public void closeAll() {
        dispose();
    }

    public AddProductDialog getAddProductDialog() {
        return addProductDialog;
    }

    public String getNewReference() {
        return addProductDialog.getReference();
    }

    public String getNewName() {
        return addProductDialog.getProductName();
    }

    public String getNewAmount(){
        return addProductDialog.getAmount();
    }

    public String getNewPrice(){
        return addProductDialog.getProductPrice();
    }
}
