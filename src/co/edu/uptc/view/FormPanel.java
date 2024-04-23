package co.edu.uptc.view;

import javax.swing.*;

import co.edu.uptc.view.interfaces.CustomEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel implements ActionListener {
    private JTextField idSaleField;
    private JTextField idProductField;
    private JTextField descriptionField;
    private JTextField amountField;
    private JTextField priceField;
    private JButton addButton;
    private CustomEvent event;

    public FormPanel() {
        setLayout(new GridLayout(6, 2));

        idSaleField = new JTextField();
        idProductField = new JTextField();
        descriptionField = new JTextField();
        amountField = new JTextField();
        priceField = new JTextField();
        addButton = new JButton("Agregar");

        add(new JLabel("ID Venta:"));
        idSaleField.setEditable(false);
        add(idSaleField);
        add(new JLabel("ID Producto:"));
        idProductField.setActionCommand("refreshInfo");
        idProductField.addActionListener(this);
        add(idProductField);

        add(new JLabel("Descripción:"));
        descriptionField.setEditable(false);
        add(descriptionField);
        add(new JLabel("Precio:"));
        priceField.setEditable(false);
        add(priceField);
        add(new JLabel("Cantidad:"));
        add(amountField);
        addButton.addActionListener(this);
        addButton.setActionCommand("add");
        add(addButton);

        setVisible(true);
    }

    public void verify() throws Exception {
        if (idSaleField.getText().isEmpty() ||
                idProductField.getText().isEmpty() ||
                descriptionField.getText().isEmpty() ||
                amountField.getText().isEmpty() ||
                priceField.getText().isEmpty()) {
            throw new Exception("DATOS INCOMPLETOS");
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        event.reciveData(e.getActionCommand());
    }

    public CustomEvent getEvent() {
        return event;
    }

    public void setEvent(CustomEvent event) {
        this.event = event;
    }
}
