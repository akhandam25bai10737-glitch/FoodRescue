package Model;

public class Receiver extends User {

    private String organizationName;
    private String address;
    private int emergencyLevel;

    public Receiver(int id, String name, String phone, String email,
                    String organizationName, String address, int emergencyLevel) {

        super(id, name, phone, email);

        this.organizationName = organizationName;
        this.address = address;
        this.emergencyLevel = emergencyLevel;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmergencyLevel() {
        return emergencyLevel;
    }

    public void setEmergencyLevel(int emergencyLevel) {
        if (emergencyLevel >= 1 && emergencyLevel <= 3) {
            this.emergencyLevel = emergencyLevel;
        }
    }

    public String getEmergencyLevelText() {
        switch (emergencyLevel) {
            case 3:
                return "HIGH";
            case 2:
                return "MEDIUM";
            case 1:
                return "LOW";
            default:
                return "UNKNOWN";
        }
    }

    @Override
    public String toString() {
        return "RECEIVER DETAILS" +
                "\nID: " + getId() +
                "\nName: " + getName() +
                "\nOrganization: " + organizationName +
                "\nPhone: " + getPhone() +
                "\nEmail: " + getEmail() +
                "\nAddress: " + address +
                "\nEmergency Level: " + getEmergencyLevelText();
    }
}