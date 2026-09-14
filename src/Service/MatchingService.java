package Service;

import Model.Allocation;
import Model.FoodItem;
import Model.FoodRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MatchingService {

    private List<Allocation> allocations;
    private int nextAllocationId;

    public MatchingService() {
        allocations = new ArrayList<>();
        nextAllocationId = 1;
    }

    public Allocation findBestMatch(
            List<FoodItem> foodItems,
            List<FoodRequest> foodRequests) {

        FoodItem bestFood = null;
        FoodRequest bestRequest = null;

        int highestScore = -1;

        for (FoodItem food : foodItems) {

            if (food.isExpired()
                    || food.isAllocated()
                    || food.getQuantity() <= 0) {
                continue;
            }

            for (FoodRequest request : foodRequests) {

                if (!request.isPending()) {
                    continue;
                }

                if (!food.getFoodType()
                        .equalsIgnoreCase(request.getFoodType())) {
                    continue;
                }

                int score = calculateMatchScore(food, request);

                if (score > highestScore) {
                    highestScore = score;
                    bestFood = food;
                    bestRequest = request;
                }
            }
        }

        if (bestFood == null || bestRequest == null) {
            return null;
        }

        int allocatedQuantity = Math.min(
                bestFood.getQuantity(),
                bestRequest.getQuantityRequired()
        );

        Allocation allocation = new Allocation(
                nextAllocationId++,
                bestFood,
                bestRequest,
                allocatedQuantity
        );

        bestFood.setQuantity(
                bestFood.getQuantity() - allocatedQuantity
        );

        if (bestFood.getQuantity() == 0) {
            bestFood.setAllocated(true);
        }

        bestRequest.setStatus("COMPLETED");

        allocations.add(allocation);

        return allocation;
    }

    private int calculateMatchScore(
            FoodItem food,
            FoodRequest request) {

        int score = 0;

        // Receiver emergency priority
        score += request.getReceiver()
                .getEmergencyLevel() * 100;

        // Quantity demand priority
        score += request.getQuantityRequired();

        // Food expiry priority
        long hoursLeft = food.getHoursUntilExpiry();

        if (hoursLeft <= 2) {
            score += 150;
        } else if (hoursLeft <= 6) {
            score += 100;
        } else if (hoursLeft <= 12) {
            score += 50;
        }

        return score;
    }

    public void viewAllAllocations() {

        if (allocations.isEmpty()) {
            System.out.println(
                    "\nNo allocations have been made yet."
            );
            return;
        }

        System.out.println(
                "\n========== FOOD ALLOCATION HISTORY =========="
        );

        for (Allocation allocation : allocations) {
            System.out.println(allocation);
        }
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public List<FoodRequest> getRequestsByPriority(
            List<FoodRequest> requests) {

        return requests.stream()
                .filter(FoodRequest::isPending)
                .sorted(
                        Comparator.comparingInt(
                                FoodRequest::calculatePriorityScore
                        ).reversed()
                )
                .toList();
    }

    public int getAllocationCount() {
        return allocations.size();
    }
}