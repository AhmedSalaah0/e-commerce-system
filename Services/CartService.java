package Services;
import Entities.CartItem;
import Entities.Customer;
import Entities.Product;
import ServiceContracts.ICartService;
import Entities.Cart;

public class CartService implements ICartService {
    private final Customer customer;

    public CartService(Customer customer) {
        this.customer = customer;
    }

    public void addItemToCart(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid product or quantity.");
        }
        if (product.isExpired()) {
            throw new IllegalArgumentException("Product is expired and cannot be added to the cart.");
        }

        if (product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            CartItem cartItem = new CartItem(product, quantity);
            customer.getCart().getItems().add(cartItem);
            customer.getCart().setTotalPrice(customer.getCart().getTotalPrice() + (product.getPrice() * quantity));
        } else {
            throw new IllegalArgumentException("item is out of stock");
        }
    }

    public void removeItemFromCart(CartItem cartItem) {
        if (customer.getCart().getItems().contains(cartItem)) {
            Product product = cartItem.getProduct();
            customer.getCart().getItems().remove(cartItem);
            product.setQuantity(product.getQuantity() + cartItem.getQuantity());
        } else {
            throw new IllegalArgumentException("Product not found in cart.");
        }
    }

    public double getTotalPrice() {
        return customer.getCart().getTotalPrice();
    }
}
