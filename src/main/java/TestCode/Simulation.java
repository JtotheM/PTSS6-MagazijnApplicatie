package JMS;

import javax.annotation.Resource;
import javax.jms.*;

import Messages.RequestHandler;
import Models.OfferRequest;
import com.google.gson.Gson;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Laurence on 20/6/2015.
 */

public class Simulation {

    private static RequestHandler requestHandler = new RequestHandler();
    private static HashMap<String, String> channels;
    private static ApplicationContext ctx;
    private static JmsMessageSender jmsMessageSender;

    public static void main(String[] args) {

        Simulation.ctx = new ClassPathXmlApplicationContext("app-context.xml");
        Simulation.jmsMessageSender = (JmsMessageSender) Simulation.ctx.getBean("jmsMessageSender");

        String message = "";
        String jmsMessageID = "";
        Integer price = 10;
        TextMessage receive = null;

        try {

            //Get channels
            Queue warehouseRequest = new ActiveMQQueue("WarehouseRequest");
            Queue mainOfficeRequest = new ActiveMQQueue("MainOfficeRequest");
            Queue orderResponse = new ActiveMQQueue("OrderResponse");
            Queue OrderRequest = new ActiveMQQueue("OrderRequest");

            //Send a order
            ArrayList<String> test = new ArrayList<String>();
            test.add("test");
            test.add("test2");

            OfferRequest requestObject = new OfferRequest("test","test","test","test","test","test","test","test","test","test","test",test,test);
            Gson gson = new Gson();
            sendMessage("OrderRequest", gson.toJson(requestObject), "-1");

            //Test
            Simulation.jmsMessageSender = (JmsMessageSender) Simulation.ctx.getBean("jmsMessageSender");
            receive = jmsMessageSender.receive(OrderRequest, "");
            jmsMessageID = receive.getJMSMessageID();

            //Wait for the warehouse request
            receive = jmsMessageSender.receive(warehouseRequest, "");
            jmsMessageID = receive.getJMSMessageID();

            sendMessage("WarehouseResponse",price.toString(),jmsMessageID);

            //Wait for the main office request
            receive = jmsMessageSender.receive(mainOfficeRequest, "");
            jmsMessageID = receive.getJMSMessageID();

            sendMessage("MainOfficeResponse",price.toString(),jmsMessageID);

            //Wait for the total order
            receive = jmsMessageSender.receive(orderResponse,"");
            String result = receive.getText();

            ((ClassPathXmlApplicationContext) ctx).close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static String sendMessage(String sendChannel, String message, String jmsMessageID) {
        Queue queueSend = new ActiveMQQueue(sendChannel);
        return jmsMessageSender.send(queueSend, message, jmsMessageID);
    }
}