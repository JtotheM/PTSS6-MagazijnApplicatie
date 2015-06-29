package Domain;

import java.util.ArrayList;

/**
 * OrderInvoice created by @author RY Jin on Jun 28, 2015
 * the actual received data from broker
 */
public class OrderInvoice {

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
    private ArrayList opperations;
    private ArrayList parts;
    private ArrayList<BroakerCost> broakerCosts;

    public OrderInvoice(String client, String clientName, String contactPerson, String phone, String shippingAddres, String street, String houseNumber, String zipcode, String city, String description, String comments, ArrayList opperations, ArrayList parts, ArrayList<BroakerCost> broakerCosts) {
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
        this.broakerCosts = broakerCosts;
    }

    public OrderInvoice() {
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

    public ArrayList getOpperations() {
        return opperations;
    }

    public ArrayList getParts() {
        return parts;
    }

    public ArrayList<BroakerCost> getBroakerCosts() {
        return broakerCosts;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setShippingAddres(String shippingAddres) {
        this.shippingAddres = shippingAddres;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setOpperations(ArrayList opperations) {
        this.opperations = opperations;
    }

    public void setParts(ArrayList parts) {
        this.parts = parts;
    }

    public void setBroakerCosts(ArrayList<BroakerCost> broakerCosts) {
        this.broakerCosts = broakerCosts;
    }
}
