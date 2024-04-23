package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddProductPanel extends JPanel {
    private JTextField idProductField;
    private JTextField descriptionField;
    private JTextField amountField;
    private JTextField priceField;
    private JButton addButton;

    public AddProductPanel(ActionListener actionListener) {
        setLayout(new GridLayout(6, 2));

        idProductField = new JTextField();
        descriptionField = new JTextField();
        amountField = new JTextField();
        priceField = new JTextField();
        addButton = new JButton("Agregar");

        add(new JLabel("ID Producto:"));
        idProductField.setActionCommand("refreshInfo");
        idProductField.addActionListener(actionListener);
        add(idProductField);

        add(new JLabel("Descripción:"));
        add(descriptionField);
        add(new JLabel("Precio:"));
        add(priceField);
        add(new JLabel("Cantidad:"));
        add(amountField);
        addButton.addActionListener(actionListener);
        addButton.setActionCommand("addProduct");
        add(addButton);

        setVisible(true);
    }

    public void clean(){
        idProductField.setText("");
        descriptionField.setText("");
        amountField.setText("");
        priceField.setText("");
    }

    public void verify() throws Exception {
        if (idProductField.getText().isEmpty() ||
                descriptionField.getText().isEmpty() ||
                amountField.getText().isEmpty() ||
                priceField.getText().isEmpty()) {
            throw new Exception("DATOS INCOMPLETOS");
        }
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public JTextField getAmountField() {
        return amountField;
    }

    public JTextField getDescriptionField() {
        return descriptionField;
    }

    public JTextField getIdProductField() {
        return idProductField;
    }

}
