package Domain;

/**
 *
 * @author RY Jin
 */
public class WorkPerformedInfo {

    private String description;
    private int price;
    
    public WorkPerformedInfo(String description) {
        this.description = description;
        price = 0;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
    }
    
    
}
