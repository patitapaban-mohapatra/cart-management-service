package cart;

// **********************************************************************
//   ShoppingCart.java
//
//   Represents a shopping cart as an array of items
// **********************************************************************
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart
{

    private List<Item> cart;
    private int itemCount;      // total number of items in the cart
    private double subTotalPrice;  // subTotal price of items in the cart(sum of price for all items)
    private double totalPrice;  // total price of items in the cart (Total payable (subtotal + tax))
    private double tax;         //Tax payable (charged at 12.5% on the subtotal)
    private int capacity;       // current cart capacity

    // -----------------------------------------------------------
    //  Creates an empty shopping cart with a capacity of 5 items.
    // -----------------------------------------------------------
    public ShoppingCart()
    {

        capacity = 5;
        cart = new ArrayList<>();
        itemCount = 0;
        totalPrice = 0.0;
        subTotalPrice = 0.0;
        tax = 0.0;
    }

    public List<Item> getCart() {
        return cart;
    }

    // -------------------------------------------------------
    //  Adds an item to the shopping cart.
    // -------------------------------------------------------
    public void addToCart(String itemName, double price, int quantity)
    {

        Item temp = new Item(itemName, price, quantity);
        subTotalPrice += (price * quantity);
        tax = subTotalPrice * .125;
        totalPrice = subTotalPrice + tax;
        itemCount += quantity;
        if(cart.contains(temp)) {
            addExistingItemToCart(temp);
        } else {
            cart.add(temp);
        }
        /*if(itemCount==capacity)
        {
            increaseSize();
        }*/
    }

    // -------------------------------------------------------
    //  Returns the contents of the cart together with
    //  summary information.
    // -------------------------------------------------------
    public String toString()
    {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        String contents = "\nShopping Cart\n";
        contents += "\nItem\t\tUnit Price\tQuantity\tTotal\n";

        for (Item itemVal: cart)
            contents += itemVal.toString() + "\n";

        contents += "\nSubtotal Price: " + fmt.format(subTotalPrice);
        contents += "\nTax: " + fmt.format(tax);
        contents += "\nTotal Price: " + fmt.format(totalPrice);
        contents += "\n";

        return contents;
    }

    // ---------------------------------------------------------
    //  Increases the capacity of the shopping cart by 3
    // ---------------------------------------------------------
    /*private void increaseSize()
    {
        Item[] temp = new Item[capacity+3];
        for(int i=0; i < capacity; i++)
        {
            temp[i] = cart[i];
        }
        cart = temp;
        temp = null;
        capacity = cart.length;
    }*/

    // ---------------------------------------------------------
    //  Increases the capacity of the shopping cart by 3
    // ---------------------------------------------------------


    public void addExistingItemToCart(Item item) {
                cart.stream()
                        .filter(it -> it.getName().equals(item.getName()))
                        .findFirst()
                        .map(it -> it.increaseQuantity(item.getQuantity())).orElse(item.getQuantity());

    }
}
