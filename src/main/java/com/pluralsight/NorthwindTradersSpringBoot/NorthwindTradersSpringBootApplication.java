package com.pluralsight.NorthwindTradersSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class NorthwindTradersSpringBootApplication {

    private static ProductDao productDao;
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean running = true;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(NorthwindTradersSpringBootApplication.class, args);

        productDao = context.getBean(ProductDao.class);
        runApp();
    }


    private static void runApp() {
        getUserChoice();
    }

    private static void menu() {
        System.out.println("1. List Products");
        System.out.println("2. Add Product");
        System.out.println("3. Exit");

    }

    private static void listProducts() {
        productDao.getAllProducts().stream().forEach(System.out::println);
    }

    private static void addProduct() {
        System.out.println("Adding a new product.");
        String name = getNonEmptyString("Enter product name: ");
        String category = getNonEmptyString("Enter product category: ");
        double price = getDouble("Enter product price: ");
        Product product = new Product(idCounter(), name, category, price);
        productDao.add(product);
        System.out.println("Product added successfully.");
    }

    private static int idCounter() {
        return productDao.getAllProducts().size() + 1;
    }


    private static void getUserChoice() {
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

    public static String getNonEmptyString(String prompt) {
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


    public static int getIntInRange(String prompt, int min, int max) {
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

    public static double getDouble(String prompt) {
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

    private static void exit() {
        System.out.println("Exiting application. Goodbye!");
        System.exit(0);
        scanner.close();
    }

}
