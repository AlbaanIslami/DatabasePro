package model;


import controller.DBController;

public class Supplier {
    private String supplierName;
    private String supplierPhone;
    private String supplierAddress;


    public Supplier(String supplierName, String supplierAddress, String supplierPhone) {
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public void saveToDatabase() {
        DBController.getInstance().executeQuery(String.format("INSERT INTO suppliers values ('%s', '%s','%s')", getSupplierName(), getSupplierPhone(), getSupplierAddress()));
    }

    @Override
    public String toString() {
        return
                supplierName;

    }
}
