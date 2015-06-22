package JMS;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.Serializable;

/**
 * Created by Laurence on 22/6/2015.
 */
public class BroakerMessageCreator implements MessageCreator, Serializable {
    private static final long serialVersionUID = 1198353391423668110L;

    private String messageString;

    private Message message;

    public BroakerMessageCreator(String messageString)
    {
        this.messageString = messageString;
    }

    @Override
    public Message createMessage(Session session) throws JMSException
    {
        message = session.createTextMessage(messageString);
        return message;
    }

    public Message getMessage() {
        return message;
    }
}
