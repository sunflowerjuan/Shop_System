package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private JLabel imageShop;
    private JButton exitButton;

    public HeaderPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(0, 151, 178));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        imageShop = new JLabel();
        ImageIcon icon = new ImageIcon("img/header.png");
        imageShop.setIcon(icon);
        add(imageShop, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 20);

        exitButton = new JButton("Salir");
        exitButton.setPreferredSize(new Dimension(60, 30));
        add(exitButton, gbc);
    }
}
