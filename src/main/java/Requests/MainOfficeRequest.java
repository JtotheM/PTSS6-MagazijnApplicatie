package Requests;

import java.util.ArrayList;

/**
 * Created by Laurence on 20/6/2015.
 */
public class MainOfficeRequest extends Request {

    private static ArrayList<RequestValue> broakerCosts = new ArrayList<RequestValue>();

    public MainOfficeRequest(String request, String correlationId) {
        super(request, correlationId);
        process();
    }

    private void process() {

        //Process response. Add the offer in memory
        String request = this.getRequest();

        Integer price = 0;
        try {
            price = Integer.parseInt(request);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        broakerCosts.add(new RequestValue(this.getCorrelationId(), price.toString()));
    }

    public static Integer getPrice(String messageId) {

        //Wait for the correct item
        while (true) {
            try {
                for (RequestValue cost : MainOfficeRequest.broakerCosts) {
                    if (cost.getMessageId().equals(messageId)) {
                        return Integer.parseInt(cost.getValue());
                    }
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
