package Messages;

import Requests.*;

/**
 * Created by Laurence on 20/6/2015.
 */
public class RequestHandle {

    public String handleMessage(String request, String channel, String correlationId) {

        String response;

        //Handle each chanel
        switch (channel) {
            case "OrderRequest":
            case "OrderRequestFontys": {
                OrderRequest orderRequest = new OrderRequest(request, correlationId);
                response = orderRequest.getResponse();
                break;
            }
            case "WarehouseResponse": {
                WarehouseRequest warehouseRequest = new WarehouseRequest(request, correlationId);
                response = warehouseRequest.getResponse();
                break;
            }
            case "MainOfficeResponse": {
                MainOfficeRequest mainOfficeRequest = new MainOfficeRequest(request, correlationId);
                response = mainOfficeRequest.getResponse();
                break;
            }
            case "orderStatus": {
                OrderStatus warehouseRequest = new OrderStatus(request, correlationId);
                response = warehouseRequest.getResponse();
                break;
            }
            case "WarehouseStatusResponse": {
                WarehouseStatusRequest warehouseStatusRequest = new WarehouseStatusRequest(request, correlationId);
                response = warehouseStatusRequest.getResponse();
                break;
            }
            default:
                System.out.println("Channel is unknown: " + channel);
                response = "Error";
                break;
        }

        System.out.println(request + " on channel " + channel + " response: " + response);

        return response;
    }
}
