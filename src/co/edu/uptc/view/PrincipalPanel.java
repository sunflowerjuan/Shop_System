package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class PrincipalPanel extends JFrame {
    private FormPanel formPanel;
    private TablePanel tablePanel;
    private SellPanel sellPanel;
    private HeaderPanel headerPanel;

    public PrincipalPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        load();
        pack();
        setVisible(true);
    }

    public void load() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        headerPanel = new HeaderPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(headerPanel, gbc);

        formPanel = new FormPanel();
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        add(formPanel, gbc);

        tablePanel = new TablePanel();
        gbc.gridy = 2;
        gbc.weighty = 0.5;
        add(tablePanel, gbc);

        sellPanel = new SellPanel();
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        add(sellPanel, gbc);
    }
}
