package Entities;

import java.time.LocalDate;
import Entities.Enums.ProductType;
import Entities.Enums.ExpirationType;

public class Product {
    private String productName;
    private double price;
    private int quantity;
    private ProductType productType;
    private ExpirationType expirationStatus;
    private LocalDate expirationDate;
    private double weight;

    public Product(String productName, double price, int quantity, ProductType productType, ExpirationType expirationStatus,
                   LocalDate expirationDate, double weight) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.productType = productType;
        this.expirationStatus = expirationStatus;
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }
    public double getWeight() {
        return weight;
    }
    
    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public boolean isShippable() {
        return this.productType != ProductType.DIGITAL_PRODUCTS;
    }
    
    public boolean isExpired() {
        if (this.expirationStatus == ExpirationType.EXPIRABLE) {
            return LocalDate.now().isAfter(this.expirationDate);
        }
        return false;
    }
}
