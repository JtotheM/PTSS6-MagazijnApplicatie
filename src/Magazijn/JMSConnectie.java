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

    private MessageProducer messageProducer;
    private MessageConsumer messageConsumer;

    public JMSConnectie(Magazijn beheer) {
        try {
            this.gson = new Gson();
            this.beheer = beheer;

            ConnectionFactory factory = getConnectionFactory();
            this.connection = factory.createConnection();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            this.responseQueue = session.createQueue("WarehouseResponse");
            this.requestQueue = session.createQueue("WarehouseRequest");

            this.messageProducer = session.createProducer(responseQueue);
            this.messageConsumer = session.createConsumer(requestQueue);

            this.messageConsumer.setMessageListener(new MessageListener() {
                public void onMessage(Message msg) {
                    try {
                        onReceiveMessage((TextMessage) msg);
                    } catch (JMSException ex) {
                        Logger.getLogger(JMSConnectie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            this.connection.start();
        } catch (JMSException ex) {
            Logger.getLogger(JMSConnectie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
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

    private void sendResponse(Message originalMessage, String message) throws JMSException {
        TextMessage textMessage = session.createTextMessage(message);
        textMessage.setJMSCorrelationID(originalMessage.getJMSMessageID());
        sendMessage(textMessage);
    }

    private void sendMessage(TextMessage textMessage) throws JMSException {
        this.messageProducer.send(textMessage);
    }

    private void onReceiveMessage(TextMessage textMessage) throws JMSException {

        OfferRequest offerRequest = this.gson.fromJson(textMessage.getText(), OfferRequest.class);

        String clientName = offerRequest.getClientName();
        String shippingAddress = offerRequest.getShippingAddres();

        if (clientName != null && shippingAddress != null
                && !clientName.isEmpty() && !shippingAddress.isEmpty()) {
            this.beheer.VoegKlantToe(offerRequest.getClientName(), offerRequest.getShippingAddres());

            sendResponse(textMessage, "1000");
        }
    }
}
