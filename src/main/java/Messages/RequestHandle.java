package Messages;

import Requests.MainOfficeRequest;
import Requests.OrderRequest;
import Requests.WarehouseRequest;

/**
 * Created by Laurence on 20/6/2015.
 */
public class RequestHandle {

    public String handleMessage(String request, String channel, String correlationId) {

        String response = "";
        if (channel.equals("OrderRequest") || channel.equals("OrderRequestFontys")) {
            OrderRequest orderRequest = new OrderRequest(request, correlationId);
            response = orderRequest.getResponse();
        } else if (channel.equals("WarehouseResponse")) {
            WarehouseRequest warehouseRequest = new WarehouseRequest(request, correlationId);
            response = warehouseRequest.getResponse();

        } else if (channel.equals("MainOfficeResponse")) {
            MainOfficeRequest mainOfficeRequest = new MainOfficeRequest(request, correlationId);
            response = mainOfficeRequest.getResponse();
        } else {
            System.out.println("Channel is unknown: " + channel);
            response = "Error";
        }

        System.out.println(request + " on channel " + channel + " response: " + response);

        return response;
    }
}
