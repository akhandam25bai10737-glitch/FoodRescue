import Model.Donor;
import Model.Receiver;
import Model.FoodItem;
import Model.FoodRequest;
import Model.Allocation;

import Service.FoodService;
import Service.RequestService;
import Service.MatchingService;
import Service.FileService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final FoodService foodService =
            new FoodService();

    private static final RequestService requestService =
            new RequestService();

    private static final MatchingService matchingService =
            new MatchingService();

    private static final FileService fileService =
            new FileService();
  

    private static int donorId = 1;
    private static int receiverId = 1;
    private static int foodId = 1;
    private static int requestId = 1;

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("         WELCOME TO FOODRESCUE");
        System.out.println(" Intelligent Surplus Food Redistribution");
        System.out.println("==========================================");

        boolean running = true;

        while (running) {

            displayMenu();

            int choice =
                    getIntegerInput("Enter your choice: ");

            switch (choice) {

                case 1:
                    addFoodDonation();
                    break;

                case 2:
                    addFoodRequest();
                    break;

                case 3:
                    foodService.viewAllFood();
                    break;

                case 4:
                    requestService.viewPendingRequests();
                    break;

                case 5:
                    findAndAllocateFood();
                    break;

                case 6:
                    matchingService.viewAllAllocations();
                    break;

                case 7:
                    foodService.viewUrgentFood();
                    break;

                case 8:
                    displaySystemStatistics();
                    break;

                case 9:
                    fileService.saveAllocations(
                            matchingService.getAllocations()
                    );
                    break;

                case 10:
                    fileService.viewSavedAllocations();
                    break;
                case 0:
                    running = false;

                    System.out.println(
                            "\nThank you for using FoodRescue!"
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println(
                "\n============== MAIN MENU =============="
        );

        System.out.println("1. Add Food Donation");
        System.out.println("2. Add Food Request");
        System.out.println("3. View Available Food");
        System.out.println("4. View Pending Requests");
        System.out.println("5. Find Best Match and Allocate Food");
        System.out.println("6. View Allocation History");
        System.out.println("7. View Urgent Food");
        System.out.println("8. View System Statistics");
        System.out.println("9. Save Allocation History to File");
        System.out.println("10. View Saved Allocation History");
        System.out.println("0. Exit");

        System.out.println(
                "======================================="
        );
    }

    private static void addFoodDonation() {

        System.out.println(
                "\n========== ADD FOOD DONATION =========="
        );

        String donorName =
                getStringInput("Enter donor name: ");

        String organization =
                getStringInput(
                        "Enter organization name: "
                );

        String phone =
                getStringInput("Enter phone number: ");

        String email =
                getStringInput("Enter email: ");

        String address =
                getStringInput("Enter address: ");

        Donor donor = new Donor(
                donorId++,
                donorName,
                phone,
                email,
                organization,
                address
        );

        String foodName =
                getStringInput("Enter food name: ");

        String foodType =
                getStringInput(
                        "Enter food type (Meals/Vegetarian/etc): "
                );

        int quantity =
                getIntegerInput("Enter quantity: ");

        int expiryHours =
                getIntegerInput(
                        "Enter hours until expiry: "
                );

        LocalDateTime expiryTime =
                LocalDateTime.now()
                        .plusHours(expiryHours);

        FoodItem foodItem = new FoodItem(
                foodId++,
                foodName,
                foodType,
                quantity,
                expiryTime,
                donor
        );

        foodService.addFood(foodItem);
    }

    private static void addFoodRequest() {

        System.out.println(
                "\n========== ADD FOOD REQUEST =========="
        );

        String receiverName =
                getStringInput("Enter receiver name: ");

        String organization =
                getStringInput(
                        "Enter organization name: "
                );

        String phone =
                getStringInput("Enter phone number: ");

        String email =
                getStringInput("Enter email: ");

        String address =
                getStringInput("Enter address: ");

        System.out.println(
                "Emergency Levels: 1 = LOW, 2 = MEDIUM, 3 = HIGH"
        );

        int emergencyLevel =
                getIntegerInput(
                        "Enter emergency level: "
                );

        while (emergencyLevel < 1
                || emergencyLevel > 3) {

            System.out.println(
                    "Invalid level. Please enter 1, 2 or 3."
            );

            emergencyLevel =
                    getIntegerInput(
                            "Enter emergency level: "
                    );
        }

        Receiver receiver = new Receiver(
                receiverId++,
                receiverName,
                phone,
                email,
                organization,
                address,
                emergencyLevel
        );

        String foodType =
                getStringInput(
                        "Enter food type required: "
                );

        int quantity =
                getIntegerInput(
                        "Enter quantity required: "
                );

        FoodRequest foodRequest = new FoodRequest(
                requestId++,
                receiver,
                foodType,
                quantity
        );

        requestService.addRequest(foodRequest);
    }

    private static void findAndAllocateFood() {

        System.out.println(
                "\n========== SMART FOOD MATCHING =========="
        );

        Allocation allocation =
                matchingService.findBestMatch(
                        foodService.getAllFoodItems(),
                        requestService.getAllRequests()
                );

        if (allocation == null) {

            System.out.println(
                    "No suitable food and request match found."
            );

        } else {

            System.out.println(
                    "\nBEST MATCH FOUND SUCCESSFULLY!"
            );

            System.out.println(allocation);
        }
    }

    private static void displaySystemStatistics() {

        System.out.println(
                "\n========== SYSTEM STATISTICS =========="
        );

        System.out.println(
                "Available Food Items: "
                        + foodService.getAvailableFoodCount()
        );

        System.out.println(
                "Pending Requests: "
                        + requestService.getPendingRequestCount()
        );

        System.out.println(
                "Completed Allocations: "
                        + matchingService.getAllocationCount()
        );
    }

    private static int getIntegerInput(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a valid number."
                );
            }
        }
    }

    private static String getStringInput(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {

                return input;
            }

            System.out.println(
                    "Input cannot be empty. Please try again."
            );
        }
    }
}