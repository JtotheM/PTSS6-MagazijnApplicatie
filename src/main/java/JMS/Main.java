package JMS;

import javax.jms.JMSException;

import javax.jms.Queue;
import javax.jms.TextMessage;

import Messages.RequestHandle;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * Created by Laurence on 20/6/2015.
 */

public class Main {

    private static RequestHandle requestHandler = new RequestHandle();
    private static HashMap<String, String> channels;
    private static ApplicationContext ctx;

    public static void main(String[] args) {

        Main.ctx = new ClassPathXmlApplicationContext("app-context.xml");

        channels = new HashMap<String, String>();
        channels.put("OrderRequest", "OrderResponse"); //ParafiksitWebI - FontysApp
        channels.put("WarehouseResponse", "WarehouseResponseBack"); //MagazijnApp
        channels.put("MainOfficeResponse", "MainOfficeResponseBack"); //ParafiksitApp

        ArrayList<Thread> threads = launchChannelThreads();
        waitForThreads(threads);
    }

    private static ArrayList<Thread> launchChannelThreads() {

        //Launch a thread for every channel
        ArrayList<Thread> threads = new ArrayList<Thread>();
        Iterator channelIterator = channels.entrySet().iterator();
        while (channelIterator.hasNext()) {
            final Map.Entry pair = (Map.Entry) channelIterator.next();

            //Start the thread
            Thread channelThread = new Thread(new Runnable() {
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
                synchronized (item) {
                    item.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void handleChannel(final String receiveChannel, final String sendChannel) {

        //Get channels
        Queue queueRequest = new ActiveMQQueue(receiveChannel);

        final List<Thread> processors = new ArrayList<Thread>();

        //Keep requesting data
        while (true) {
            JmsMessageSender jmsMessageSender = (JmsMessageSender) Main.ctx.getBean("jmsMessageSender");

            final TextMessage receive = jmsMessageSender.receive(queueRequest);
            if (receive == null)
                continue;

            Thread handler = new Thread(new Runnable() {
                public void run() {
                    String message = null;
                    try {
                        message = receive.getText();

                        String response = requestHandler.handleMessage(message, receiveChannel, receive.getJMSCorrelationID());
                        String jmsMessageID = receive.getJMSMessageID();

                        if (message.equals("Quit")) {
                            return;
                        }

                        if (response != null) {
                            sendMessage(sendChannel, response, jmsMessageID);
                        }
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            handler.start();
            processors.add(handler);
        }
    }

    public static String sendMessage(String sendChannel, String message, String jmsMessageID) {
        Queue queueSend = new ActiveMQQueue(sendChannel);
        JmsMessageSender jmsMessageSender = (JmsMessageSender) Main.ctx.getBean("jmsMessageSender");
        return jmsMessageSender.send(queueSend, message, jmsMessageID);
    }
}