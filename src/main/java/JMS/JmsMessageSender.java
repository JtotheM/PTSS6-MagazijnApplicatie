package JMS;

import javax.jms.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class JmsMessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public String send(final Destination dest, final String text, final String correlationId) {

        String messageId = "";
        try {
            BroakerMessageCreator messageCreator = new BroakerMessageCreator(text);
            messageId = messageCreator.getMessage().getJMSMessageID();
            this.jmsTemplate.send(dest, messageCreator);

        } catch (JMSException e) {
            e.printStackTrace();
        }

        return messageId;
    }

    public TextMessage receive(final Destination dest) {
        TextMessage receive = (TextMessage) this.jmsTemplate.receive(dest);
        return receive;
    }
}