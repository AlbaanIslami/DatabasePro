package model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Order cart = new Order(this.getUserName());
    private Set<Order> orderHistory = new HashSet<>();

    private String userName;
    private String firstName;
    private String password;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String country;
    private String phone;
    private Roles role;
    public static String aName = "ADMIN";
    public static String aPass = " ";


    public User() {
        userName = aName;
        firstName = "admin";
        password = aPass;
        lastName = "admin";
        email = "admin";
        address = "admin";
        city = "admin";
        country = "admin";
        phone = "admin";
        role = Roles.Admin;
    }

    public User(String userName, String firstName, String lastName, String country, String city, String address, String password, String email, String phone) {
        this.userName = userName;
        this.firstName = firstName;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.role = Roles.User;
    }

    public void loadOrderHistory() {

    }

    public Set<Order> getOrderHistory() {
        return orderHistory;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Order getCart() {
        return cart;
    }

    public void setCart(Order cart) {
        this.cart = cart;
    }

    public void setOrderHistory(Set<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }


    @Override
    public String toString() {
        return "User{" +
                "userName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }


}
