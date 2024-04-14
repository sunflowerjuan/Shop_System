package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class SellPanel extends JPanel {
    private JTextField totalField;
    private JButton sellButton;

    public SellPanel() {
        setLayout(new GridLayout(1, 3));

        totalField = new JTextField();
        add(new JLabel("Total:"));
        add(totalField);

        sellButton = new JButton("Vender");
        add(sellButton);
    }
}
