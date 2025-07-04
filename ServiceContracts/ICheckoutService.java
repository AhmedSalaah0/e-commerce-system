package ServiceContracts;

import Entities.Customer;
import Entities.Quotation;
import Entities.Cart;
import Entities.Invoice;
public interface ICheckoutService {
    Invoice processCheckout(Customer customer, Cart cart);
    Quotation calculateTotalPrice(Cart cart);
    void cancelOrder(Invoice invoice);
    void showAllInvoices(Customer customer);
}
