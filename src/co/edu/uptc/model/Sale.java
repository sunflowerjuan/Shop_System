package co.edu.uptc.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    private String id;
    private List<Product> products;
    private double price;
    private LocalDate saleDate;

    public Sale(String id, List<Product> products, double price, LocalDate saleDate) {
        this.id = id;
        this.products = products;
        this.price = price;
        this.saleDate = saleDate;
    }

    public Sale() {
        products = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
}
