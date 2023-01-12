package controller;

import model.*;
import view.admin.AdminFrames.*;
import view.main.MainFrame;
import view.main.MainPanel;
import view.user.CreateAccountFrame;
import view.user.OrderHistoryFrame;
import view.user.ShoppingCartFrame;

import javax.swing.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class Controller {

    private DBController dbController;
    protected static Controller instance;

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    private MainFrame mainFrame;
    private MainPanel mainPanel;
    private CreateAccountFrame createAccountFrame;
    private AddDiscountFrame addDiscountFrame;
    private AddSupplierFrame addSupplierFrame;
    private AddProductFrame addProductFrame;
    private DeleteProductFrame deleteProductFrame;
    private HandleProductFrame handleProductFrame;
    private HandleOrdersFrame handleOrdersFrame;
    private ViewUsedDiscountsFrame viewUsedDiscountsFrame;

    private ViewMaximumOrdersFrame viewMaximumOrdersFrame;
    private ShoppingCartFrame shoppingCartFrame;

    private Set<Supplier> suppliers;
    private Set<Product> products;
    private Set<Discount> discounts;
    private Set<String> discountHistory;
    private Set<String> maximumOrders;


    private Set<Order> orders;

    private User user;

    public Controller() {
        suppliers = new HashSet<>();
        products = new HashSet<>();
        discounts = new HashSet<>();
        orders = new HashSet<>();
        discountHistory = new HashSet<>();
        maximumOrders = new HashSet<>();
    }


    public void setUp() throws SQLException {
        dbController = DBController.getInstance();
        suppliers.addAll(dbController.fetchAllSuppliers());
        products.addAll(dbController.fetchAllProducts());
        orders.addAll(dbController.fetchAllOrders());
        discounts.addAll(dbController.fetchAllDiscounts());
        discountHistory.addAll(dbController.fetchDiscountHistory());

        maximumOrders.addAll(dbController.fetchMaximumOrder());

        mainFrame = new MainFrame(this);
        this.mainPanel = mainFrame.getMainPanel();
        applyDiscounts();
    }

    public void applyDiscounts() {
        for (Discount discount : discounts) {
            discount.applyDiscount();
            System.out.println(discount);
        }
    }

    public Product getProductById(String id) {
        for (Product product : products) {
            if (product.getProductID().equals(id))
                return product;
        }
        return null;
    }

    public void createNormalUser(User regularUser) {
        user = regularUser;
        dbController.createNormalUser(regularUser);
        createAccountFrame.dispose();
    }

    public void loginUser(User user) {
        this.user = user;
        System.out.println(orders.size());
        if (user.getRole() != Roles.Admin) {
            for (Order order : orders) {
                if (order.getOrderPlacer().equalsIgnoreCase(user.getUserName())) {
                    user.getOrderHistory().add(order);
                }
            }
        }
        openUserView();
    }

    public void openUserView() {
        if (user.getRole() == Roles.User)
            mainPanel.updateUserView();
        else mainPanel.updateAdminView();
        updateProductList();
    }


    public boolean checkQuantity(int nbrOfItems, int productID) {
        return dbController.checkQuantity(nbrOfItems, productID);
    }

    public void updateProductList() {
        if (user == null)
            mainPanel.getPnlStore().getListProducts().setListData(getProducts().toArray(new Product[0]));
        else if (user.getRole() == Roles.Admin)
            mainPanel.getPnlAdminMain().getAdminStorePanel().getListProducts().setListData(getProducts().toArray(new Product[0]));
        else
            mainPanel.getPnlUserMain().getUserStorePanel().getListProducts().setListData(getProducts().toArray(new Product[0]));

    }


    public Product getProduct(String id) {
        for (Product product : products) {
            if (product.getProductID().equalsIgnoreCase(id))
                return product;
        }
        return null;
    }

    public void openCreateAccountWindow() {
        createAccountFrame = new CreateAccountFrame(this, dbController);
    }

    public void openAddSupplierFrame() {
        addSupplierFrame = new AddSupplierFrame(this, dbController);
    }

    public void openAddProductFrame() {
        addProductFrame = new AddProductFrame(this, dbController);
    }

    public void openAddDiscountFrame() {
        addDiscountFrame = new AddDiscountFrame(this, dbController);
    }

    public void openViewMaximumOrdersFrame() {
        viewMaximumOrdersFrame = new ViewMaximumOrdersFrame(this);
    }

    public void openDeleteProductFrame() {
        deleteProductFrame = new DeleteProductFrame(this, dbController);
    }

    public void openHandleProductFrame() {
        handleProductFrame = new HandleProductFrame(this, dbController);
    }

    public void openHandleOrdersFrame() {
        handleOrdersFrame = new HandleOrdersFrame(this, dbController);
    }

    public void openViewUsedDiscountsFrame() {
        viewUsedDiscountsFrame = new ViewUsedDiscountsFrame(this);
    }

    public void openShoppingCart() {
        shoppingCartFrame = new ShoppingCartFrame(this);
    }

    public void openOrderHistory() {
        new OrderHistoryFrame(this);
    }

    public controller.DBController getDbController() {
        return dbController;
    }

    public void setDbController(controller.DBController dbController) {
        this.dbController = dbController;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public CreateAccountFrame getCreateAccountFrame() {
        return createAccountFrame;
    }

    public void setCreateAccountFrame(CreateAccountFrame createAccountFrame) {
        this.createAccountFrame = createAccountFrame;
    }

    public AddDiscountFrame getAddDiscountFrame() {
        return addDiscountFrame;
    }

    public void setAddDiscountFrame(AddDiscountFrame addDiscountFrame) {
        this.addDiscountFrame = addDiscountFrame;
    }

    public AddSupplierFrame getAddSupplierFrame() {
        return addSupplierFrame;
    }

    public void setAddSupplierFrame(AddSupplierFrame addSupplierFrame) {
        this.addSupplierFrame = addSupplierFrame;
    }

    public AddProductFrame getAddProductFrame() {
        return addProductFrame;
    }

    public void setAddProductFrame(AddProductFrame addProductFrame) {
        this.addProductFrame = addProductFrame;
    }

    public DeleteProductFrame getDeleteProductFrame() {
        return deleteProductFrame;
    }

    public void setDeleteProductFrame(DeleteProductFrame deleteProductFrame) {
        this.deleteProductFrame = deleteProductFrame;
    }

    public HandleProductFrame getHandleProductFrame() {
        return handleProductFrame;
    }

    public void setHandleProductFrame(HandleProductFrame handleProductFrame) {
        this.handleProductFrame = handleProductFrame;
    }

    public HandleOrdersFrame getHandleOrdersFrame() {
        return handleOrdersFrame;
    }

    public void setHandleOrdersFrame(HandleOrdersFrame handleOrdersFrame) {
        this.handleOrdersFrame = handleOrdersFrame;
    }

    public ViewUsedDiscountsFrame getViewUsedDiscountsFrame() {
        return viewUsedDiscountsFrame;
    }

    public void setViewUsedDiscountsFrame(ViewUsedDiscountsFrame viewUsedDiscountsFrame) {
        this.viewUsedDiscountsFrame = viewUsedDiscountsFrame;
    }

    public ShoppingCartFrame getShoppingCartFrame() {
        return shoppingCartFrame;
    }

    public void setShoppingCartFrame(ShoppingCartFrame shoppingCartFrame) {
        this.shoppingCartFrame = shoppingCartFrame;
    }

    private void updateView() {
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Set<Discount> discounts) {
        this.discounts = discounts;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Countries[] getCountries() {
        return Countries.values();
    }

    public boolean newSupplier(String supplierName, String supplierAddress, String supplierPhone) {
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierName().equalsIgnoreCase(supplierName)) {
                System.out.println(supplier);
                System.out.println(supplierName);
                return false;
            }
        }
        Supplier supplier = new Supplier(supplierName, supplierAddress, supplierPhone);
        supplier.saveToDatabase();
        suppliers.add(supplier);

        return true;
    }

    public boolean newDiscount(String code, int percentage, String reason, Date startDate, Date endDate) {
        for (Discount discount : discounts) {
            if (discount.getDiscountCode().equalsIgnoreCase(code)) {
                return false;
            }
        }
        Discount discount = new Discount(code, percentage, reason, startDate, endDate);
        discount.saveToDatabase();
        discounts.add(discount);
        return true;
    }

    public boolean newProduct(String productName, int productQuantity, int productPrice, String productSupplier, String productID) {
        for (Product product : products) {
            if (product.getProductID().equalsIgnoreCase(productID)) {
                return false;
            }
        }
        Product product = new Product(productName, productQuantity, productPrice, productSupplier, productID, 0);
        product.saveToDatabase();
        products.add(product);
        return true;
    }

    public boolean deleteProduct(Product selectedItem) {
        for (Order order : orders) {
            for (Product item : order.getItems()) {
                if (item.getProductID().equals(selectedItem.getProductID()))
                    return false;
            }
        }
        products.remove(selectedItem);
        ((Product) selectedItem).deleteFromDatabase();
        updateProductList();

        return true;
    }

    public void updateQuantityForProduct(Product product, int quantity) {
        product.setProductQuantity(quantity);
        product.updateProduct();
        updateProductList();
    }

    public void placeOrder() {
        if (user.getCart().getItems().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You dont have any products in your Cart.");
            return;
        }
        user.getCart().setStatus(OrderStatus.CREATED);
        user.getCart().setOrderPlacer(user.getUserName());
        for (Product p : user.getCart().getItems()) {
            for (Product product : Controller.getInstance().getProducts()) {
                if (p.getProductID().equals(product.getProductID())) {
                    product.setProductQuantity(product.getProductQuantity() - p.getProductQuantity());
                    product.updateProduct();
                }

            }
        }
        JOptionPane.showMessageDialog(null, "Order placed.");
        user.getOrderHistory().add(user.getCart());
        user.getCart().saveToDatabase();

        user.setCart(new Order(user.getUserName()));
        updateProductList();

    }

    public Set<String> getDiscountHistory() {
        return discountHistory;
    }

    public Set<String> getMaximumOrders() {
        return maximumOrders;
    }

    public void setDiscountHistory(Set<String> discountHistory) {
        this.discountHistory = discountHistory;
    }

    public void setMaximumOrders(Set<String> maximumOrders) {
        this.maximumOrders = maximumOrders;
    }

    public void fetchStoreData() {
        products.clear();
        products.addAll(DBController.getInstance().fetchAllProducts());
        applyDiscounts();
        updateProductList();
    }

    public void productSearch(String text) {
        List<Product> val = new ArrayList<>();
        boolean isNumber;
        int textNum = 0;
        try{
            textNum = Integer.parseInt(text);
            isNumber = true;
        }catch (NumberFormatException exception){
            isNumber = false;
        }

        if (!text.isEmpty()) {
            if(isNumber){


                for (Product p : products) {
                    if (p.getProductPrice() <= textNum)
                        val.add(p);
                }
            } else if (text.equalsIgnoreCase("discount")) {
                products.forEach(p -> {
                    if (p.hasDiscount())
                        val.add(p);
                });
            } else {
                products.forEach(p -> {
                    if (p.getProductID().startsWith(text) || p.getProductName().startsWith(text) || p.getProductSupplier().startsWith(text)) {
                        val.add(p);
                    }
                });
            }
        } else
            val.addAll(products);

        if (user == null)
            mainPanel.getPnlStore().getListProducts().setListData(val.toArray(new Product[0]));
        else if (user.getRole() == Roles.Admin)
            mainPanel.getPnlAdminMain().getAdminStorePanel().getListProducts().setListData(val.toArray(new Product[0]));
        else mainPanel.getPnlUserMain().getUserStorePanel().getListProducts().setListData(val.toArray(new Product[0]));

    }

}
