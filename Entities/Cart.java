package Entities;
import java.util.ArrayList;
import Entities.CartItem;

public class Cart {
    ArrayList<CartItem> items;
    double totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }
    
    public ArrayList<CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
