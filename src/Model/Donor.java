package Model;

public class Donor extends User {

    private String organizationName;
    private String address;

    public Donor(int id, String name, String phone, String email,
                 String organizationName, String address) {

        super(id, name, phone, email);

        this.organizationName = organizationName;
        this.address = address;
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

    @Override
    public String toString() {
        return "DONOR DETAILS" +
                "\nID: " + getId() +
                "\nName: " + getName() +
                "\nOrganization: " + organizationName +
                "\nPhone: " + getPhone() +
                "\nEmail: " + getEmail() +
                "\nAddress: " + address;
    }
}