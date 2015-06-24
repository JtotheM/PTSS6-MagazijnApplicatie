package Models;

import Requests.BroakerCost;

import java.util.ArrayList;

/**
 * Created by Laurence on 24/6/2015.
 */
public class OfferRequestProcessed {
    private String client;
    private String clientName;
    private String ContactPerson;
    private String Phone;
    private String ShippingAddres ;
    private String Street ;
    private String HouseNumber ;
    private String Zipcode ;
    private String City ;
    private String Description ;
    private String Comments ;
    private ArrayList<String> Opperations;
    private ArrayList<String> Parts;
    private ArrayList<BroakerCost> broakerCosts;

    public OfferRequestProcessed(OfferRequest offerRequest, ArrayList<BroakerCost> broakerCosts) {
        this.client = offerRequest.getClient();
        this.clientName = offerRequest.getClientName();
        this.ContactPerson = offerRequest.getContactPerson();
        this.Phone = offerRequest.getPhone();
        this.ShippingAddres = offerRequest.getShippingAddres();
        this.Street = offerRequest.getStreet();
        this.HouseNumber = offerRequest.getHouseNumber();
        this.Zipcode = offerRequest.getZipcode();
        this.City = offerRequest.getCity();
        this.Description = offerRequest.getDescription();
        this.Comments = offerRequest.getComments();
        this.Opperations = offerRequest.getOpperations();
        this.Parts = offerRequest.getParts();
        this.broakerCosts = broakerCosts;
    }

    public String getClient() {
        return client;
    }

    public String getClientName() {
        return clientName;
    }

    public String getContactPerson() {
        return ContactPerson;
    }

    public String getPhone() {
        return Phone;
    }

    public String getShippingAddres() {
        return ShippingAddres;
    }

    public String getStreet() {
        return Street;
    }

    public String getHouseNumber() {
        return HouseNumber;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public String getCity() {
        return City;
    }

    public String getDescription() {
        return Description;
    }

    public String getComments() {
        return Comments;
    }

    public ArrayList<String> getOpperations() {
        return Opperations;
    }

    public ArrayList<String> getParts() {
        return Parts;
    }

    public ArrayList<BroakerCost> getBroakerCosts() {
        return broakerCosts;
    }
}
