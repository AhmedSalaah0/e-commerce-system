package Entities;

import java.util.ArrayList;

public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private double balance;
    private Cart cart;
    private ArrayList<Invoice> invoices;

    public Customer(String name, String email, String phoneNumber, String address, double balance, Cart cart, ArrayList<Invoice> invoices) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.balance = balance;
        this.cart = cart;
        this.invoices = invoices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public Cart getCart() {
        return cart;
    }

}
