package Model;
import java.time.LocalDateTime;

public class Allocation {

    private int allocationId;
    private FoodItem foodItem;
    private FoodRequest foodRequest;
    private int allocatedQuantity;
    private LocalDateTime allocationDateTime;
    private String status;

    public Allocation(int allocationId, FoodItem foodItem,
                      FoodRequest foodRequest, int allocatedQuantity) {

        this.allocationId = allocationId;
        this.foodItem = foodItem;
        this.foodRequest = foodRequest;
        this.allocatedQuantity = allocatedQuantity;
        this.allocationDateTime = LocalDateTime.now();
        this.status = "ALLOCATED";
    }

    public int getAllocationId() {
        return allocationId;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public FoodRequest getFoodRequest() {
        return foodRequest;
    }

    public int getAllocatedQuantity() {
        return allocatedQuantity;
    }

    public LocalDateTime getAllocationDateTime() {
        return allocationDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {

        return "==================================" +
                "\nALLOCATION ID: " + allocationId +
                "\nFood: " + foodItem.getFoodName() +
                "\nReceiver: " +
                foodRequest.getReceiver().getOrganizationName() +
                "\nQuantity Allocated: " + allocatedQuantity +
                "\nAllocation Time: " + allocationDateTime +
                "\nStatus: " + status +
                "\n==================================";
    }
}