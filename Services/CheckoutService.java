package Services;

import ServiceContracts.ICheckoutService;
import ServiceContracts.Helpers.InvoiceNumberGenerator;
import Entities.Customer;
import Entities.Invoice;
import Entities.Quotation;

import java.util.ArrayList;
import java.time.LocalDate;

import Entities.Cart;
import Entities.CartItem;

public class CheckoutService implements ICheckoutService
{

    public Invoice processCheckout(Customer customer, Cart cart) {
        if (customer == null || cart == null) {
            throw new IllegalArgumentException("Customer and cart cannot be null.");
        }
        if (cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty.");
        }

        if (customer.getBalance() < cart.getTotalPrice()) {
            throw new IllegalArgumentException("no enough balance.");
        }

        Quotation quotation = calculateTotalPrice(cart);
        Invoice invoice = new Invoice(InvoiceNumberGenerator.generateInvoiceNumber(), customer, LocalDate.now().toString(), quotation);
        customer.setBalance(customer.getBalance() - cart.getTotalPrice());
        customer.getInvoices().add(invoice);


        System.out.println("\nCheckout successful. invoice number " + invoice.getInvoiceNumber() + "\n\n" +
        "** Shipment notice **");
        ArrayList<CartItem> UnShippableItems = new ArrayList<>();
        int totalWeight = 0;
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().isShippable()) {
                System.out.println(item.getQuantity() + "x" + item.getProduct().getProductName() + 
                " (Weight: " + item.getProduct().getWeight() * item.getQuantity() + "g)");
                totalWeight += item.getProduct().getWeight() * item.getQuantity();
            } else {
                UnShippableItems.add(item);
            }
        }
        System.out.println("Total package weight: " + totalWeight + "g");
        if (UnShippableItems.size() > 0) {
        System.out.println("Unshippable Items: ");
        for (CartItem item : UnShippableItems) {
            System.out.println(item.getQuantity() + "x" + item.getProduct().getProductName());
        }
    }
        System.out.println("\n** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x" + item.getProduct().getProductName() + 
            " - Price: " + item.getProduct().getPrice() * item.getQuantity());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal\t\t" + quotation.getSubtotal());
        System.out.println("Shipping\t\t" + quotation.getShippingCost());
        System.out.println("Amount\t\t\t" + (quotation.getSubtotal() + quotation.getShippingCost()));

        cart.getItems().clear();
        return invoice;
    }

    public Quotation calculateTotalPrice(Cart cart) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null.");
        }
        double subtotal = cart.getTotalPrice();
        double shippingCost = 0.0;
        for (CartItem item : cart.getItems()) {
            double totalWeight = item.getProduct().getWeight() * item.getQuantity();
            shippingCost += totalWeight * 0.1;
        }
        
        return new Quotation(subtotal, shippingCost);
    }
    public void cancelOrder(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice cannot be null.");
        }
        Customer customer = invoice.getCustomer();
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        customer.setBalance(customer.getBalance() + invoice.getSubtotal());
        customer.getInvoices().remove(invoice);
    }

    public void showAllInvoices(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        if (customer.getInvoices().isEmpty()) {
            System.out.println("No invoices found for this customer.");
            return;
        }
        System.out.println("Invoices for " + customer.getName() + ":");
        for (Invoice invoice : customer.getInvoices()) {
            System.out.println("Invoice Number: " + invoice.getInvoiceNumber() + ", Date: " + invoice.getDate() + ", Total: " + invoice.getSubtotal());
        }
    }

}
