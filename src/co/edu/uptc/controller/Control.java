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
        fileManager.clear();
        for (Product product : listProducts) {

            fileManager.writeFile(product.makeToString());
        }
    }

    public void refreshInfo() {
        try {
            Product product = system.searchProduct(principalPanel.getIdProduct());
            principalPanel.setDescription(product.getName());
            principalPanel.setPrice(String.valueOf(product.getPrice()));
            if (principalPanel.getSaleId().equals("")) {
                principalPanel.setSaleId(system.generateIds());
            }

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
            if (principalPanel.getAmount() <= 0) {
                principalPanel.setAmount("");
                throw new Exception("CANTIDAD INVALIDA");
            }
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
                principalPanel.closeAll();
                principalPanel.getClosePanel().setVisible(false);
                uploadProducts();
                break;

        }
    }

    private void closeAll() {
        principalPanel.close(this);
        double plus = 0;
        for (Sale sale : system.getSales()) {
            Object[] row = new Object[2];
            row[0] = sale.getId();
            row[1] = sale.getPrice();
            plus += sale.getPrice();
            principalPanel.getClosePanel().addRRow(row);
        }
        principalPanel.getClosePanel().setpayTextField(plus + "");
        principalPanel.getClosePanel().setVisible(true);
    }

    private void delete() {
        Object[] data = principalPanel.tableDelete();
        if (data[0] == null) {
            try {
                throw new Exception("SELECCIONE EL OBJETO PRIMERO");
            } catch (Exception e) {
                principalPanel.showErrorMessage(e.getMessage());
            }
        } else {
            Product product = new Product();
            product.setReference((String) data[0]);
            product.setName((String) data[1]);
            product.setStock((Integer) data[2]);
            int pos = system.getSale(principalPanel.getSaleId());
            try {
                system.getSales().get(pos).deleteProduct(product.getReference());
                pos = system.getProduct(product.getReference());
                int stok = system.getProducts().get(pos).getStock() + product.getStock();
                system.getProducts().get(pos).setStock(stok);
            } catch (Exception e) {
                e.printStackTrace();
            }
            refreshInfo();
        }

    }

    private void sell() {
        int index = system.getSale(principalPanel.getSaleId());
        if (index == -1) {
            try {
                throw new Exception("AGREGUE PRODUCTOS AL CARRITO");
            } catch (Exception e) {
                principalPanel.showErrorMessage(e.getMessage());
            }
        } else {
            Sale sale = system.getSales().get(index);
            principalPanel.sell(this);
            for (Map.Entry<Product, Integer> entry : sale.getProducts().entrySet()) {
                Object[] row = new Object[5];
                row[0] = entry.getKey().getReference();
                row[1] = entry.getKey().getName();
                row[2] = entry.getValue();
                row[3] = entry.getKey().getPrice();
                double price = principalPanel.getAmount() * entry.getKey().getPrice();
                row[4] = price;
                principalPanel.getBillPanel().addRRow(row);
            }
            principalPanel.getBillPanel().setpayTextField(sale.getPrice() + "");
            principalPanel.getBillPanel().setVisible(true);
        }

    }

    public void closeBill() {
        principalPanel.getBillPanel().setVisible(false);
        principalPanel.setSaleId("");
        principalPanel.setIdProduct("");
        principalPanel.setDescription("");
        principalPanel.setPrice("");
        principalPanel.setAmount("");
        principalPanel.clean();
    }

}
