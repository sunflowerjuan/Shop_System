package co.edu.uptc.model;

import java.util.List;

public class SystemManager {
    private List<Product> products;
    private List<Sale> sales;

    public SystemManager(List<Product> products, List<Sale> sales) {
        this.products = products;
        this.sales = sales;
    }

    public SystemManager() {
    }

    public Product searchProduct(String id) {
        Product theProduct = null;
        for (Product product : products) {
            if(product.getReference().equals(id)) {
                theProduct = product;
            }
        }
        return theProduct;
    }

    public void sellProducts(Sale sale) {
        sales.add(sale);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
