package Model;

import java.time.LocalDateTime;

public class FoodRequest {

    private int requestId;
    private Receiver receiver;
    private String foodType;
    private int quantityRequired;
    private LocalDateTime requestDateTime;
    private String status;

    public FoodRequest(int requestId, Receiver receiver,
                       String foodType, int quantityRequired) {

        this.requestId = requestId;
        this.receiver = receiver;
        this.foodType = foodType;
        this.quantityRequired = quantityRequired;
        this.requestDateTime = LocalDateTime.now();
        this.status = "PENDING";
    }

    public int getRequestId() {
        return requestId;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public String getFoodType() {
        return foodType;
    }

    public int getQuantityRequired() {
        return quantityRequired;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public void setQuantityRequired(int quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPending() {
        return status.equalsIgnoreCase("PENDING");
    }

    public int calculatePriorityScore() {

        int emergencyScore = receiver.getEmergencyLevel() * 100;

        int quantityScore = quantityRequired;

        long waitingHours =
                java.time.Duration.between(
                        requestDateTime,
                        LocalDateTime.now()
                ).toHours();

        int waitingScore = (int) waitingHours * 10;

        return emergencyScore + quantityScore + waitingScore;
    }

    @Override
    public String toString() {

        return "----------------------------------" +
                "\nREQUEST ID: " + requestId +
                "\nReceiver: " + receiver.getOrganizationName() +
                "\nFood Type Needed: " + foodType +
                "\nQuantity Required: " + quantityRequired +
                "\nEmergency Level: " +
                receiver.getEmergencyLevelText() +
                "\nPriority Score: " + calculatePriorityScore() +
                "\nStatus: " + status +
                "\n----------------------------------";
    }
}