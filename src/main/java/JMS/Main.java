package JMS;

import javax.jms.JMSException;

import javax.jms.Queue;
import javax.jms.TextMessage;

import Messages.RequestHandler;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Laurence on 20/6/2015.
 */
public class Main {

    private static RequestHandler requestHandler = new RequestHandler();

    public static void main(String[] args) {

        try {

            Thread testChannel1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    handleChannel("MessageRequest", "MessageResponse");
                }
            });
            Thread testChannel2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    handleChannel("MessageRequest2", "MessageResponse2");
                }
            });

            testChannel1.start();
            testChannel2.start();

            testChannel1.wait();
            testChannel2.wait();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    private static void handleChannel(String receiveChannel, String sendChannel) {
        try {

            ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
            JmsMessageSender jmsMessageSender = (JmsMessageSender) ctx.getBean("jmsMessageSender");

            Queue queueRequest = new ActiveMQQueue(receiveChannel);
            Queue queueSend = new ActiveMQQueue(sendChannel);

            //TEST CODE! REMOVE PLZ!
            testMessage(jmsMessageSender, queueRequest);

            while (true) {
                TextMessage receive = jmsMessageSender.receive(queueRequest);
                if (receive == null)
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

                if (request.equals("Quit")) {
                    break;
                }

                String response = requestHandler.handleMessage(request, commands, receiveChannel);
                jmsMessageSender.send(queueSend, response, jmsMessageID);
            }

            ((ClassPathXmlApplicationContext) ctx).close();


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private static void testMessage(JmsMessageSender jmsMessageSender, Queue queueRequest) {
        jmsMessageSender.send(queueRequest, "Test message", "0");
    }
}