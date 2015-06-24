package TestCode;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;

import JMS.JmsMessageSender;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestSend {


    public static void main(String[] args) {

        try {

            ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
            JmsMessageSender jmsMessageSender = (JmsMessageSender) ctx.getBean("jmsMessageSender");

            Queue queueRequest = new ActiveMQQueue("MessageRequest");
            Queue queueSend = new ActiveMQQueue("MessageResponse");

            //TEST CODE! REMOVE PLZ!
            testMessage(jmsMessageSender, queueRequest);

            while(true) {
                TextMessage receive = jmsMessageSender.receive(queueRequest);
                if( receive == null)
                    continue;

                String message = receive.getText();
                String jmsMessageID = receive.getJMSMessageID();

                //Get commands from string
                String[] commands = message.split(":");

                //This should not fail.
                if (commands.length == 0)
                    continue;

                //Handle messages
                String request = commands[0];

                if(request.equals("Quit")) {
                    break;
                }

                String response = "yes!";
                jmsMessageSender.send(queueSend, response, jmsMessageID);
            }

            ((ClassPathXmlApplicationContext) ctx).close();


        } catch (JMSException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    private static void testMessage(JmsMessageSender jmsMessageSender, Queue queueRequest) {
        jmsMessageSender.send(queueRequest, "VehiclePositions:test:0:10:ta1", "0");
        jmsMessageSender.send(queueRequest, "VehiclePositions:test:0:10:ta0", "0");
    }

}