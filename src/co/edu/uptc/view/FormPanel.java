package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel {
    private JTextField idSaleField;
    private JTextField idProductoField;
    private JTextField descriptionField;
    private JTextField amountField;
    private JTextField priceField;
    private JButton addButton;

    public FormPanel() {
        setLayout(new GridLayout(6,2));

        idSaleField = new JTextField();
        idProductoField = new JTextField();
        descriptionField = new JTextField();
        amountField = new JTextField();
        priceField = new JTextField();
        addButton = new JButton("Agregar");

        add(new JLabel("ID Venta:"));
        add(idSaleField);
        add(new JLabel("ID Producto:"));
        add(idProductoField);
        add(new JLabel("Descripción:"));
        add(descriptionField);
        add(new JLabel("Cantidad:"));
        add(amountField);
        add(new JLabel("Precio:"));
        add(priceField);
        add(addButton);

        setVisible(true);
    }
}
