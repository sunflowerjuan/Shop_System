package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private JLabel imageShop;
    private JButton exitButton;

    public HeaderPanel() {
        setLayout(new GridLayout(1, 2));
        setBackground(new Color(0, 151, 178));

        imageShop = new JLabel();
        ImageIcon icon = new ImageIcon("img/header.png");
        imageShop.setIcon(icon);
        add(imageShop);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(0, 151, 178));
        exitButton = new JButton("Salir");
        exitButton.setPreferredSize(new Dimension(60, 30));
        buttonPanel.add(exitButton);

        add(buttonPanel);
    }
}
