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

    private static final RequestHandle requestHandler = new RequestHandle();
    private static HashMap<String, String> channels;
    private static ApplicationContext ctx;

    public static void main(String[] args) {

        Main.ctx = new ClassPathXmlApplicationContext("app-context.xml");

        //Channels that we support. The Request Handle will handle them on chanel name
        channels = new HashMap<>();
        channels.put("OrderRequest", "OrderResponse"); //ParafiksitWebI
        channels.put("OrderRequestFontys", "OrderResponseFontys"); //FontysApp
        channels.put("WarehouseResponse", "WarehouseResponseBack"); //MagazijnApp
        channels.put("MainOfficeResponse", "MainOfficeResponseBack"); //ParafiksitApp
        channels.put("WarehouseStatusResponse", "WarehouseStatusResponseBack"); //Unknown
        channels.put("orderStatus", "orderStatusResponse"); //Unknown

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
        //Wait for all threads to finish
        try {
            for (Thread item : threads) {
                synchronized (item) {
                    item.wait();
                }
            }
        } catch (InterruptedException e) {
        }
    }

    private static void handleChannel(final String receiveChannel, final String sendChannel) {

        //Get channels
        Queue queueRequest = new ActiveMQQueue(receiveChannel);

        final List<Thread> processors = new ArrayList<>();

        //Keep requesting data
        while (true) {
            JmsMessageSender jmsMessageSender = (JmsMessageSender) Main.ctx.getBean("jmsMessageSender");

            final TextMessage receive = jmsMessageSender.receive(queueRequest);
            if (receive == null) {
                continue;
            }

            //Handle the message in another thread. Now you can handle multiple requests
            Thread handler = new Thread(new Runnable() {
                @Override
                public void run() {
                    String message = null;
                    try {

                        message = receive.getText();

                        //Get return address
                        String returnAddress = receive.getStringProperty("returnAddress");
                        if (returnAddress == null || returnAddress.isEmpty()) {
                            returnAddress = sendChannel;
                        }

                        //Handle message
                        String response = requestHandler.handleMessage(message, receiveChannel, receive.getJMSCorrelationID());

                        if (message.equals("Quit")) {
                            return;
                        }

                        //Send response
                        String jmsMessageID = receive.getJMSMessageID();
                        if (response != null) {
                            sendMessage(returnAddress, response, jmsMessageID);
                        }

                    } catch (JMSException e) {
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
