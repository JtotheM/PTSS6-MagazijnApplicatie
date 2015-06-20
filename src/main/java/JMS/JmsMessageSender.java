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

    public void send(final Destination dest, final String text, final String correlationId) {

        this.jmsTemplate.send(dest, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(text);
                message.setJMSCorrelationID(correlationId);
                return message;
            }
        });
    }

    public TextMessage receive(final Destination dest) {
        TextMessage receive = (TextMessage) this.jmsTemplate.receive(dest);
        return receive;
    }
}