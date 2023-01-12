package controller;

import model.*;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class DBController {
    private static DBController instance;
    private static String DB_URL = "jdbc:postgresql://pgserver.mau.se:5432/online_storeal0794";
    private static String user = "al0794";
    private static String password = "utiaqxwh";
    private static Connection connection;

    private Date date = new Date(System.currentTimeMillis());

    public DBController() {
        connect();
    }



    public boolean checkDate(){
        ResultSet set = executeQuery("SELECT * FROM settings");
        try {
            set.next();
            long val = set.getLong("date");
            Date then = new Date(val);
            return then.toLocalDate().getMonthValue() == date.toLocalDate().getMonthValue();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public void updateDate(){
        executeQuery("UPDATE settings SET date = " + date.getTime());
    }

    public Set<Order> fetchAllOrders() {
        Set<Order> val = new HashSet<>();
        ResultSet set = executeQuery("SELECT * FROM orders;");
        try {
            while (set.next()) {
                val.add(new Order(
                        set.getString("orderId"),
                        set.getString("orderContent"),
                        OrderStatus.valueOf(set.getString("confirmation")),
                        set.getString("username")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return val;
    }

    public Set<Product> fetchAllProducts() {
        Set<Product> val = new HashSet<>();
        ResultSet set = executeQuery("SELECT * FROM products;");
        try {
            while (set.next()) {
                val.add(
                        new Product(
                                set.getString("pName"),
                                set.getInt("stockTotal"),
                                set.getInt("basePrice"),
                                set.getString("supplier"),
                                set.getString("pCode"),
                                checkDate() ? set.getInt("orderTimes") : 0
                        ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!checkDate()){
            val.forEach(Product::updateProduct);
            updateDate();
        }

        return val;
    }

    public Set<Supplier> fetchAllSuppliers() {
        Set<Supplier> val = new HashSet<>();
        ResultSet set = executeQuery("SELECT * FROM suppliers;");
        try {
            while (set.next()) {
                val.add(new Supplier(set.getString("name"), set.getString("address"), set.getString("tel")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return val;
    }

    public Set<Discount> fetchAllDiscounts() {
        Set<Discount> val = new HashSet<>();
        ResultSet set = executeQuery("SELECT * FROM discount;");
        try {
            while (set.next()) {
                Discount d = new Discount(
                        set.getString("code"),
                        set.getInt("percentage"),
                        set.getString("description"),
                        set.getString("products"),
                        set.getDate("startDate"),
                        set.getDate("endDate")
                );
                //boolean removed = d.expiredDate();
                //if (!removed) {
                val.add(d);
                //}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return val;
    }

    public static DBController getInstance() {
        if (instance == null) {
            instance = new DBController();
        }
        return instance;
    }

    public static Connection connect() {
        connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("Connection to database successful.");
        } catch (SQLException e) {
            System.out.println(e);

        }
        return connection;
    }


    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Failed to disconnect from database.");
        }
    }


    public boolean nameIsTaken(String userName) {
        ResultSet set = executeQuery("SELECT * FROM customers where username='" + userName + "'");
        try {
            return set.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public void createNormalUser(User user) {
        executeQuery("INSERT INTO customers values ('" +
                user.getUserName() + "','" +
                user.getFirstName() + "','" +
                user.getLastName() + "','" +
                user.getCountry() + "','" +
                user.getCity() + "','" +
                user.getAddress() + "','" +
                user.getPassword() + "','" +
                user.getEmail() + "','" +
                user.getPhone() + "')");
    }

    public boolean addDiscount(Discount discount) {
        return true;
    }

    public void addSupplier(Supplier supplier) {
        executeQuery("INSERT INTO suppliers values ('" +
                supplier.getSupplierName() + "','" +
                supplier.getSupplierPhone() + "','" +
                supplier.getSupplierAddress() + "')");
    }


    public boolean addProduct(Product product) {
        return true;
    }

    public boolean deleteProduct(String productNameToDelete) {
        return true;
    }

    public User authenticateUser(String username, String password) {
        try {

            User user;
            ResultSet set = executeQuery("select * from customers where username='" + username + "'");
            set.next();
            if (!password.equals(set.getString("pass"))) {
                return null;
            }

            return new User(set.getString("username"),
                    set.getString("firstname"),
                    set.getString("lastname"),
                    set.getString("country"),
                    set.getString("city"),
                    set.getString("adress"),
                    set.getString("pass"),
                    set.getString("email"),
                    set.getString("phonenr"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void logDiscount(Product product, Discount discount) {
        executeQuery(String.format("INSERT INTO discount_history values ('Product %s - Product code %s - Discount %s at %s%s - Final price %s - Start date %s - End date %s')",
                product.getProductName(), product.getProductID() ,discount.getDiscountReason(), discount.getDiscountPercentage(), "%",product.getProductPrice(),discount.getStartDate(), discount.getEndDate()));
    }





    public ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            return set;
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the query: " + e.getMessage());

        }
        return null;
    }

    public boolean updateQuantity(int newQuantity, String productNameToUpdate) {
        return true;
    }

    public boolean AddDiscountUnusedPeriod(String startDate, String endDate, String productNameToUpdate, String discountToSetDate) {
        return true;
    }



    public boolean checkQuantity(int nbrOfItems, int productID) {
        return false;
    }

    public Set<String> fetchDiscountHistory() {
        Set<String> val = new HashSet<>();
        ResultSet set = executeQuery("SELECT * FROM discount_history;");
        try {
            while (set.next()) {
                val.add(set.getString("message"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return val;

    }

    public Set<String> fetchMaximumOrder()
    {
        Set<String> val = new HashSet<>();
        ResultSet set = executeQuery(
                "SELECT pName, ordertimes from products\n" +
                        "where ordertimes != 0\n" +
                        "order by ordertimes desc;");
        try {
            while (set.next()) {
                String productName = set.getString("pName");
                int orderTimes = set.getInt("ordertimes");
                String productString = productName + ": " + orderTimes;
                val.add(productString);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }
}
