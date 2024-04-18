package co.edu.uptc.model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class SystemManager {
    private List<Product> products;
    private List<Sale> sales;

    public SystemManager(List<Product> products, List<Sale> sales) {
        this.products = products;
        this.sales = sales;
    }

    public SystemManager() {
        sales = new ArrayList<>();
    }

    public void addProduct(Product product) throws Exception {
        if (getProduct(product.getReference()) == -1) {
            throw new Exception("REFERENCIA DUPLICADA");
        }
        products.add(product);
    }

    public void deleteProduct(String reference) throws Exception {
        int pos = getProduct(reference);
        if (pos == -1) {
            throw new Exception("NO ENCONTRADO");
        }
        products.remove(pos);
    }

    public double calcSalePrice(double price) {
        double soldPrice = price * 3939.80;
        return (soldPrice + (soldPrice * 0.25));
    }

    public Product getMoreExpensive() {
        Product auxProduc = products.get(0);
        for (Product product : products) {
            if (product.getPrice() > auxProduc.getPrice()) {
                auxProduc = product;
            }
        }
        return auxProduc;
    }

    public Product getMoreCheap() {
        Product auxProduc = products.get(0);
        for (Product product : products) {
            if (product.getPrice() < auxProduc.getPrice()) {
                auxProduc = product;
            }
        }
        return auxProduc;
    }

    public double calcInventoryCost() {
        double totalCost = 0;
        for (Product product : products) {
            totalCost += (product.getPrice() * product.getStock());
        }
        return totalCost;
    }

    public Product searchProduct(String id) throws Exception {
        for (Product product : products) {
            if (product.getReference().equals(id)) {
                return product;
            }
        }
        throw new Exception("NO ENCONTRADO");
    }

    public int getProduct(String id) {
        int index = 0;
        for (Product product : products) {
            if (product.getReference().equals(id)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int getSale(String id) {
        int index = 0;
        for (Sale sale : sales) {
            if (sale.getId().equals(id)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public String generateIds() {
        return sales.size() + "";
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
