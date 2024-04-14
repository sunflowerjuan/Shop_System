package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SellPanel extends JPanel {
    private JTextField totalField;
    private JButton sellButton;

    public SellPanel() {
        setLayout(new GridLayout(1, 3));

        totalField = new JTextField();
        add(new JLabel("Total:"));
        add(totalField);

        sellButton = new JButton("Vender");
        sellButton.setPreferredSize(new Dimension(100, 30));
        add(sellButton);
    }
}
