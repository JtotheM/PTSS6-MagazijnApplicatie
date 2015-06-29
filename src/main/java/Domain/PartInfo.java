package Domain;


/**
 *
 * @author RY Jin
 */
public class PartInfo {

    private String description;
    private int price;

    public PartInfo(String name) {
        this.description = name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.description = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
