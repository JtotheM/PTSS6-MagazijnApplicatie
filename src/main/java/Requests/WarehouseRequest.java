package Requests;

/**
 * Created by Laurence on 20/6/2015.
 */
public class WarehouseRequest extends Request{

    public WarehouseRequest(String request,String correlationId) {
        super(request,correlationId);
        process();
    }

    private void process() {
        this.setResponse(this.getRequest());
    }

    public static Integer getPrice(String messageId) {


    }
}
