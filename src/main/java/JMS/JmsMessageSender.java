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
            BroakerMessageCreator messageCreator = new BroakerMessageCreator(text,correlationId);
            this.jmsTemplate.send(dest, messageCreator);

            //Retry on failed message
            Message message = messageCreator.getMessage();
            if(message == null) {
                return this.send(dest,text,correlationId);
            }

            messageId = message.getJMSMessageID();

        } catch (JMSException e) {
            e.printStackTrace();
        }

        return messageId;
    }

    public TextMessage receive(final Destination dest) {
        while (true) {

            //this.jmsTemplate.setReceiveTimeout(250);
            TextMessage receive = (TextMessage) this.jmsTemplate.receive(dest);
            if (receive == null) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            return receive;
        }
    }
}