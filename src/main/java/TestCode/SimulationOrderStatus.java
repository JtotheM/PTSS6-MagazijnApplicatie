package TestCode;

import javax.jms.*;

import JMS.JmsMessageSender;
import Models.OfferRequest;
import Models.WorkRequest;
import com.google.gson.Gson;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Laurence on 20/6/2015.
 */

public class SimulationOrderStatus {

    private static ApplicationContext ctx;
    private static JmsMessageSender jmsMessageSender;

    public static void main(String[] args) {

        while(true) {
            simulation();
        }
    }

    private static void simulation() {

        //Get message system
        SimulationOrderStatus.ctx = new ClassPathXmlApplicationContext("app-context.xml");
        SimulationOrderStatus.jmsMessageSender = (JmsMessageSender) SimulationOrderStatus.ctx.getBean("jmsMessageSender");

        String jmsMessageID = "";
        TextMessage receive = null;

        try {

            //Get channels
            Queue orderStatusResponse = new ActiveMQQueue("orderStatusResponse");
            Queue warehouseStatusRequest = new ActiveMQQueue("WarehouseStatusRequest");

            //Send a order
            Gson gson = new Gson();
            sendMessage("orderStatus", gson.toJson("SomeRequest"), "-1");

            //Do the warehouse request
            receive = jmsMessageSender.receive(warehouseStatusRequest);
            jmsMessageID = receive.getJMSMessageID();
            String request = receive.getText();

            sendMessage("WarehouseStatusResponse", request, jmsMessageID);

            //Wait for the warehouse request
            receive = jmsMessageSender.receive(orderStatusResponse);
            jmsMessageID = receive.getJMSMessageID();
            String response = receive.getText();

            System.out.println(response);

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