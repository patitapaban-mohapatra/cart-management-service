package cart;

// ***************************************************************
//   Item.java
//
//   Represents an item in a shopping cart.
// ***************************************************************

import java.text.NumberFormat;
import java.util.Objects;

public class Item
{
    private String name;
    private double price;
    private int quantity;

    // -------------------------------------------------------
    //  Create a new item with the given attributes.
    // -------------------------------------------------------
    public Item (String itemName, double itemPrice, int numPurchased)
    {
        name = itemName;
        price = itemPrice;
        quantity = numPurchased;
    }

    // -------------------------------------------------------
    //   Return a string with the information about the item
    // -------------------------------------------------------
    public String toString ()
    {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();

        return (name + "\t" + fmt.format(price) + "\t" + quantity + "\t"
                + fmt.format(price*quantity));
    }

    // -------------------------------------------------
    //   Returns the unit price of the item
    // -------------------------------------------------
    public double getPrice()
    {
        return price;
    }

    // -------------------------------------------------
    //   Returns the name of the item
    // -------------------------------------------------
    public String getName()
    {
        return name;
    }

    // -------------------------------------------------
    //   Returns the quantity of the item
    // -------------------------------------------------
    public int getQuantity()
    {
        return quantity;
    }

    // -------------------------------------------------
    //   Set the quantity of the item
    // -------------------------------------------------
    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        /*if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }*/
        return true;
    }

    public int increaseQuantity(int quantity) {
        if (quantity <0) {
            return -1;
        }
        this.setQuantity(this.getQuantity() + quantity);
        return this.getQuantity();
    }

    public int decreaseQuantity(int quantity) {
        if (quantity <0) {
            return -1;}
        this.setQuantity(this.getQuantity() - quantity);
        return this.getQuantity();
    }

    Item increaseQuantityOfExistingItem(Item item, int quantity)
    {
        return new Item(item.getName(), item.getPrice(), quantity);
    }
}
