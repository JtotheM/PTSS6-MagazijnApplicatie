package Models;

import java.util.ArrayList;

/**
 * Created by Laurence on 20/6/2015.
 */
public class OfferRequest {

    private final String client;
    private final ContactPersoon contactPersoon;
    private final Address shipping;
    private final String comments;

    private final ArrayList<String> operations;
    private final ArrayList<String> parts;

    public OfferRequest(String client, ContactPersoon contactPersoon, Address shipping, String comments, ArrayList<String> operations, ArrayList<String> parts) {
        this.client = client;
        this.contactPersoon = contactPersoon;
        this.shipping = shipping;
        this.comments = comments;
        this.operations = operations;
        this.parts = parts;
    }

    public String getClient() {
        return client;
    }

    public ContactPersoon getContactPersoon() {
        return contactPersoon;
    }

    public Address getShipping() {
        return shipping;
    }

    public String getComments() {
        return comments;
    }

    public ArrayList<String> getOperations() {
        return operations;
    }

    public ArrayList<String> getParts() {
        return parts;
    }
}
