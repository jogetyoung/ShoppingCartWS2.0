package workshop03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ShoppingCartDB {

    // function to save cart
    // takes in String parameters and ShoppingCart.java parameter
    // database is the database you choose to access - cartdb or db or any database
    // you may have created
    // customer is the customer's name
    // cart is the contents of that specific customer's cart
    public static void saveCart(String database, String customer, ShoppingCart cart) throws IOException {
        // creating an object called customerDB
        File customerDB = new File(database, customer + ".db");

        // write to the specific file to save the contents of the file, in this case,
        // the contents of the shopping cart
        // writing to File customerDB
        FileWriter fw = new FileWriter(customerDB);
        BufferedWriter bw = new BufferedWriter(fw);

        // for-each loop to go through what is in your cart one by one
        for (String item : cart.getItems()) {
            // and then writing the list into the database, becoming a saved file in File
            // customerDB
            bw.write(item + System.lineSeparator());
        }

        bw.flush();
        bw.close();
        fw.close();
        System.out.println("Your cart has been saved");

    }

    // function to load cart
    // takes in String parameter
    public static void loadCart(String database, String customer, ShoppingCart cart) throws IOException {

        File customerDB = new File(database, customer + ".db");

        // if customer does not exist, create a new file for customer in whichever
        // database was declared in the beginning
        if (!customerDB.exists()) {
            customerDB.createNewFile();
            System.out.println("Welcome " + customer + ", your cart is empty");
        } else { // if customer exists, in database (customerDB), read out their existing file
            FileReader fr = new FileReader(customerDB);
            BufferedReader br = new BufferedReader(fr);

            // if customer cart is empty, 0 items so .length==0, tell customer it is empty
            if (customerDB.length() == 0) {
                System.out.println(customer + ", your cart is empty");
            } else {
                // acts as a placeholder so that when .readLine is called on it, there is
                // something to "read"
                String line = "x";
                int index = 1;
                // so the format will be ("index. line") --> e.g. (1. apple)
                System.out.println(customer + ", your cart contains the following items:");

                // so while the line is not empty, it reads each line and prints out the items
                while ((line = br.readLine()) != null) {
                    // whatever you read from 'line', is then added to cart
                    // .getItems is to reference to ShoppingCart.java
                    cart.getItems().add(line);
                    System.out.println(index++ + ". " + line);
                }
            }
            br.close();
            fr.close();

        }
    }

}
