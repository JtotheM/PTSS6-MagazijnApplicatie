package Requests;

import Models.OfferRequest;
import com.google.gson.Gson;

/**
 * Created by Laurence on 20/6/2015.
 */
public class OrderRequest extends Request {

    public OrderRequest(String request) {
        super(request);
        process();
    }

    private void process() {
        Gson gson = new Gson();
        OfferRequest orderRequest = gson.fromJson(this.getRequest(), OfferRequest.class);

        //Request product status. We have to reserve them. When not found we have to buy them.


        this.setResponse(this.getRequest());
    }
}
