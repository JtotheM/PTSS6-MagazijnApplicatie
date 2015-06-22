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
    private static ApplicationContext ctx;
    private static JmsMessageSender jmsMessageSender;

    public static void main(String[] args) {

        Main.ctx = new ClassPathXmlApplicationContext("app-context.xml");
        Main.jmsMessageSender = (JmsMessageSender) Main.ctx.getBean("jmsMessageSender");

        channels = new HashMap<>();
        channels.put("OrderRequest", "OrderResponse"); //ParafiksitWebI - FontysApp
        channels.put("WarehouseResponse", "WarehouseResponseBack"); //MagazijnApp
        channels.put("MainOfficeResponse", "MainOfficeResponseBack"); //ParafiksitApp

        ArrayList<Thread> threads = launchChannelThreads();
        waitForThreads(threads);
    }

    private static ArrayList<Thread> launchChannelThreads() {

        //Launch a thread for every channel
        ArrayList<Thread> threads = new ArrayList<>();
        Iterator channelIterator = channels.entrySet().iterator();
        while (channelIterator.hasNext()) {
            final Map.Entry pair = (Map.Entry) channelIterator.next();

            //Start the thread
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

    private static String handleChannel(String receiveChannel, String sendChannel) {

        String messageId = "";
        try {

            //Get channels
            Queue queueRequest = new ActiveMQQueue(receiveChannel);

            //Keep requesting data
            while (true) {
                TextMessage receive = jmsMessageSender.receive(queueRequest);
                if (receive == null)
                    continue;

                String message = receive.getText();
                String response = requestHandler.handleMessage(message, receiveChannel,receive.getJMSCorrelationID());
                String jmsMessageID = receive.getJMSMessageID();

                if (message.equals("Quit")) {
                    break;
                }

                if (!response.isEmpty()) {
                    messageId = sendMessage(sendChannel, response, jmsMessageID);
                }
            }

            ((ClassPathXmlApplicationContext) ctx).close();


        } catch (JMSException e) {
            e.printStackTrace();
        }

        return messageId;
    }

    public static String sendMessage(String sendChannel, String message, String jmsMessageID) {
        Queue queueSend = new ActiveMQQueue(sendChannel);
        return jmsMessageSender.send(queueSend, message, jmsMessageID);
    }
}