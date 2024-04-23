package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddButtonPanel extends JPanel {
    public AddButtonPanel(ActionListener actionListener) {
        setLayout(new BorderLayout());

        // Creación del botón
        JButton addButton = new JButton("Agregar");
        addButton.setActionCommand("addProductPanel");
        addButton.addActionListener(actionListener);

        // Añadir el botón al panel
        add(addButton, BorderLayout.WEST);
    }
}
