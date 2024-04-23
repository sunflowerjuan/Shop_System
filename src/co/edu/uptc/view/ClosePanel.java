package co.edu.uptc.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import co.edu.uptc.view.interfaces.CustomEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClosePanel extends JPopupMenu implements ActionListener {

    private DefaultTableModel modelTable;
    private JLabel priceLabel;
    private JTextField payTextField;
    private CustomEvent event;

    public ClosePanel() {
        setVisible(false);

        JLabel header = new JLabel("CIERRE DE CAJA");
        add(header);
        // Crear la tabla
        modelTable = new DefaultTableModel();
        modelTable.addColumn("ID Venta");
        modelTable.addColumn("Total");
        JTable table = new JTable(modelTable);

        // Crear el panel para la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 250));

        // Crear el label "A pay"
        priceLabel = new JLabel("TOTAl VENTAS:");

        // Crear el campo de texto para el monto a pay
        payTextField = new JTextField(10);
        payTextField.setEditable(false);
        // Crear el botón "Cerrar"
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(this);
        cerrarButton.setActionCommand("closeAll");

        // Crear el panel principal y agregar componentes
        JPanel secondPanel = new JPanel(new GridLayout(1, 3));
        secondPanel.add(priceLabel);
        secondPanel.add(payTextField);
        secondPanel.add(cerrarButton);
        add(scrollPane);
        add(secondPanel);
    }

    public void addRRow(Object[] row) {
        modelTable.addRow(row);
    }

    public JTextField getPriceTextField() {
        return payTextField;
    }

    public void setpayTextField(String text) {
        payTextField.setText(text);
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
