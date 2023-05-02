package cart;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class ShoppingCartTest {

    ShoppingCart shoppingCart = new ShoppingCart();

    @Test
    public void addToCartTest() {
        shoppingCart.addToCart("testItem", 8.6, 1);

        Assert.assertEquals(shoppingCart.getCart().size(), 1);

        shoppingCart.addToCart("testItem", 8.6, 2);
        Assert.assertEquals(shoppingCart.getCart().size(), 1);

        shoppingCart.addToCart("testItem2", 3.6, 2);
        Assert.assertEquals(shoppingCart.getCart().size(), 2);
    }

}
