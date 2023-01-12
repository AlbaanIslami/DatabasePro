package model;

import controller.Controller;
import controller.DBController;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Discount {
    private String discountCode;
    private int discountPercentage;
    private String discountReason;
    private Set<Product> discountedItems;
    private Date startDate;
    private Date endDate;

    private SimpleDateFormat formatted = new SimpleDateFormat("yyyy-mm-dd");

    public Discount(String discountCode, int discountPercentage, String discountReason, Date startDate, Date endDate) {
        this.discountCode = discountCode;
        this.discountPercentage = discountPercentage;
        this.discountReason = discountReason;
        this.startDate = startDate;
        this.endDate = endDate;
        discountedItems = new HashSet<>();
    }


    public Discount(String discountCode, int discountPercentage, String discountReason, String items, Date startDate, Date endDate) {
        this.discountCode = discountCode;
        this.discountPercentage = discountPercentage;
        this.discountReason = discountReason;
        this.startDate = startDate;
        this.endDate = endDate;
        discountedItems = deCodeProducts(items);
    }


    public Date getStartDate() {

        return startDate;
    }

    public Date getEndDate() {

        return endDate;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getDiscountReason() {
        return discountReason;
    }

    public void setDiscountReason(String discountReason) {
        this.discountReason = discountReason;
    }

    public Set<Product> getDiscountedItems() {
        return discountedItems;
    }

    public void setDiscountedItems(Set<Product> discountedItems) {
        this.discountedItems = discountedItems;
    }

    private String codeProducts() {
        if (getDiscountedItems().isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        for (Product discountedItem : discountedItems) {
            if (discountedItem == null)
                continue;
            sb.append(discountedItem.getProductID()).append(":"); // item1:item2:item3:item4
        }
        return sb.substring(0, sb.length() - 1);
    }

    private Set<Product> deCodeProducts(String coded) { // item1:item2:item3:item4 -> [item1,item2,item3,item4]
        if (coded == null || coded.isEmpty())
            return new HashSet<>();
        Set<Product> val = new HashSet<>();
        if (!coded.contains(":")) {
            val.add(Controller.getInstance().getProduct(coded));
            return val;
        }
        for (String s : coded.split(":")) {
            Product p = Controller.getInstance().getProduct(s);
            if (p == null)
                continue;
            val.add(p);
        }
        return val;
    }


    public void deleteFromDatabase() {

        DBController.getInstance().executeQuery("BEGIN;");
        DBController.getInstance().executeQuery(String.format("DELETE FROM discount WHERE code='%s'", discountCode));
        DBController.getInstance().executeQuery("COMMIT;");
    }

    public void saveToDatabase() {

        DBController.getInstance().executeQuery(String.format("INSERT INTO discount values ('%s', '%s','%s', '%s', '%s','%s')", discountCode, discountPercentage, discountReason, codeProducts(), startDate, endDate));
    }

    public void applyDiscount() {
        double percentage = 1 - (discountPercentage / 100.0);
        for (Product discountedItem : discountedItems) {
            if (discountedItem == null)
                continue;
            Product ni = Controller.getInstance().getProductById(discountedItem.getProductID());
            if (ni == null)
                continue;
            ni.setDiscount(percentage);
        }
        Controller.getInstance().updateProductList();
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s", getDiscountReason(), getDiscountPercentage(), startDate, endDate);
    }

    public void addDiscount(Product product) {
        discountedItems.add(product);
        applyDiscount();
        deleteFromDatabase();
        saveToDatabase();
        DBController.getInstance().logDiscount(product, this);


    }


}
