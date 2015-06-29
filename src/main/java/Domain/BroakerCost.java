package Domain;

/**
 * BroakerCost
 * created by @author RY Jin on Jun 28, 2015
 */
public class BroakerCost {
    private int prices;
    private String operation;
    
    public BroakerCost(){
        
    }

    public BroakerCost(int prices, String operation) {
        this.prices = prices;
        this.operation = operation;
    }

    public int getPrices() {
        return prices;
    }

    public String getOperation() {
        return operation;
    }

    public void setPrices(int prices) {
        this.prices = prices;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
