package JMS;

import javax.jms.JMSException;

import javax.jms.Queue;
import javax.jms.TextMessage;

import Messages.RequestHandler;
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
public class Main {

    private static RequestHandler requestHandler = new RequestHandler();
    private static HashMap<String, String> channels;

    public static void main(String[] args) {

        channels = new HashMap<>();
        channels.put("MessageRequest", "MessageResponse");
        channels.put("MessageRequest2", "MessageResponse2");
        channels.put("MessageRequest3", "MessageResponse3");

        ArrayList<Thread> threads = launchChannelThreads();
        waitForThreads(threads);
    }

    private static ArrayList<Thread> launchChannelThreads() {
        //Launch a thread for every channel
        ArrayList<Thread> threads = new ArrayList<>();
        Iterator channelIterator = channels.entrySet().iterator();
        while (channelIterator.hasNext()) {
            final Map.Entry pair = (Map.Entry) channelIterator.next();

            Thread channelThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    handleChannel((String) pair.getKey(), (String) pair.getValue());
                }
            });
            channelThread.start();
            threads.add(channelThread);
            channelIterator.remove();
        }
        return threads;
    }

    private static void waitForThreads(ArrayList<Thread> threads) {
        //Wait for all threads
        try {
            for (Thread item : threads) {
                item.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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