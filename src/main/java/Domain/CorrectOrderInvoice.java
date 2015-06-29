package Domain;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * CorrectOrderInvoice created by @author RY Jin on Jun 28, 2015
 */
public class CorrectOrderInvoice {

    private final String nameClient;
    private final ShippingAddres shippingAddress;
    private final String reparationDescription;
    private final ArrayList<WorkPerformedInfo> workPerformed;
    private final ArrayList<PartInfo> parts;
    private final String bankAccount;

    public CorrectOrderInvoice(String nameClient, ShippingAddres shippingAddress, String reparationDescription, ArrayList<WorkPerformedInfo> workPerformed, ArrayList<PartInfo> parts, String bankAccount) {
        this.nameClient = nameClient;
        this.shippingAddress = shippingAddress;
        this.reparationDescription = reparationDescription;
        this.workPerformed = workPerformed;
        this.parts = parts;
        this.bankAccount = bankAccount;
    }

    public String getNameClient() {
        return nameClient;
    }

    public ShippingAddres getShippingAddress() {
        return shippingAddress;
    }

    public String getReparationDescription() {
        return reparationDescription;
    }

    public ArrayList<WorkPerformedInfo> getWorkPerformed() {
        return workPerformed;
    }

    public ArrayList<PartInfo> getParts() {
        return parts;
    }

    public String getBankAccount() {
        return bankAccount;
    }
}
