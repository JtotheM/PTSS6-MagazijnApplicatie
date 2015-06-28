package Requests;

/**
 * Created by Laurence on 22/6/2015.
 */
public class BroakerCost {

    private Integer prices;
    private String operation;

    public BroakerCost(Integer prices, String operation) {
        this.prices = prices;
        this.operation = operation;
    }

    public Integer getPrices() {
        return prices;
    }

    public void setPrices(Integer prices) {
        this.prices = prices;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
