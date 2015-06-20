package Requests;

/**
 * Created by Laurence on 20/6/2015.
 */
public class Request {
    private String request;
    private String response;

    public Request(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public String getResponse() {
        return response;
    }

    protected void setResponse(String response) {
        this.response = response;
    }
}
