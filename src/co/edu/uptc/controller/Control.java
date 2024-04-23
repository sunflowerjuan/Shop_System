package co.edu.uptc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import co.edu.uptc.model.Product;
import co.edu.uptc.model.Sale;
import co.edu.uptc.model.SystemManager;
import co.edu.uptc.persist.FileManager;
import co.edu.uptc.view.DashBoard;

public class Control implements ActionListener {
    private SystemManager system;
    private FileManager fileManager;
    private DashBoard dashBoard;

    public Control() {
        system = new SystemManager();
        fileManager = new FileManager("data/Products.txt");
        init();
    }

    public void init() {
        loadProducts();
        costInventory();
    }

    public void addNewProduct() {
        String reference = dashBoard.getNewReference();
        String name = dashBoard.getNewName();
        Double price = Double.valueOf(dashBoard.getNewPrice());
        int amount = Integer.parseInt(dashBoard.getNewAmount());
        Double priceSold = system.calcSalePrice(price);
        Product newProduct = new Product(reference,amount, name, price, priceSold);
        try {
            system.addProduct(newProduct);
        } catch (Exception e) {
            dashBoard.showErrorMessage(e.getMessage());
        }
    }

    public void loadProducts() {
        List<Product> listProducts = new ArrayList<>();
        for (String fileProduct : fileManager.readFile()) {
            String[] data = fileProduct.split(",");
            Product product = new Product();
            product.setReference(data[0]);
            product.setName(data[1]);
            product.setStock(Integer.parseInt(data[2]));
            double price = Double.parseDouble(data[3]);
            product.setPrice(price);
            product.setSalePrice(system.calcSalePrice(price));
            listProducts.add(product);
        }
        system.setProducts(listProducts);
        dashBoard = new DashBoard(this);
    }

    public void uploadProducts() {
        List<Product> listProducts = system.getProducts();
        fileManager.clear();
        for (Product product : listProducts) {
            fileManager.writeFile(product.makeToString());
        }
    }

    public void costInventory() {
        FileManager costManager = new FileManager("data/ConstInventory.txt");
        costManager.clear();
        costManager.writeFile("More Expensive: " + system.getMoreExpensive().makeToString());
        costManager.writeFile("More Cheap: " + system.getMoreCheap().makeToString());
        costManager.writeFile("Total Inventary Cost: " + system.calcInventoryCost());
    }

    public void refreshInfo() {
        try {
            Product product = system.searchProduct(dashBoard.getIdProduct());
            dashBoard.setDescription(product.getName());
            dashBoard.setPrice(String.valueOf(product.getSalePrice()));
            if (dashBoard.getSaleId().equals("")) {
                dashBoard.setSaleId(system.generateIds());
            }

        } catch (Exception e) {
            dashBoard.showErrorMessage(e.getMessage());
            dashBoard.setIdProduct("");
            dashBoard.setDescription("");
            dashBoard.setPrice("");
            dashBoard.setAmount("");
        }
    }

    public void add() {
        try {
            Product product = system.searchProduct(dashBoard.getIdProduct());
            dashBoard.verify();
            product.verifyStock(dashBoard.getAmount());
            Object[] row = new Object[5];
            row[0] = product.getReference();
            row[1] = product.getName();
            if (dashBoard.getAmount() <= 0) {
                dashBoard.setAmount("");
                throw new Exception("CANTIDAD INVALIDA");
            }
            row[2] = dashBoard.getAmount();
            row[3] = product.getSalePrice();
            double price = dashBoard.getAmount() * product.getSalePrice();
            row[4] = price;
            dashBoard.addRow(row);
            int index = system.getProduct(product.getReference());
            system.getProducts().get(index)
                    .setStock(system.getProducts().get(index).getStock() - dashBoard.getAmount());

            index = system.getSale(dashBoard.getSaleId());
            if (index == -1) {
                Sale sale = new Sale();
                sale.setId(dashBoard.getSaleId());
                sale.addProduct(product, dashBoard.getAmount());
                system.sellProducts(sale);
                dashBoard.setTotalField(sale.getPrice() + "");
            } else {
                Sale sale = system.getSales().get(index);
                sale.addProduct(product, dashBoard.getAmount());
                system.getSales().set(index, sale);
                dashBoard.setTotalField(sale.getPrice() + "");
            }

        } catch (Exception e) {
            dashBoard.showErrorMessage(e.getMessage());
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
            case "delete":
                delete();
                break;
            case "closeBill":
                closeBill();
                break;
            case "exit":
                closeAll();
                break;
            case "closeAll":
                dashBoard.closeAll();
                dashBoard.getClosePanel().setVisible(false);
                uploadProducts();
                break;
            case "addProductPanel":
                dashBoard.getAddProductDialog().setVisible(true);
                break;
            case "addProduct":
                    addNewProduct();
                    dashBoard.getAddProductDialog().clean();
                break;
        }
    }

    private void closeAll() {
        dashBoard.close(this);
        double plus = 0;
        for (Sale sale : system.getSales()) {
            Object[] row = new Object[2];
            row[0] = sale.getId();
            row[1] = sale.getPrice();
            plus += sale.getPrice();
            dashBoard.getClosePanel().addRRow(row);
        }
        dashBoard.getClosePanel().setpayTextField(plus + "");
        dashBoard.getClosePanel().setVisible(true);
    }

    private void delete() {
        Object[] data = dashBoard.tableDelete();
        if (data[0] == null) {
            try {
                throw new Exception("SELECCIONE EL OBJETO PRIMERO");
            } catch (Exception e) {
                dashBoard.showErrorMessage(e.getMessage());
            }
        } else {
            Product product = new Product();
            product.setReference((String) data[0]);
            product.setName((String) data[1]);
            product.setStock((Integer) data[2]);
            int pos = system.getSale(dashBoard.getSaleId());
            try {
                system.getSales().get(pos).deleteProduct(product.getReference());
                double price = (Double) data[4] - (system.getSales().get(pos).getPrice());
                system.getSales().get(pos).setPrice(price);
                dashBoard.setTotalField(system.getSales().get(pos).getPrice() + "");
                pos = system.getProduct(product.getReference());
                int stock = system.getProducts().get(pos).getStock() + product.getStock();
                system.getProducts().get(pos).setStock(stock);

            } catch (Exception e) {
                e.printStackTrace();
            }

            refreshInfo();
        }

    }

    private void sell() {
        int index = system.getSale(dashBoard.getSaleId());
        if (index == -1) {
            try {
                throw new Exception("AGREGUE PRODUCTOS AL CARRITO");
            } catch (Exception e) {
                dashBoard.showErrorMessage(e.getMessage());
            }
        } else {
            Sale sale = system.getSales().get(index);
            dashBoard.sell(this);
            for (Map.Entry<Product, Integer> entry : sale.getProducts().entrySet()) {
                Object[] row = new Object[5];
                row[0] = entry.getKey().getReference();
                row[1] = entry.getKey().getName();
                row[2] = entry.getValue();
                row[3] = entry.getKey().getPrice();
                double price = dashBoard.getAmount() * entry.getKey().getPrice();
                row[4] = price;
                dashBoard.getBillPanel().addRRow(row);
            }
            dashBoard.getBillPanel().setpayTextField(sale.getPrice() + "");
            dashBoard.getBillPanel().setVisible(true);
        }

    }

    public void closeBill() {
        dashBoard.getBillPanel().setVisible(false);
        dashBoard.setSaleId("");
        dashBoard.setIdProduct("");
        dashBoard.setDescription("");
        dashBoard.setPrice("");
        dashBoard.setAmount("");
        dashBoard.clean();
    }

}
