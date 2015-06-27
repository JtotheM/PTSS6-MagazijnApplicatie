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

    private Magazijn beheer;

    private Connection connection;
    private Session session;

    private Queue responseQueue;
    private Queue requestQueue;

    private MessageProducer messageProducer;
    private MessageConsumer messageConsumer;

    public JMSConnectie(Magazijn beheer) {
        try {
            this.beheer = beheer;

            ConnectionFactory factory = getConnectionFactory();
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            responseQueue = session.createQueue("WarehouseResponse");
            requestQueue = session.createQueue("WarehouseRequest");

            messageProducer = session.createProducer(responseQueue);
            messageConsumer = session.createConsumer(requestQueue);

            messageConsumer.setMessageListener(new MessageListener() {
                public void onMessage(Message msg) {
                    try {
                        onReceiveMessage((TextMessage) msg);
                    } catch (JMSException ex) {
                        Logger.getLogger(JMSConnectie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            connection.start();
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
        messageProducer.send(textMessage);
    }

    private void onReceiveMessage(TextMessage textMessage) throws JMSException {
        Gson gson = new Gson();
        OfferRequest offerRequest = gson.fromJson(textMessage.getText(), OfferRequest.class);

        beheer.VoegKlantToe(offerRequest.getClientName(), offerRequest.getShippingAddres());

        sendResponse(textMessage, "1000");
    }
}
