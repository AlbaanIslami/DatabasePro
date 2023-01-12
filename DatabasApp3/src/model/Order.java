package model;

import controller.Controller;
import controller.DBController;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Order {
    private String id;
    private Set<Product> items;
    private OrderStatus status;
    private String orderPlacer;
    private Set<Product> orderTimes;


    public Order(String id, String items, OrderStatus status, String orderPlacer) {
        this.id = id;
        this.items = deCodeOrderItems(items);
        this.status = status;
        this.orderPlacer = orderPlacer;

    }

    public Order(String orderPlacer) {
        this.id = UUID.randomUUID().toString();
        this.items = new HashSet<>();
        this.status = OrderStatus.CURRENT;
        this.orderPlacer = orderPlacer;
    }

    public void addProductToOrder(Product product) {
        items.add(product);
    }

    public static Set<Product> deCodeOrderItems(String items) {
        Set<Product> val = new HashSet<>();
        if (items.isEmpty())
            return val;

        if (!items.contains("-")) {
            String[] parts = items.split(":");
            String code = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            Product p = Controller.getInstance().getProductById(code);
            if (p == null)
                return val;
            Product newProduct = new Product(p.getProductName(), quantity, p.getProductBasePrice(), p.getProductSupplier(), p.getProductID(), p.orderTimes);
            val.add(newProduct);
            return val;
        }
        String[] singleItems = items.split("-");
        for (String s : singleItems) {
            String[] parts = s.split(":");
            String code = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            Product p = Controller.getInstance().getProductById(code);
            if (p == null)
                continue;
            Product newProduct = new Product(p.getProductName(), quantity, p.getProductBasePrice(), p.getProductSupplier(), p.getProductID(), p.orderTimes);
            val.add(newProduct);
        }
        return val;
    }

    public static String codeOrderItems(Set<Product> items) {
        if (items.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();

        for (Product p : items) {
            sb.append(p.getProductID()).append(":").append(p.getProductQuantity()).append("-");
        }
        return sb.substring(0, sb.length() - 1);
    }


    public void saveToDatabase() {
        DBController.getInstance().executeQuery(String.format("INSERT INTO orders (confirmation, orderContent, orderId, username) VALUES ('%s','%s','%s','%s')", getStatus().name(), codeOrderItems(getItems()), getId(), getOrderPlacer()));
    }

    public void deleteFromDatabase() {
        DBController.getInstance().executeQuery("BEGIN;\n"+
                "\n" + "DELETE FROM orders WHERE orderId='" + getId() + "'; \n" +
                "\n" +
                "COMMIT;\n");
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Product> getItems() {
        return items;
    }

    public void setItems(Set<Product> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getOrderPlacer() {
        return orderPlacer;
    }

    public void setOrderPlacer(String orderPlacer) {
        this.orderPlacer = orderPlacer;
    }



    public int calcPrice() {
        int val = 0;
        for (Product item : items) {
            val += item.getProductPrice() * item.getProductQuantity();
        }
        System.out.println(val);
        return val;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s pcs | %s:- | Placed from %s", getId(), getStatus().name(), items.size(), calcPrice(), getOrderPlacer());
    }

    public void updateDatabase() {
        deleteFromDatabase();
        saveToDatabase();
    }

    public void cancelOrder() {
        for (Product p : getItems()) {
            for (Product product : Controller.getInstance().getProducts()) {
                if (p.getProductID().equals(product.getProductID())) {
                    product.setProductQuantity(product.getProductQuantity() + p.getProductQuantity());
                    product.updateProduct();
                }

            }
        }
        Controller.getInstance().updateProductList();
        this.deleteFromDatabase();
    }

    public void confirmOrder() {
        this.setStatus(OrderStatus.CONFIRMED);
        this.updateDatabase();
        for (Product p : getItems()) {
            for (Product product : Controller.getInstance().getProducts()) {
                if (p.getProductID().equals(product.getProductID())) {
                    product.orderTimes += p.getProductQuantity();
                    product.updateProduct();
                }

            }
        }
        Controller.getInstance().updateProductList();

    }
}
