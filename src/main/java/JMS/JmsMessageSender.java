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
            this.jmsTemplate.send(dest, messageCreator);
            messageId = messageCreator.getMessage().getJMSMessageID();

        } catch (JMSException e) {
            e.printStackTrace();
        }

        return messageId;
    }

    public TextMessage receive(final Destination dest, String debug) {
        while (true) {

            if (!debug.isEmpty())
                System.out.println(debug);


            this.jmsTemplate.setReceiveTimeout(250);
            TextMessage receive = (TextMessage) this.jmsTemplate.receive(dest);
            if (receive == null) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            return receive;
        }
    }
}