package Requests;

import JMS.Main;

/**
 * Created by Laurence on 26/6/2015.
 */
public class OrderStatus extends Request {


    public OrderStatus(String request, String correlationId) {
        super(request, correlationId);
        process();
    }

    private void process() {

        //Process prices
        String request = this.getRequest();

        //Ask the main office for process
        String warehouseRequest = Main.sendMessage("WarehouseStatusRequest", request, "-1");

        String status = WarehouseStatusRequest.getStatus(warehouseRequest);
        this.setResponse(status);
    }
}
