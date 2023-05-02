package cart;

// ***************************************************************
//   Shop.java
//
//   Uses the Item class to create items and add them to a shopping
//   cart stored in an ArrayList.
// ***************************************************************

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Shop {
    public static void main(String[] args) {
        ArrayList<Item> cart = new ArrayList<Item>();

        Item item;
        String itemName;
        double itemPrice;
        int quantity;

        Scanner scan = new Scanner(System.in);

        String keepShopping = "y";
        ShoppingCart cart1 = new ShoppingCart();
        do {

            System.out.print("Enter the name of the item: ");
            itemName = scan.next();

            itemPrice = getItemData(itemName);

            System.out.print("Enter the quantity: ");
            quantity = scan.nextInt();

            // *** create a new item and add it to the cart
            cart1.addToCart(itemName, itemPrice, quantity);


            // *** print the contents of the cart object using println
            System.out.println(cart1);

            System.out.print("Continue shopping (y/n)? ");
            keepShopping = scan.next();
        }
        while (keepShopping.equals("y"));

    }

    public static double getItemData(String itemName) {
        double itemPrice = 0.0;
        try {
            URL url = new URL("https://equalexperts.github.io//backend-take-home-test-data/" + itemName.toLowerCase() + ".json");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {

                String inline = "";
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }

                //Close the scanner
                scanner.close();

                //Using the JSON simple library parse the string into a json object
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(inline);

                 itemPrice = (double) data_obj.get("price");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemPrice;
    }
}
