package co.edu.uptc.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import co.edu.uptc.view.interfaces.CustomEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TablePanel extends JPanel implements ActionListener {
    private JTable table;
    private JButton deleteButton;
    private DefaultTableModel modelTable;
    private CustomEvent event;

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
        deleteButton.addActionListener(this);
        deleteButton.setActionCommand("delete");
        deleteButton.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void addRRow(Object[] row) {
        modelTable.addRow(row);
    }

    public Object[] delete() {
        Object[] row = new Object[5];
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            row[0] = table.getValueAt(selectedRow, 0);
            row[1] = table.getValueAt(selectedRow, 1);
            row[2] = table.getValueAt(selectedRow, 2);
            row[3] = table.getValueAt(selectedRow, 3);
            row[4] = table.getValueAt(selectedRow, 4);
            modelTable.removeRow(selectedRow);
        }
        return row;
    }

    public void clean() {
        for (int i = 0; i < table.getRowCount(); i++) {
            modelTable.removeRow(i);
        }
        revalidate();
        repaint();
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
