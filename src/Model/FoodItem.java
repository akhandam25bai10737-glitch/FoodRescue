package Model;

import java.time.LocalDateTime;

public class FoodItem {

    private int foodId;
    private String foodName;
    private String foodType;
    private int quantity;
    private LocalDateTime expiryDateTime;
    private Donor donor;
    private boolean allocated;

    public FoodItem(int foodId, String foodName, String foodType,
                    int quantity, LocalDateTime expiryDateTime, Donor donor) {

        this.foodId = foodId;
        this.foodName = foodName;
        this.foodType = foodType;
        this.quantity = quantity;
        this.expiryDateTime = expiryDateTime;
        this.donor = donor;
        this.allocated = false;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    public Donor getDonor() {
        return donor;
    }

    public boolean isAllocated() {
        return allocated;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDateTime);
    }

    public long getHoursUntilExpiry() {
        return java.time.Duration.between(
                LocalDateTime.now(),
                expiryDateTime
        ).toHours();
    }

    @Override
    public String toString() {

        String status;

        if (isExpired()) {
            status = "EXPIRED";
        } else if (allocated) {
            status = "ALLOCATED";
        } else {
            status = "AVAILABLE";
        }

        return "----------------------------------" +
                "\nFOOD ID: " + foodId +
                "\nFood Name: " + foodName +
                "\nFood Type: " + foodType +
                "\nQuantity: " + quantity +
                "\nExpiry: " + expiryDateTime +
                "\nDonor: " + donor.getOrganizationName() +
                "\nStatus: " + status +
                "\n----------------------------------";
    }
}