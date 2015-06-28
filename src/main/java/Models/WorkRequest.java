package Models;

/**
 * Created by Laurence on 20/6/2015.
 */
public class WorkRequest {

    private final String client;
    private final ContactPersoon contactPersoon;
    private final Address shipping;
    private final String part;

    public WorkRequest(String client, ContactPersoon contactPersoon, Address shipping, String part) {
        this.client = client;
        this.contactPersoon = contactPersoon;
        this.shipping = shipping;
        this.part = part;
    }

    public WorkRequest(OfferRequest orderRequest, String part) {
        this.client = orderRequest.getClient();
        this.contactPersoon = orderRequest.getContactPersoon();
        this.shipping = orderRequest.getShipping();
        this.part = part;
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

    public String getPart() {
        return part;
    }
}
