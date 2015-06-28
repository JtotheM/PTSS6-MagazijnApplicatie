package Requests;

import JMS.Main;
import Models.OfferRequest;
import Models.OfferResponse;
import Models.Part;
import Models.WorkPerformed;
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
        ArrayList<Part> parts = new ArrayList<>();
        ArrayList<WorkPerformed> workPerformed = new ArrayList<>();

        requestWarehousePrice(orderRequest, parts);
        requestMainOfficePrice(gson, orderRequest, workPerformed);

        //Return result
        OfferResponse offerRequestProcessed = new OfferResponse(orderRequest, workPerformed, parts);
        this.setResponse(gson.toJson(offerRequestProcessed));
    }

    private void requestMainOfficePrice(Gson gson, OfferRequest orderRequest, ArrayList<WorkPerformed> workPerformed) {

        //Get operation cost
        ArrayList<String> operations = orderRequest.getOperations();
        ArrayList<RequestValue> operationsRequest = new ArrayList<>();
        for (String operation : operations) {
            WorkRequest workRequest = new WorkRequest(orderRequest, operation);
            String json = gson.toJson(workRequest);

            String operationRequest = Main.sendMessage("MainOfficeRequest", json, "-1");
            operationsRequest.add(new RequestValue(operationRequest, operation));
        }

        //Request all Warehouse prices
        for (RequestValue requestValue : operationsRequest) {
            Integer price = MainOfficeRequest.getPrice(requestValue.getMessageId());
            workPerformed.add(new WorkPerformed("Labor: " + requestValue.getValue(), price));
        }
    }

    private void requestWarehousePrice(OfferRequest orderRequest, ArrayList<Part> parts) {
        Gson gson = new Gson();

        //Ask the main office for process
        ArrayList<String> partIdentifiers = orderRequest.getParts();
        ArrayList<RequestValue> warehouseRequests = new ArrayList<>();
        for (String partIdentifier : partIdentifiers) {
            WorkRequest workRequest = new WorkRequest(orderRequest, partIdentifier);
            String json = gson.toJson(workRequest);

            String warehouseRequest = Main.sendMessage("WarehouseRequest", json, "-1");
            warehouseRequests.add(new RequestValue(warehouseRequest, partIdentifier));
        }

        //Request all main office prices
        for (RequestValue requestValue : warehouseRequests) {
            Integer price = WarehouseRequest.getPrice(requestValue.getMessageId());
            parts.add(new Part("Part: " + requestValue.getValue(), price));
        }
    }
}
