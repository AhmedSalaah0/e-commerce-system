import java.time.LocalDate;
import java.util.ArrayList;
import Entities.Customer;
import Entities.Cart;
import Entities.Product;
import Entities.Enums.ProductType;
import Entities.Enums.ExpirationType;
import ServiceContracts.*;
import Services.*;

public class Main {
    public static void main(String[] args){
        Product Laptop = new Product("Laptop", 1500.0, 5, ProductType.ELECTRONICS, ExpirationType.NON_EXPIRABLE, null, 2500.0);
        Product TV = new Product("TV", 1000.0, 3, ProductType.ELECTRONICS, ExpirationType.NON_EXPIRABLE, null, 3000.0);
        Product Cheese = new Product("Cheese", 100.0, 10, ProductType.GROCERIES, ExpirationType.EXPIRABLE, LocalDate.of(2025, 10, 31), 200.0);
        Product coupon = new Product("Coupon", 50.0, 1, ProductType.DIGITAL_PRODUCTS, ExpirationType.NON_EXPIRABLE, null, 0.0);
        Product Biscuits = new Product("Biscuits", 150.0, 20, ProductType.GROCERIES, ExpirationType.EXPIRABLE, LocalDate.of(2026, 12, 31), 700.0);

        Customer customer = new Customer("John Doe", "john@example.com", "123456789", "123 Main St", 1000, new Cart(), new ArrayList<>());
        Cart cart = customer.getCart();

        ICartService cartService = new CartService(customer);
        ICheckoutService checkoutService = new CheckoutService();

        try {
            cartService.addItemToCart(Cheese, 2);
            cartService.addItemToCart(Biscuits, 1);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        checkoutService.processCheckout(customer, cart);
        
    }    
}