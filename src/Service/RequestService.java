package Service;

import Model.FoodRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RequestService {

    private List<FoodRequest> foodRequests;

    public RequestService() {
        foodRequests = new ArrayList<>();
    }

    public void addRequest(FoodRequest foodRequest) {

        if (foodRequest == null) {
            System.out.println("Food request cannot be null.");
            return;
        }

        if (foodRequest.getQuantityRequired() <= 0) {
            System.out.println("Requested quantity must be greater than zero.");
            return;
        }

        foodRequests.add(foodRequest);

        System.out.println("Food request added successfully.");
    }

    public List<FoodRequest> getAllRequests() {
        return foodRequests;
    }

    public FoodRequest findRequestById(int requestId) {

        for (FoodRequest request : foodRequests) {
            if (request.getRequestId() == requestId) {
                return request;
            }
        }

        return null;
    }

    public void viewAllRequests() {

        if (foodRequests.isEmpty()) {
            System.out.println("\nNo food requests available.");
            return;
        }

        System.out.println("\n========== ALL FOOD REQUESTS ==========");

        for (FoodRequest request : foodRequests) {
            System.out.println(request);
        }
    }

    public void viewPendingRequests() {

        List<FoodRequest> pendingRequests = foodRequests.stream()
                .filter(FoodRequest::isPending)
                .sorted(Comparator.comparingInt(
                        FoodRequest::calculatePriorityScore
                ).reversed())
                .collect(Collectors.toList());

        if (pendingRequests.isEmpty()) {
            System.out.println("\nNo pending food requests.");
            return;
        }

        System.out.println("\n========== PENDING REQUESTS BY PRIORITY ==========");

        for (FoodRequest request : pendingRequests) {
            System.out.println(request);
        }
    }

    public boolean completeRequest(int requestId) {

        FoodRequest request = findRequestById(requestId);

        if (request == null) {
            System.out.println("Food request not found.");
            return false;
        }

        request.setStatus("COMPLETED");

        System.out.println("Food request marked as completed.");

        return true;
    }

    public boolean cancelRequest(int requestId) {

        FoodRequest request = findRequestById(requestId);

        if (request == null) {
            System.out.println("Food request not found.");
            return false;
        }

        request.setStatus("CANCELLED");

        System.out.println("Food request cancelled.");

        return true;
    }

    public List<FoodRequest> getPendingRequests() {

        return foodRequests.stream()
                .filter(FoodRequest::isPending)
                .collect(Collectors.toList());
    }

    public int getPendingRequestCount() {

        return (int) foodRequests.stream()
                .filter(FoodRequest::isPending)
                .count();
    }
}