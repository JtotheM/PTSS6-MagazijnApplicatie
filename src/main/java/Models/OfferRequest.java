package Models;

import java.util.ArrayList;

/**
 * Created by Laurence on 20/6/2015.
 */
public class OfferRequest {
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
}
