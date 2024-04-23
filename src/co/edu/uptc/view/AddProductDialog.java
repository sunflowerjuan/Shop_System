package co.edu.uptc.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AddProductDialog extends JDialog {
    private AddProductPanel addProductPanel;

    public AddProductDialog(JFrame parentFrame, ActionListener actionListener) {
        super(parentFrame, "Agregar Producto", true);
        setSize(400, 200);
        setLocationRelativeTo(parentFrame);

        addProductPanel = new AddProductPanel(actionListener);
        add(addProductPanel);
    }

    public void clean(){
        addProductPanel.clean();
        dispose();
    }

    public String getReference(){
        return addProductPanel.getIdProductField().getText();
    }

    public String getProductName() {
        return addProductPanel.getDescriptionField().getText();
    }

    public String getProductPrice() {
        return addProductPanel.getPriceField().getText();
    }

    public String getAmount() {
        return addProductPanel.getAmountField().getText();
    }

}

