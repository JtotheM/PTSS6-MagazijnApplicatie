/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author User
 */
public class Address {

    private String street;
    private String number;
    private String postalCode;
    private String place;

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return this.street + " " + this.number;
    }
}
