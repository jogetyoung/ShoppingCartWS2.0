package workshop03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // initializing the position of "cmd"
    public static final int CMD_POS = 0;
    // creating array list called userList to store customer names and database
    private static List<String> userList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Map<String, boolean>() loginOrOut = HashMap<>();

        // dont declare value so that it can be applied for cartdb or db
        String database = "";

        // ==1 instead of >0 because there is only one index
        if (args.length == 1) {
            // if entry equals to "cartdb", automatically accesses the folder cartdb
            if (args[0].equalsIgnoreCase("cartdb")) {
                database = "/Users/joel/Desktop/ShoppingCartWS2.0/" + args[0];
                System.out.println(database + " has been accessed");
            } else {
                System.err.println("This database does not exist. Run the program with correct database");
                System.exit(1);
            }
        } else {
            // default database if cartdb is not called
            // automatically goes to db
            database = "/Users/joel/Desktop/ShoppingCartWS2.0/db";
            System.out.println("default database accessed");
        }
        System.out.println("Welcome to your shopping cart");

        // stop command to end program
        boolean stop = false;

        Scanner scanner = new Scanner(System.in);

        // declaring that shoppingCart auto value is null, kosong
        ShoppingCart shoppingCart = null;

        String customer;

        while (!stop) {
            System.out.println(">>>");

            String input = scanner.nextLine();

            // splitting by " " because when we add items,
            // we type in "add orange, apple etc" and it helps to split items up
            String[] terms = input.split(" ");

            // declare that cmd will forever be at index 0 for our String[] called terms
            String cmd = terms[CMD_POS].toUpperCase();

            switch (cmd) {

                // to list cart items
                case "LIST":
                    shoppingCart.listItems();
                    break;

                // added to make it more realistic if it was used in real life
                case "STOP":
                    stop = true;
                    break;

                // to add items to cart
                case "ADD":
                    for (int idx = 1; idx < terms.length; idx++) {
                        shoppingCart.addItems(terms[idx]);

                    }
                    break;

                case "DELETE":
                    // parseInt is function to convert a String, containing a number, to an actual
                    // Integer
                    // e.g. "delete 2" - delete is the CMD_POS at terms[0] and the integer 2 is at
                    // terms[1]
                    int itemIndexRemove = Integer.parseInt(terms[1]);
                    shoppingCart.removeItems(itemIndexRemove);
                    break;

                case "LOGIN":
                    // user has to type "login" + "name"
                    // if the length of terms is ==1 (meaning there is something at index 0 but not
                    // index 1),
                    // print out the following message
                    if (terms.length == 1) {
                        System.out.println("Please input name after login");
                    } else {
                        customer = terms[1];
                        shoppingCart = new ShoppingCart(customer);
                        ShoppingCartDB.loadCart(database, customer, shoppingCart);
                        // if the customer name is not in database
                        if (!userList.contains(customer)) {
                            userList.add(customer);
                        } else {
                            System.out.println();
                            System.out.println(customer + " is already logged in");
                        }
                    }
                    break;

                /*
                 * case "LOGOUT":
                 * if(terms.length == 1){
                 * System.out.println("Please input name after logout");
                 * } else {
                 * HashMap
                 * }
                 */

                case "SAVE":
                    if (shoppingCart != null) {
                        ShoppingCartDB.saveCart(database, shoppingCart.getUser(), shoppingCart);
                    } else {
                        System.out.println("You must log in to save cart");
                    }
                    break;

                case "USERS":
                    System.out.println("These are the registered users");
                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println((i + 1) + ". " + userList.get(i));
                    }
                    break;

                default:
                    System.out.println("Unknown command. Only enter 'users', 'login', 'save', 'list', 'add', 'stop', or 'delete'.");
                    break;

            }

        }

    }

}
