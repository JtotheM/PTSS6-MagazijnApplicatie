package Models;

import java.util.ArrayList;

/**
 * Created by Laurence on 20/6/2015.
 */
public class OfferRequest {

    private String client;
    private String clientName;
    private String contactPerson;
    private String phone;
    private String shippingAddres;
    private String street;
    private String houseNumber;
    private String zipcode;
    private String city;
    private String description;
    private String comments;
    private ArrayList<String> opperations;
    private ArrayList<String> parts;

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
