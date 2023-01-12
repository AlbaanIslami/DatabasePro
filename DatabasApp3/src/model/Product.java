package model;

import controller.DBController;

public class Product implements Cloneable {
    private String productName;
    private int productQuantity;
    private int productBasePrice;
    private int productPrice;
    private String productSupplier;
    private String productID;
    public int orderTimes;


    public Product(String productName, int productQuantity, int productPrice, String productSupplier, String productID, int orderTimes) {
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productBasePrice = productPrice;
        this.productSupplier = productSupplier;
        this.productID = productID;
        this.productPrice = productBasePrice;
        this.orderTimes = orderTimes;
    }



    public int getOrderTimes() {
        return orderTimes;
    }

    public void setOrderTimes(int orderTimes) {
        this.orderTimes = orderTimes;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductBasePrice() {
        return productBasePrice;
    }

    public void setProductBasePrice(int productBasePrice) {
        this.productBasePrice = productBasePrice;
    }

    public String getProductSupplier() {
        return productSupplier;
    }

    public void setProductSupplier(String productSupplier) {
        this.productSupplier = productSupplier;
    }

    public void saveToDatabase() {
        DBController.getInstance().executeQuery(String.format("INSERT INTO products VALUES ('%s','%s', '%s','%s','%s', '%s')", getProductName(), getProductID(), getProductQuantity(), getProductBasePrice(), getProductSupplier(), getOrderTimes()));
    }

    public void deleteFromDatabase() {
        DBController.getInstance().executeQuery("BEGIN;\n"+
                "\n" + "DELETE FROM products WHERE pCode='" + productID + "'; \n" +
                "\n" +
                "COMMIT;\n");
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }


    public boolean hasDiscount(){
        return this.productBasePrice != this.productPrice;
    }

    public void setDiscount(double percentage) {
        this.productPrice = (int) (productBasePrice * percentage);
    }



    @Override
    public String toString() {
        if (productBasePrice == -1)
            return String.format("%s |  %s:- | %s Pcs| %s", getProductName(), getProductPrice(), getProductQuantity(), getProductID());
        else if (productPrice != productBasePrice)
            return String.format("%s | %s:- NOW: %s:- | %s  In stock | %s", getProductName(), getProductBasePrice(), getProductPrice(), getProductQuantity(), getProductID());
        else
            return String.format("%s | %s:- | %s In stock | %s", getProductName(), getProductPrice(), getProductQuantity(), getProductID());
    }

    public void updateProduct() {
        System.out.println("UPDATE" + this.productName);
        deleteFromDatabase();
        saveToDatabase();
    }

    public Product newUserProduct(int quantity) {
        try {
            Product product = (Product) this.clone();
            product.setProductQuantity(quantity);
            product.setProductBasePrice(-1);

            return product;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

}