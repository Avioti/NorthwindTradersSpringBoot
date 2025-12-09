package com.pluralsight.NorthwindTradersSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class NorthWindApplication implements CommandLineRunner {
    private final ProductDao productDao;
    private final Scanner scanner;
    private boolean running;


    @Autowired
    public NorthWindApplication(ProductDao productDao) {
        this.productDao = productDao;
        this.scanner = new Scanner(System.in);
        this.running = true;
    }
    
    
    @Override
    public void run(String... args) throws Exception {
        getUserChoice();
    }


    private  void menu() {
        System.out.println("1. List Products");
        System.out.println("2. Add Product");
        System.out.println("3. Exit");

    }

    private  void listProducts() {
        productDao.getAllProducts().stream().forEach(System.out::println);
    }

    private  void addProduct() {
        System.out.println("Adding a new product.");
        String name = getNonEmptyString("Enter product name: ");
        String category = getNonEmptyString("Enter product category: ");
        double price = getDouble("Enter product price: ");
        Product product = new Product(idCounter(), name, category, price);
        productDao.add(product);
        System.out.println("Product added successfully.");
    }

    private  int idCounter() {
        return productDao.getAllProducts().size() + 1;
    }


    private  void getUserChoice() {
        int choice = 0;
        while (running) {
            menu();
            choice = getIntInRange("Enter your choice: ", 1, 3);
            switch (choice) {
                case 1:
                    listProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public  String getNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
    }


    public  int getIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);

                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public  double getDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private  void exit() {
        System.out.println("Exiting application. Goodbye!");
        System.exit(0);
        running = false;
        scanner.close();
    }
}
