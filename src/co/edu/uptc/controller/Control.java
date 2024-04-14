package co.edu.uptc.controller;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.model.Product;
import co.edu.uptc.model.SystemManager;
import co.edu.uptc.persist.FileManager;
import co.edu.uptc.view.PrincipalPanel;

public class Control {
    private SystemManager system;
    private FileManager fileManager;

    public Control() {
        system = new SystemManager();
        fileManager = new FileManager("data/Productos.txt");
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

    public static void main(String[] args) {
        PrincipalPanel xd = new PrincipalPanel();
    }
}
