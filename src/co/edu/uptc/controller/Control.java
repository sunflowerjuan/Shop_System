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

    public void uploadProducts() {
        List<Product> listProducts = system.getProducts();
        for (Product product : listProducts) {
            fileManager.writeFile(product.makeToString());
        }
    }

    public void refreshInfo() {
        try {
            Product product = system.searchProduct(principalPanel.getIdProduct());
            principalPanel.setDescription(product.getName());
            principalPanel.setPrice(String.valueOf(product.getPrice()));
            principalPanel.setSaleId(system.generateIds());
        } catch (Exception e) {
            principalPanel.showErrorMessage(e.getMessage());
            principalPanel.setIdProduct("");
            principalPanel.setDescription("");
            principalPanel.setPrice("");
            principalPanel.setAmount("");
        }
    }

    public void add() {
        try {
            Product product = system.searchProduct(principalPanel.getIdProduct());
            principalPanel.verify();
            product.verifyStock(principalPanel.getAmount());
            Object[] row = new Object[5];
            row[0] = product.getReference();
            row[1] = product.getName();
            row[2] = principalPanel.getAmount();
            row[3] = product.getPrice();
            double price = principalPanel.getAmount() * product.getPrice();
            row[4] = price;
            principalPanel.addRow(row);
            int index = system.getProduct(product.getReference());
            system.getProducts().get(index)
                    .setStock(system.getProducts().get(index).getStock() - principalPanel.getAmount());

            index = system.getSale(principalPanel.getSaleId());
            if (index == -1) {
                Sale sale = new Sale();
                sale.setId(principalPanel.getSaleId());
                sale.addProduct(product, principalPanel.getAmount());
                system.sellProducts(sale);
                principalPanel.setTotalField(sale.getPrice() + "");
            } else {
                Sale sale = system.getSales().get(index);
                sale.addProduct(product, principalPanel.getAmount());
                system.getSales().set(index, sale);
                principalPanel.setTotalField(sale.getPrice() + "");
            }

        } catch (Exception e) {
            principalPanel.showErrorMessage(e.getMessage());
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
            case "sell":
                sell();
                break;
        }
    }

    private void sell() {

    }

}
