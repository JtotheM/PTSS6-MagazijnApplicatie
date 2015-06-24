package Requests;

import JMS.Main;
import Models.OfferRequest;
import Models.OfferRequestProcessed;
import Models.WorkRequest;
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
        //Get the request
        Gson gson = new Gson();
        OfferRequest orderRequest = gson.fromJson(this.getRequest(), OfferRequest.class);

        //Ask warehouse and main office for prices
        ArrayList<BroakerCost> prices = new ArrayList<BroakerCost>();
        requestWarehousePrice(orderRequest, prices);
        requestMainOfficePrice(gson, orderRequest, prices);

        //Return result
        OfferRequestProcessed offerRequestProcessed = new OfferRequestProcessed(orderRequest, prices);
        this.setResponse(gson.toJson(offerRequestProcessed));
    }

    private void requestMainOfficePrice(Gson gson, OfferRequest orderRequest, ArrayList<BroakerCost> prices) {

        //Get operation cost
        ArrayList<String> operations = orderRequest.getOpperations();
        ArrayList<RequestValue> operationsRequest = new ArrayList<RequestValue>();
        for (String operation : operations) {
            WorkRequest workRequest = new WorkRequest(orderRequest,operation);
            String json = gson.toJson(workRequest);

            String operationRequest = Main.sendMessage("MainOfficeRequest", json, "-1");
            operationsRequest.add(new RequestValue(operationRequest,operation));
        }

        //Request all Warehouse prices
        for( RequestValue requestValue : operationsRequest) {
            Integer price = MainOfficeRequest.getPrice(requestValue.getMessageId());
            prices.add(new BroakerCost(price,"Labor: " + requestValue.getValue()));
        }
    }

    private void requestWarehousePrice(OfferRequest orderRequest, ArrayList<BroakerCost> prices) {
        Gson gson = new Gson();

        //Ask the main office for process
        ArrayList<String> parts = orderRequest.getParts();
        ArrayList<RequestValue> warehouseRequests = new ArrayList<RequestValue>();
        for (String part : parts) {
            WorkRequest workRequest = new WorkRequest(orderRequest,part);
            String json = gson.toJson(workRequest);

            String warehouseRequest = Main.sendMessage("WarehouseRequest", json, "-1");
            warehouseRequests.add(new RequestValue(warehouseRequest,part));
        }

        //Request all main office prices
        for( RequestValue requestValue : warehouseRequests) {
            Integer price = WarehouseRequest.getPrice(requestValue.getMessageId());
            prices.add(new BroakerCost(price,"Part: " + requestValue.getValue()));
        }
    }
}
