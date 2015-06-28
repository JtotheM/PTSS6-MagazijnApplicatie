package Requests;

import java.util.ArrayList;

/**
 * Created by Laurence on 26/6/2015.
 */
public class WarehouseStatusRequest extends Request {

    private static final ArrayList<RequestValue> responses = new ArrayList<>();

    public WarehouseStatusRequest(String request, String correlationId) {
        super(request, correlationId);
        process();
    }

    private void process() {

        //Process prices
        String request = this.getRequest();
        responses.add(new RequestValue(this.getCorrelationId(), request));
    }

    public static String getStatus(String messageId) {

        while (true) {
            try {
                for (RequestValue status : WarehouseStatusRequest.responses) {
                    if (status.getMessageId().equals(messageId)) {
                        return status.getValue();
                    }
                }

                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}
