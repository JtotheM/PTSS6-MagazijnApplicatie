package Models;

import java.util.ArrayList;

/**
 * Created by Laurence on 24/6/2015.
 */
public class OfferResponse {

    private final String nameClient;
    private final Address shippingAddress;
    private final String reparationDescription;
    private final ArrayList<WorkPerformed> workPerformed;
    private final ArrayList<Part> parts;
    private final String bankAccount;

    public OfferResponse(String nameClient, Address shippingAddress, String reparationDescription, ArrayList<WorkPerformed> workPerformed, ArrayList<Part> parts, String bankAccount) {
        this.nameClient = nameClient;
        this.shippingAddress = shippingAddress;
        this.reparationDescription = reparationDescription;
        this.workPerformed = workPerformed;
        this.parts = parts;
        this.bankAccount = bankAccount;
    }

    public OfferResponse(OfferRequest orderRequest, ArrayList<WorkPerformed> workPerformed, ArrayList<Part> parts) {
        this.nameClient = orderRequest.getClient();
        this.shippingAddress = orderRequest.getShipping();
        this.workPerformed = workPerformed;
        this.parts = parts;
        this.reparationDescription = "WHERE TO GET DESCRIPTION?";
        this.bankAccount = "WHERE TO GET BANKACCOUNT?";
    }

    public String getNameClient() {
        return nameClient;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public String getReparationDescription() {
        return reparationDescription;
    }

    public ArrayList<WorkPerformed> getWorkPerformed() {
        return workPerformed;
    }

    public ArrayList<Part> getParts() {
        return parts;
    }

    public String getBankAccount() {
        return bankAccount;
    }
}
