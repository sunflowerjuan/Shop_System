package co.edu.uptc.model;

public class Product {
    private String reference;
    private int stock;
    private String name;
    private double price;

    public Product(String reference, int stock, String name, double price) {
        this.reference = reference;
        this.stock = stock;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public void verifyStock(int amount) throws Exception {
        if (amount > stock) {
            throw new Exception("Stock Insuficiente. Cantidad Actual :" + stock);
        }
    }

    public String makeToString() {
        return reference + "," + name + "," + stock + "," + price;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
