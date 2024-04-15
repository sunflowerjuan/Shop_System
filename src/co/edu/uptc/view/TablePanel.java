package co.edu.uptc.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class TablePanel extends JPanel{
    private JTable table;
    private JButton deleteButton;
    private DefaultTableModel modelTable;


    public TablePanel() {
        setLayout(new BorderLayout());

        modelTable = new DefaultTableModel();
        modelTable.addColumn("ID Producto");
        modelTable.addColumn("Descripción");
        modelTable.addColumn("Cantidad");
        modelTable.addColumn("Precio/u");
        modelTable.addColumn("Precio Total");

        table = new JTable(modelTable);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deleteButton = new JButton("Eliminar");
        deleteButton.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addRRow(Object[] row) {
        modelTable.addRow(row);
    }

    public void delete() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            modelTable.removeRow(selectedRow);
        }
    }
}
