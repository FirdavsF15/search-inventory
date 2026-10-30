package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Product> inventory = getInventory();

    public static void main(String[] args) {

        //inventory.sort(Comparator<?getName()>);

        //display(inventory);

        boolean keepGoing = true;
        do{
            System.out.println("""
                    \nWhat do you want to do?
                    \t1- List all products
                    \t2- Lookup a product by its id
                    \t3- Find all products within a price range
                    \t4- Add a new product
                    \t5- Quit the application
                    Enter command:
                    """);
            int command = Integer.parseInt(scanner.nextLine());

            switch(command){
                case 1:
                    display(inventory);
                    break;
                case 2:
                    searchById();
                    break;
                case 3:
                    searchByPriceRange();
                    break;
                case 4:
                    addProduct();
                    break;
                case 5:
                    keepGoing = false;
                    break;
            }



        }while(keepGoing);

    }

    public static ArrayList<Product> getInventory(){
        ArrayList<Product> inventory = new ArrayList<Product>();

        try{
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();

            while((input = bufReader.readLine())!=null){
                String[] product = input.split("\\|");
                int id = Integer.parseInt(product[0]);
                String name = product[1];
                double price = Double.parseDouble(product[2]);

                Product item = new Product(id,name,price);
                inventory.add(item);
            }
        }catch(Exception e){
            e.printStackTrace();
        }


        return inventory;
    }

    public static void display(ArrayList<Product> inventory){
        for (Product p: inventory) {

            System.out.printf("id: %d %s - Price: $%.2f%n",
                    p.getId(), p.getName(), p.getPrice());
        }
    }

    public static void searchById(){
        System.out.println("Enter product ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        for(Product p: inventory){
            if(p.getId() == id){
                System.out.println(p.getId() + "-" + p.getName() + " : " + p.getPrice());
            }
        }
    }

    public static void searchByPriceRange(){
        System.out.print("Enter minimum product price: ");
        double minPrice = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter maximum product price: ");
        double maxPrice = Double.parseDouble(scanner.nextLine());

        for(Product p: inventory){
            if(p.getPrice()>= minPrice && p.getPrice()<=maxPrice){
                System.out.println(p.getId() + "-" + p.getName() + " : " + p.getPrice());
            }
        }
    }

    public static void addProduct(){
        System.out.println("Adding product sequence activated, enter information ->");
        System.out.print("Enter product ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Product newProduct = new Product(id, name, price);
        inventory.add(newProduct);
    }

}


