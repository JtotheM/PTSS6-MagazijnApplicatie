package Domain;

/**
 * ShippingAddres created by @author RY Jin on Jun 20, 2015
 */
public class ShippingAddres {

    private String street;
    private String number;
    private String postalCode;
    private String place;

    public ShippingAddres(String street, String number, String postalcode, String place) {
        this.street = street;
        this.number = number;
        this.postalCode = postalcode;
        this.place = place;
    }

    public ShippingAddres() {

    }

    public void setStreet(String Street) {
        this.street = Street;
    }

    public void setNumber(String Number) {
        this.number = Number;
    }

    public void setPostalCode(String PostalCode) {
        this.postalCode = PostalCode;
    }

    public void setPlace(String Place) {
        this.place = Place;
    }

    public String getStreet() {
        return this.street;
    }

    public String getNumber() {
        return this.number;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getPlace() {
        return this.place;
    }
}
