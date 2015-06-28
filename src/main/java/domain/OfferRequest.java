package domain;

import java.util.ArrayList;

public class OfferRequest {

    private final String client;
    private final String clientName;
    private final String contactPerson;
    private final String phone;
    private final String shippingAddres;
    private final String street;
    private final String houseNumber;
    private final String zipcode;
    private final String city;
    private final String description;
    private final String comments;
    private final ArrayList<String> opperations;
    private final ArrayList<String> parts;

    public OfferRequest(String client, String clientName, String contactPerson, String phone, String shippingAddres, String street, String houseNumber, String zipcode, String city, String description, String comments, ArrayList<String> opperations, ArrayList<String> parts) {
        this.client = client;
        this.clientName = clientName;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.shippingAddres = shippingAddres;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipcode = zipcode;
        this.city = city;
        this.description = description;
        this.comments = comments;
        this.opperations = opperations;
        this.parts = parts;
    }

    public String getClient() {
        return client;
    }

    public String getClientName() {
        return clientName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public String getShippingAddres() {
        return shippingAddres;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public String getComments() {
        return comments;
    }

    public ArrayList<String> getOpperations() {
        return opperations;
    }

    public ArrayList<String> getParts() {
        return parts;
    }
}
