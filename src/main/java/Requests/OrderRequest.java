package Requests;

import JMS.Main;
import Models.OfferRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Laurence on 20/6/2015.
 */
public class OrderRequest extends Request {

    public OrderRequest(String request, String correlationId) {
        super(request, correlationId);
        process();
    }

    private void process() {
        Gson gson = new Gson();
        OfferRequest orderRequest = gson.fromJson(this.getRequest(), OfferRequest.class);

        ArrayList<BroakerCost> prices = new ArrayList<BroakerCost>();

        //Ask the warehouse if there are new items
        ArrayList<String> parts = orderRequest.getParts();
        ArrayList<RequestValue> warehouseRequests = new ArrayList<RequestValue>();
        for (String part : parts) {
            String json = gson.toJson(part);
            String warehouseRequest = Main.sendMessage("WarehouseRequest", json, "-1");
            warehouseRequests.add(new RequestValue(warehouseRequest,part));
        }

        //Request all Warehouse prices
        for( RequestValue requestValue : warehouseRequests) {
            Integer price = WarehouseRequest.getPrice(requestValue.getMessageId());
            prices.add(new BroakerCost(price,"Part: " + requestValue.getValue()));
        }

        //Ask what it cost to do the labor
        ArrayList<String> operations = orderRequest.getOpperations();
        ArrayList<RequestValue> operationsRequest = new ArrayList<RequestValue>();
        for (String operation : operations) {
            String json = gson.toJson(operation);
            String operationRequest = Main.sendMessage("MainOfficeRequest", json, "-1");
            operationsRequest.add(new RequestValue(operationRequest,operation));
        }

        //Request all Warehouse prices
        for( RequestValue requestValue : operationsRequest) {
            Integer price = MainOfficeRequest.getPrice(requestValue.getMessageId());
            prices.add(new BroakerCost(price,"Labor: " + requestValue.getValue()));
        }

        this.setResponse(gson.toJson(prices));
    }
}
