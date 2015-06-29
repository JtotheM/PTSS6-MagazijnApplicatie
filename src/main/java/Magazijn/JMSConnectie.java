package Magazijn;

import domain.OfferRequest;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import com.google.gson.Gson;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import DAO.Magazijn;

public class JMSConnectie {

    private Gson gson;
    private Magazijn beheer;

    private Connection connection;
    private Session session;

    private Queue responseQueue;
    private Queue requestQueue;

    private Queue statusResponseQueue;
    private Queue statusRequestQueue;

    private MessageProducer messageProducer;
    private MessageConsumer messageConsumer;

    private MessageProducer statusMessageProducer;
    private MessageConsumer statusMessageConsumer;

    public JMSConnectie(Magazijn beheer) {
        try {
            this.gson = new Gson();
            this.beheer = beheer;

            ConnectionFactory factory = getConnectionFactory();
            this.connection = factory.createConnection();
            this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            this.responseQueue = this.session.createQueue("WarehouseResponse");
            this.requestQueue = this.session.createQueue("WarehouseRequest");
            this.statusResponseQueue = this.session.createQueue("WarehouseStatusResponse");
            this.statusRequestQueue = this.session.createQueue("WarehouseStatusRequest");

            this.messageProducer = this.session.createProducer(this.responseQueue);
            this.messageConsumer = this.session.createConsumer(this.requestQueue);

            this.statusMessageProducer = this.session.createProducer(this.statusResponseQueue);
            this.statusMessageConsumer = this.session.createConsumer(this.statusRequestQueue);

            this.messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message msg) {
                    try {
                        onWarehouseRequest((TextMessage) msg);
                    } catch (JMSException ex) {
                        Logger.getLogger(JMSConnectie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            this.statusMessageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message msg) {
                    try {
                        onWarehouseStatusRequest((TextMessage) msg);
                    } catch (JMSException ex) {
                        Logger.getLogger(JMSConnectie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            this.connection.start();
        } catch (JMSException | NamingException ex) {
            Logger.getLogger(JMSConnectie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ConnectionFactory getConnectionFactory() throws NamingException {
        Properties props = new Properties();
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://86.86.7.186:61616");

        Context jndiContext = new InitialContext(props);
        ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");

        return connectionFactory;
    }

    private void sendResponse(MessageProducer messageProducer, Message originalMessage, String message) throws JMSException {
        TextMessage textMessage = session.createTextMessage(message);
        textMessage.setJMSCorrelationID(originalMessage.getJMSMessageID());
        sendMessage(messageProducer, textMessage);
    }

    private void sendMessage(MessageProducer messageProducer, TextMessage textMessage) throws JMSException {
        messageProducer.send(textMessage);
    }

    private void onWarehouseRequest(TextMessage textMessage) throws JMSException {
        OfferRequest offerRequest = this.gson.fromJson(textMessage.getText(), OfferRequest.class);

        String clientName = offerRequest.getClient();
        String shippingAddress = offerRequest.getShipping().toString();

        if (clientName != null && shippingAddress != null
                && !clientName.isEmpty() && !shippingAddress.isEmpty()) {
            this.beheer.VoegKlantToe(clientName, shippingAddress);

            sendResponse(this.messageProducer, textMessage, "1000");
            return;
        }

        sendResponse(this.messageProducer, textMessage, "0");
    }

    private void onWarehouseStatusRequest(TextMessage textMessage) throws JMSException {
        sendResponse(this.statusMessageProducer, textMessage, "ALMOST DONE");
    }
}
