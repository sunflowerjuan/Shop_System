package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class PrincipalPanel extends JFrame {
    private FormPanel formPanel;
    private TablePanel tablePanel;
    private SellPanel sellPanel;


    public PrincipalPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        load();
        setVisible(true);
    }

    public void load(){
        setLayout(new BorderLayout());

        formPanel = new FormPanel();
        tablePanel = new TablePanel();
        sellPanel = new SellPanel();

        add(formPanel,BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(sellPanel, BorderLayout.SOUTH);
    }

}