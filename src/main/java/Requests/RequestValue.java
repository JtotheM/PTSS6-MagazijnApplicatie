package Requests;

/**
 * Created by Laurence on 22/6/2015.
 */
public class RequestValue {

    private final String messageId;
    private final String value;

    public RequestValue(String messageId, String value) {
        this.messageId = messageId;
        this.value = value;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getValue() {
        return value;
    }
}
