package co.edu.uptc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.model.Product;
import co.edu.uptc.model.Sale;
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

    public void refreshInfo() {
        Product product = system.searchProduct(principalPanel.getIdProduct());
        principalPanel.setDescription(product.getName());
        principalPanel.setPrice(String.valueOf(product.getPrice()));
    }

    public void add() {

        Product product = system.searchProduct(principalPanel.getIdProduct());
        if (!principalPanel.verify() && product.getStock() >= principalPanel.getAmount()) {

            Object[] row = new Object[5];
            row[0] = product.getReference();
            row[1] = product.getName();
            row[2] = principalPanel.getAmount();
            row[3] = product.getPrice();
            double price = principalPanel.getAmount() * product.getPrice();
            row[4] = price;
            principalPanel.addRow(row);
            int index = system.getSale(principalPanel.getSaleId());
            if (index == -1) {
                Sale sale = new Sale();
                sale.setId(principalPanel.getSaleId());
                sale.setPrice(sale.getPrice() + price);
                sale.addProduct(product, principalPanel.getAmount());
                system.sellProducts(sale);
            } else {
                Sale sale = system.getSales().get(index);
                sale.addProduct(product, principalPanel.getAmount());
                system.getSales().set(index, sale);
                System.out.println(sale.toString());
            }

        } else {
            principalPanel.showErrorMessage("DATOS INVALIDOS");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String source = e.getActionCommand();
        switch (source) {
            case "refreshInfo":
                refreshInfo();
                break;
            case "add":
                add();

                break;
        }
    }

}
