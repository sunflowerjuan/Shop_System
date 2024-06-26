package co.edu.uptc.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Sale {
    private String id;
    HashMap<Product, Integer> products = new HashMap<>();
    private double price;

    public Sale(String id, HashMap<Product, Integer> hashMap, double price) {
        this.id = id;
        this.products = hashMap;
        this.price = price;
    }

    public Sale() {
        price = 0;
    }

    public void addProduct(Product product, int amount) {
        products.put(product, amount);
        price += product.getPrice() * amount;
    }

    public void deleteProduct(String id) throws Exception {
        Product product = searchProduct(id);
        if (product == null) {
            throw new Exception("Producto no encontrado");
        }
        products.remove(product);
    }

    public Product searchProduct(String id) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getReference().equals(id)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Product, Integer> hashMap) {
        this.products = hashMap;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
