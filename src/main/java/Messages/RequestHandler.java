package Messages;

import Requests.MainOfficeRequest;
import Requests.OrderRequest;
import Requests.WarehouseRequest;

/**
 * Created by Laurence on 20/6/2015.
 */
public class RequestHandler {

    public String handleMessage(String request, String channel) {

        String response;
        switch (channel) {
            case "OrderRequest":
                OrderRequest orderRequest = new OrderRequest(request);
                response = orderRequest.getResponse();
                break;

            case "WarehouseRequest":
                WarehouseRequest warehouseRequest = new WarehouseRequest(request);
                response = warehouseRequest.getResponse();
                break;

            case "MainOfficeRequest":
                MainOfficeRequest mainOfficeRequest = new MainOfficeRequest(request);
                response = mainOfficeRequest.getResponse();
                break;

            default:
                System.out.println("Channel is unknown: " + channel);
                response = "Error";
                break;
        }

        System.out.println(request + " on channel " + channel + " response: " + response);

        return response;
    }
}
