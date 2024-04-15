package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {
    private JTextField idSaleField;
    private JTextField idProductField;
    private JTextField descriptionField;
    private JTextField amountField;
    private JTextField priceField;
    private JButton addButton;

    public FormPanel(ActionListener actionListener) {
        setLayout(new GridLayout(6, 2));

        idSaleField = new JTextField();
        idProductField = new JTextField();
        descriptionField = new JTextField();
        amountField = new JTextField();
        priceField = new JTextField();
        addButton = new JButton("Agregar");

        add(new JLabel("ID Venta:"));
        add(idSaleField);
        add(new JLabel("ID Producto:"));
        idProductField.setActionCommand("refreshInfo");
        idProductField.addActionListener(actionListener);
        add(idProductField);

        add(new JLabel("Descripción:"));
        descriptionField.setEditable(false);
        add(descriptionField);
        add(new JLabel("Precio:"));
        priceField.setEditable(false);
        add(priceField);
        add(new JLabel("Cantidad:"));
        add(amountField);
        addButton.addActionListener(actionListener);
        addButton.setActionCommand("add");
        add(addButton);

        setVisible(true);
    }

    public boolean verify() {
        return idSaleField.getText().isEmpty() ||
                idProductField.getText().isEmpty() ||
                descriptionField.getText().isEmpty() ||
                amountField.getText().isEmpty() ||
                priceField.getText().isEmpty();
    }

    public JTextField getIdSaleField() {
        return idSaleField;
    }

    public void setPriceField(String price) {
        priceField.setText(price);
    }

    public void setDescriptionField(String description) {
        descriptionField.setText(description);
    }

    public JTextField getIdProductField() {
        return idProductField;
    }

    public JTextField getAmountField() {
        return amountField;
    }
}
