package ServiceContracts;
import Entities.CartItem;
import Entities.Product;

public interface ICartService {
    void addItemToCart(Product product, int quantity);
    void removeItemFromCart(CartItem cartItem);
    double getTotalPrice();
}
