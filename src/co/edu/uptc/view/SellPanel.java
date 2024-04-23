package co.edu.uptc.view;

import javax.swing.*;

import co.edu.uptc.view.interfaces.CustomEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellPanel extends JPanel implements ActionListener {
    private JTextField totalField;
    private JButton sellButton;
    private CustomEvent event;

    public SellPanel() {
        setLayout(new GridLayout(1, 3));

        totalField = new JTextField();
        totalField.setEditable(false);
        add(new JLabel("Total:"));
        add(totalField);

        sellButton = new JButton("Vender");
        sellButton.setActionCommand("sell");
        sellButton.addActionListener(this);
        sellButton.setPreferredSize(new Dimension(100, 30));
        add(sellButton);
    }

    public String getTotalField() {
        return totalField.getText();
    }

    public void setTotalField(String text) {
        totalField.setText(text);
    }

    public JButton getSellButton() {
        return sellButton;
    }

    public void setSellButton(JButton sellButton) {
        this.sellButton = sellButton;
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
