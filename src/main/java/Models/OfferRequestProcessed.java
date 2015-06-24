package Models;

import Requests.BroakerCost;

import java.util.ArrayList;

/**
 * Created by Laurence on 24/6/2015.
 */
public class OfferRequestProcessed {
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
    private ArrayList<BroakerCost> broakerCosts;

    public OfferRequestProcessed(OfferRequest offerRequest, ArrayList<BroakerCost> broakerCosts) {
        this.client = offerRequest.getClient();
        this.clientName = offerRequest.getClientName();
        this.contactPerson = offerRequest.getContactPerson();
        this.phone = offerRequest.getPhone();
        this.shippingAddres = offerRequest.getShippingAddres();
        this.street = offerRequest.getStreet();
        this.houseNumber = offerRequest.getHouseNumber();
        this.zipcode = offerRequest.getZipcode();
        this.city = offerRequest.getCity();
        this.description = offerRequest.getDescription();
        this.comments = offerRequest.getComments();
        this.opperations = offerRequest.getOpperations();
        this.parts = offerRequest.getParts();
        this.broakerCosts = broakerCosts;
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

    public ArrayList<BroakerCost> getBroakerCosts() {
        return broakerCosts;
    }
}
