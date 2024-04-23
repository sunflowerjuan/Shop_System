package co.edu.uptc.view;

import javax.swing.*;

import co.edu.uptc.view.interfaces.CustomEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeaderPanel extends JPanel implements ActionListener {
    private JLabel imageShop;
    private JButton exitButton;
    private CustomEvent event;

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

        exitButton = new JButton("Cerrar Turno");
        exitButton.setActionCommand("exit");
        exitButton.addActionListener(this);
        exitButton.setPreferredSize(new Dimension(150, 30));
        add(exitButton, gbc);
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
