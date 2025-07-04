import java.time.LocalDate;
import java.util.ArrayList;
import Entities.Customer;
import Entities.Cart;
import Entities.CartItem;
import Entities.Product;
import Entities.Enums.ProductType;
import Entities.Enums.ExpirationType;
import ServiceContracts.*;
import Services.*;
import Entities.Invoice;

public class Main {
    public static void main(String[] args){
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1500.0, 5, ProductType.ELECTRONICS, ExpirationType.NON_EXPIRABLE, null, 2500.0));
        products.add(new Product("TV", 1000.0, 3, ProductType.ELECTRONICS, ExpirationType.NON_EXPIRABLE, null, 3000.0));
        products.add(new Product("Cheese", 100.0, 10, ProductType.GROCERIES, ExpirationType.EXPIRABLE, LocalDate.of(2025, 10, 31), 200.0));
        products.add(new Product("Coupon", 50.0, 1, ProductType.DIGITAL_PRODUCTS, ExpirationType.NON_EXPIRABLE, null, 0.0));
        products.add(new Product("Biscuits", 150.0, 20, ProductType.GROCERIES, ExpirationType.EXPIRABLE, LocalDate.of(2026, 12, 31), 700.0));
        Customer customer = new Customer("John Doe", "john@example.com", "123456789", "123 Main St", 10000, new Cart(), new ArrayList<>());
        ICartService cartService = new CartService(customer);
        ICheckoutService checkoutService = new CheckoutService();
        boolean showProducts = true;


        System.out.println("Welcome to the E-commerce System!");

        while (true) {
        if (showProducts){    
        System.out.println("Available Products:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getProductName() + " - Price: " + product.getPrice() + " - Quantity: " + product.getQuantity());
        }
        System.out.println("Please select a product to add to your cart.");
        int productIndex = Integer.parseInt(System.console().readLine()) - 1;

        System.out.println("Please enter the quantity:");
        int quantity = Integer.parseInt(System.console().readLine());

        if (productIndex < 0 || productIndex >= products.size()) {
            System.out.println("Invalid product selection.");
            return;
        }
        
        Product selectedProduct = products.get(productIndex);


        try {
            cartService.addItemToCart(selectedProduct, quantity);
            System.out.println("Item added to cart: " + selectedProduct.getProductName() + " - Quantity: " + quantity);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
        Cart cart = customer.getCart();
            
            System.out.println("\n** Select an action: **\n");
            System.out.println("1. View Cart");
            System.out.println("2. Checkout");
            System.out.println("3. Cancel Order");
            System.out.println("4. Show All Invoices");
            System.out.println("5. Add More Items to Cart");
            System.out.println("6. Exit");
            int action = Integer.parseInt(System.console().readLine());

            if (action < 1 || action > 6) {
                System.out.println("Invalid action selection.");
                return;
            } else if (action == 1) {
                showProducts = false;
                System.out.println("Your Cart:");
                for (CartItem item : cart.getItems()) {
                    System.out.println(item.getQuantity() + "x " + item.getProduct().getProductName() + " - Price: " + (item.getProduct().getPrice() * item.getQuantity()));
                }
                System.out.println("Total Price: " + cart.getTotalPrice());
            } else if (action == 2) {
                showProducts = false;
                checkoutService.processCheckout(customer, cart);
            } else if (action == 3) {
                showProducts = false;
                System.out.println("Enter invoice number to cancel:");
                String invoiceNumber = System.console().readLine();
                var invoices = customer.getInvoices();
                if (invoices.isEmpty()) {
                    System.out.println("No invoices found.");
                    return;
                }
                for (Invoice invoice : invoices) {
                    if (invoice.getInvoiceNumber().equals(invoiceNumber)) {
                        checkoutService.cancelOrder(invoice);
                        System.out.println("Order cancelled successfully.");
                        return;
                    }
                }
            } else if (action == 4) {
                showProducts = false;
                checkoutService.showAllInvoices(customer);
            }
            else if (action == 5){
                showProducts = true;
            }
            else if (action == 6) {
                System.out.println("Thank you for using the E-commerce System!");
                break;
            }
        }    
    }
}