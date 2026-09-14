package Service;

import Model.FoodItem;
import Model.InvalidFoodException;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class FoodService {

    private List<FoodItem> foodItems;

    public FoodService() {

        foodItems = new ArrayList<>();

    }

    public void addFood(FoodItem foodItem) {

        try {

            if (foodItem == null) {

                throw new InvalidFoodException(
                        "Food item cannot be null."
                );

            }

            if (foodItem.getQuantity() <= 0) {

                throw new InvalidFoodException(
                        "Food quantity must be greater than zero."
                );

            }

            foodItems.add(foodItem);

            System.out.println("Food added successfully.");

        } catch (InvalidFoodException e) {

            System.out.println(
                    "Invalid Food: " + e.getMessage()
            );

        }

    }

    public List<FoodItem> getAllFoodItems() {

        return foodItems;

    }

    public FoodItem findFoodById(int foodId) {

        for (FoodItem foodItem : foodItems) {

            if (foodItem.getFoodId() == foodId) {

                return foodItem;

            }

        }

        return null;

    }

    public void viewAllFood() {

        if (foodItems.isEmpty()) {

            System.out.println("\nNo food items available.");

            return;

        }

        System.out.println(
                "\n========== AVAILABLE FOOD ITEMS =========="
        );

        Iterator<FoodItem> iterator =
                foodItems.iterator();

        while (iterator.hasNext()) {

            FoodItem foodItem =
                    iterator.next();

            System.out.println(foodItem);

        }

    }

    public void viewUrgentFood() {

        boolean found = false;

        System.out.println(
                "\n========== URGENT FOOD ITEMS =========="
        );

        for (FoodItem foodItem : foodItems) {

            if (!foodItem.isExpired()
                    && !foodItem.isAllocated()
                    && foodItem.getHoursUntilExpiry() <= 6) {

                System.out.println(foodItem);

                found = true;

            }

        }

        if (!found) {

            System.out.println(
                    "No urgent food items found."
            );

        }

    }

    public boolean updateFoodQuantity(
            int foodId,
            int newQuantity
    ) {

        FoodItem foodItem =
                findFoodById(foodId);

        if (foodItem == null) {

            System.out.println(
                    "Food item not found."
            );

            return false;

        }

        if (newQuantity < 0) {

            System.out.println(
                    "Quantity cannot be negative."
            );

            return false;

        }

        foodItem.setQuantity(newQuantity);

        System.out.println(
                "Food quantity updated successfully."
        );

        return true;

    }

    public boolean removeFood(int foodId) {

        FoodItem foodItem =
                findFoodById(foodId);

        if (foodItem == null) {

            System.out.println(
                    "Food item not found."
            );

            return false;

        }

        foodItems.remove(foodItem);

        System.out.println(
                "Food item removed successfully."
        );

        return true;

    }

    public int getAvailableFoodCount() {

        int count = 0;

        for (FoodItem foodItem : foodItems) {

            if (!foodItem.isExpired()
                    && !foodItem.isAllocated()
                    && foodItem.getQuantity() > 0) {

                count++;

            }

        }

        return count;

    }

}