package Requests;

/**
 * Created by Laurence on 20/6/2015.
 */
public class MainOfficeRequest extends Request{
    public MainOfficeRequest(String request) {
        super(request);
        process();
    }

    private void process() {
        this.setResponse(this.getRequest());
    }
}
