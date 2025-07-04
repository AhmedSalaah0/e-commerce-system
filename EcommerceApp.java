import java.time.LocalDate;
import java.util.ArrayList;

import Entities.*;
import Entities.Enums.*;
import ServiceContracts.*;
import Services.*;

public class EcommerceApp {
    ArrayList<Product> products = new ArrayList<>();
    private Customer customer;
    private ICartService cartService;
    private ICheckoutService checkoutService;

    public EcommerceApp() {
        this.customer = new Customer("John Doe", "john@example.com", "123456789", "123 Main St", 10000, new Cart(), new ArrayList<>());
        this.cartService = new CartService(customer);
        this.checkoutService = new CheckoutService();
        loadProducts();
    }

    private void loadProducts() {
        products.add(new Product("Laptop", 1500.0, 5, ProductType.ELECTRONICS, ExpirationType.NON_EXPIRABLE, null, 2500.0));
        products.add(new Product("TV", 1000.0, 3, ProductType.ELECTRONICS, ExpirationType.NON_EXPIRABLE, null, 3000.0));
        products.add(new Product("Cheese", 100.0, 10, ProductType.GROCERIES, ExpirationType.EXPIRABLE, LocalDate.of(2025, 10, 31), 200.0));
        products.add(new Product("Coupon", 50.0, 1, ProductType.DIGITAL_PRODUCTS, ExpirationType.NON_EXPIRABLE, null, 0.0));
        products.add(new Product("Biscuits", 150.0, 20, ProductType.GROCERIES, ExpirationType.EXPIRABLE, LocalDate.of(2026, 12, 31), 700.0));
    }

    public void start() {
         System.out.println("Welcome to the E-commerce System!");

        boolean showProductsList = true;
        while (true) {
            if (showProductsList) {
                showProducts();
                selectItem();
            }
            int action = Actions();
            Cart cart = customer.getCart();

            if (action < 1 || action > 6) {
                System.out.println("Invalid action selection.");
                return;
            } else if (action == 1) {
                showProductsList = false;
                System.out.println("Your Cart:");
                for (CartItem item : cart.getItems()) {
                    System.out.println(item.getQuantity() + "x " + item.getProduct().getProductName() + " - Price: " + (item.getProduct().getPrice() * item.getQuantity()));
                }
                System.out.println("Total Price: " + cart.getTotalPrice());
            } else if (action == 2) {
                showProductsList = false;
                checkoutService.processCheckout(customer, cart);
            } else if (action == 3) {
                showProductsList = false;
                System.out.println("Enter invoice number to cancel:");
                String invoiceNumber = System.console().readLine();
                var invoices = customer.getInvoices();
                if (invoices.isEmpty()) {
                    System.out.println("No invoices found.");
                }
                else
                {
                for (Invoice invoice : invoices) {
                    if (invoice.getInvoiceNumber().equals(invoiceNumber)) {
                        checkoutService.cancelOrder(invoice);
                        System.out.println("Order cancelled successfully.");
                    }
                }
                }
            } else if (action == 4) {
                showProductsList = false;
                checkoutService.showAllInvoices(customer);
            }
            else if (action == 5){
                showProductsList = true;
            }
            else if (action == 6) {
                System.out.println("Thank you for using the E-commerce System!");
                break;
            }
        }
    }
    private void showProducts()
    {
        System.out.println("Available Products:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getProductName() + " - Price: " + product.getPrice() + " - Quantity: " + product.getQuantity());
        }
    }
    
    private void selectItem() {
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

    private int Actions()
    {
         System.out.println("\n** Select an action: **\n");
            System.out.println("1. View Cart");
            System.out.println("2. Checkout");
            System.out.println("3. Cancel Order");
            System.out.println("4. Show All Invoices");
            System.out.println("5. Add More Items to Cart");
            System.out.println("6. Exit");
            int action = Integer.parseInt(System.console().readLine());
            return action;
    }
}