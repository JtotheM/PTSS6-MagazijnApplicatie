package Models;

/**
 * Created by Laurence on 20/6/2015.
 */
public class WorkRequest {

    private String client;
    private String clientName;
    private String ContactPerson;
    private String Phone;
    private String ShippingAddres ;
    private String Street ;
    private String HouseNumber ;
    private String Zipcode ;
    private String City ;
    private String itemToBuy ;

    public WorkRequest(OfferRequest offerRequest, String itemToBuy) {
        this.client = offerRequest.getClient();
        this.clientName = offerRequest.getClientName();
        this.ContactPerson = offerRequest.getContactPerson();
        this.Phone = offerRequest.getPhone();
        this.ShippingAddres = offerRequest.getShippingAddres();
        this.Street = offerRequest.getStreet();
        this.HouseNumber = offerRequest.getHouseNumber();
        this.Zipcode = offerRequest.getZipcode();
        this.City = offerRequest.getCity();
        this.itemToBuy = itemToBuy;
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

    public String getItemToBuy() {
        return itemToBuy;
    }
}


