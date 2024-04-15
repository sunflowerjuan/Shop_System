package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SellPanel extends JPanel {
    private JTextField totalField;
    private JButton sellButton;

    public SellPanel(ActionListener actionListener) {
        setLayout(new GridLayout(1, 3));

        totalField = new JTextField();
        totalField.setEditable(false);
        add(new JLabel("Total:"));
        add(totalField);

        sellButton = new JButton("Vender");
        sellButton.setActionCommand("sell");
        sellButton.addActionListener(actionListener);
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
}
