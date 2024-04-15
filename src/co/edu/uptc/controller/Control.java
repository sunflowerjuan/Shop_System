package co.edu.uptc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.model.Product;
import co.edu.uptc.model.SystemManager;
import co.edu.uptc.persist.FileManager;
import co.edu.uptc.view.PrincipalPanel;

public class Control implements ActionListener {
    private SystemManager system;
    private FileManager fileManager;
    private PrincipalPanel principalPanel;

    public Control() {
        system = new SystemManager();
        fileManager = new FileManager("data/Productos.txt");
        init();
        principalPanel = new PrincipalPanel(this);
    }

    public void init() {
        loadProducts();
    }

    public void loadProducts() {
        List<Product> listProducts = new ArrayList<>();
        for (String fileProduct : fileManager.readFile()) {
            String[] data = fileProduct.split(",");
            Product product = new Product();
            product.setReference(data[0]);
            product.setName(data[1]);
            product.setStock(Integer.parseInt(data[2]));
            product.setPrice(Double.parseDouble(data[3]));
            listProducts.add(product);
        }
        system.setProducts(listProducts);
    }

    public void refreshInfo(){
        Product product = system.searchProduct(principalPanel.getIdProduct());
        principalPanel.setDescription(product.getName());
        principalPanel.setPrice(String.valueOf(product.getPrice()));
    }

    public static void main(String[] args) {
        Control xd = new Control();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String source = e.getActionCommand();
        switch (source) {
            case "refreshInfo":
                refreshInfo();
                break;
        }
    }
}
