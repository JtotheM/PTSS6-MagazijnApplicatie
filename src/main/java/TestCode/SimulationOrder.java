package TestCode;

import javax.jms.*;

import JMS.JmsMessageSender;
import Models.OfferRequest;
import Models.WorkRequest;
import com.google.gson.Gson;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Laurence on 20/6/2015.
 */

public class SimulationOrder {

    private static HashMap<String, String> channels;
    private static ApplicationContext ctx;
    private static JmsMessageSender jmsMessageSender;

    public static void main(String[] args) {

        while(true) {
            simulation();
        }
    }

    private static void simulation() {
        //Get message system
        SimulationOrder.ctx = new ClassPathXmlApplicationContext("app-context.xml");
        SimulationOrder.jmsMessageSender = (JmsMessageSender) SimulationOrder.ctx.getBean("jmsMessageSender");

        String message = "";
        String jmsMessageID = "";
        Integer price = 10;
        TextMessage receive = null;

        try {

            //Get channels
            Queue warehouseRequest = new ActiveMQQueue("WarehouseRequest");
            Queue mainOfficeRequest = new ActiveMQQueue("MainOfficeRequest");
            Queue orderResponse = new ActiveMQQueue("OrderResponseFontys");

            //Send a order
            ArrayList<String> test = new ArrayList<String>();
            test.add("test");
            test.add("test2");

            OfferRequest requestObject = new OfferRequest("test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", test, test);
            Gson gson = new Gson();
            sendMessage("OrderRequestFontys", gson.toJson(requestObject), "-1");

            //Wait for the warehouse request
            for (int i = 0; i < 2; i++) {
                receive = jmsMessageSender.receive(warehouseRequest);
                jmsMessageID = receive.getJMSMessageID();
                WorkRequest request = gson.fromJson(receive.getText(), WorkRequest.class);

                sendMessage("WarehouseResponse", price.toString(), jmsMessageID);
            }

            //Wait for the main office request
            for (int i = 0; i < 2; i++) {
                receive = jmsMessageSender.receive(mainOfficeRequest);
                jmsMessageID = receive.getJMSMessageID();
                WorkRequest request = gson.fromJson(receive.getText(), WorkRequest.class);

                sendMessage("MainOfficeResponse", price.toString(), jmsMessageID);
            }

            //Wait for the total order
            receive = jmsMessageSender.receive(orderResponse);
            String result = receive.getText();

            System.out.println(result);

            //Close the connection
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