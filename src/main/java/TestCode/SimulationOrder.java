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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Laurence on 20/6/2015.
 */
public class SimulationOrder {

    private static ApplicationContext ctx;
    private static JmsMessageSender jmsMessageSender;

    public static void main(String[] args) {

        while (true) {
            simulation();
        }
    }

    private static void simulation() {
        //Get message system
        SimulationOrder.ctx = new ClassPathXmlApplicationContext("app-context.xml");
        SimulationOrder.jmsMessageSender = (JmsMessageSender) SimulationOrder.ctx.getBean("jmsMessageSender");

        String jmsMessageID = "";
        Integer price = 10;
        TextMessage receive = null;

        Queue warehouseRequest = new ActiveMQQueue("WarehouseRequest");
        Queue warehouseResponse = new ActiveMQQueue("WarehouseResponse");
        
        ArrayList<String> test = new ArrayList<>();
        test.add("test");
        test.add("test2");
        OfferRequest requestObject = new OfferRequest("test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", test, test);
        Gson gson = new Gson();
        sendMessage("WarehouseRequest", gson.toJson(requestObject), "-1");
        ((ClassPathXmlApplicationContext) ctx).close();
    }

    public static String sendMessage(String sendChannel, String message, String jmsMessageID) {
        Queue queueSend = new ActiveMQQueue(sendChannel);
        Logger.getLogger(SimulationOrder.class.getName()).log(Level.SEVERE, message);
        return jmsMessageSender.send(queueSend, message, jmsMessageID);
    }
}
