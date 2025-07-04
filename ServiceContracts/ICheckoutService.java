package ServiceContracts;

import Entities.Customer;
import Entities.Quotation;
import Entities.Cart;
import Entities.Invoice;
public interface ICheckoutService {
    void processCheckout(Customer customer, Cart cart);
    public Quotation calculateTotalPrice(Cart cart);
    void cancelOrder(Invoice invoice);
}
