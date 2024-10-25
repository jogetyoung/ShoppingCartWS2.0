package workshop03;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    // create a String list called cart
    private List<String> cart;
    private String customer;

    // create a constructor called ShoppingCart
    public ShoppingCart(String customerName) {
        // to specify a cart for each user
        this.cart = new ArrayList<>();
        this.customer = customerName;
    }

    // array list to return the values in user's cart
    public List<String> getItems() {
        return cart;
    }

    public String getUser() {
        return customer;
    }

    // function to list items
    public void listItems() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty");
        } else {
            for (int i = 0; i < cart.size(); i++) {
                System.out.println((i + 1) + ". " + cart.get(i));

            }
        }
    }

    // function to add items
    // (String item) is parameter for type of item we want to add
    public void addItems(String item) {
        cart.add(item.trim().replaceAll("[\\p{Punct}]", ""));
        System.out.println(item + " added to cart");
    }

    // function to remove items
    // (int index) is parameter for type of item we want to remove
    // (we want to remove items from cart via their numbering/index)
    public void removeItems(int index) {
        int size = cart.size();
        if (index < 0 || index > size) {
            System.out.println("Incorrect item index");
            // ends the method
            return;
        }
        // declare String named removedItem (item which was removed from shopping cart)
        // (index - 1) because item 1 is at index 0, item 2 is at index 1 etc etc
        String removedItem = cart.remove(index - 1);
        System.out.println(removedItem + " removed from cart");
    }

}
