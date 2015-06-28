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
public class Part {

    private final String description;
    private final int price; // CENTS!!!

    public Part(String description, int price) {
        this.description = description;
        this.price = price;
    }
   
    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
