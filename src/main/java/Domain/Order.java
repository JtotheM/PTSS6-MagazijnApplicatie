package Domain;

import java.awt.List;
import java.util.ArrayList;

/**
 * Order
 * created by @author RY Jin on Jun 20, 2015
 */
public class Order {

    private String client;
    private ContactPersoon contactPersoon;
    private ShippingAddres shipping;
    private String comments;
    private ArrayList<String> operations;
    private ArrayList<String> parts;

    public Order(String nameclient, ContactPersoon contactpersoon, ShippingAddres shippingaddres, String reparationDescription, ArrayList workperformed, ArrayList parts) {
        this.client = nameclient;
        this.contactPersoon = contactpersoon;
        this.shipping = shippingaddres;
        this.comments = reparationDescription;
        this.operations = workperformed;
        this.parts = parts;
    }
    
    public Order(){
        
    }

    public void setNameClient(String NameClient) {
        this.client = NameClient;
    }

    public void setContactPersoon(ContactPersoon ContactPersoon) {
        this.contactPersoon = ContactPersoon;
    }

    public void setShippingAddres(ShippingAddres ShippingAddres) {
        this.shipping = ShippingAddres;
    }

    public void setReparationDescription(String ReparationDescription) {
        this.comments = ReparationDescription;
    }

    public void setWorkperformed(ArrayList Workperformed) {
        this.operations = Workperformed;
    }

    public void setParts(ArrayList Parts) {
        this.parts = Parts;
    }

    public String getNameClient() {
        return this.client;
    }

    public ContactPersoon getContactPersoon() {
        return this.contactPersoon;
    }

    public ShippingAddres getNameShippingAddres() {
        return this.shipping;
    }

    public String getReparationDescription() {
        return this.comments;
    }

    public ArrayList getWorkperformed() {
        return this.operations;
    }

    public ArrayList getParts() {
        return this.parts;
    }
}
