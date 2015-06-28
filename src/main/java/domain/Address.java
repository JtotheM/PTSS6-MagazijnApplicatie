package domain;

public class Address {

    private String street;
    private String number;
    private String postalCode;
    private String place;

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return this.street + " " + this.number;
    }
}
