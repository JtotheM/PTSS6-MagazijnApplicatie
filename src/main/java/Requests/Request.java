package Requests;

/**
 * Created by Laurence on 20/6/2015.
 */
public class Request {
    private String request;
    private String response;
    private String correlationId;

    public Request(String request, String correlationId) {
        this.request = request;
        this.correlationId = correlationId;
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
