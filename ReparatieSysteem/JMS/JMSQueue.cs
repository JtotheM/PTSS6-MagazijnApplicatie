using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Apache.NMS;
using Apache.NMS.Util;

namespace ReparatieSysteem.JMS
{
    class JMSQueue : IDisposable
    {
        private string mServer;

        public event MessageListener OnMessage;

        private Apache.NMS.IConnection mConnection;
        private Apache.NMS.ISession mSession;
        private Apache.NMS.IMessageConsumer mConsumer;
        private Apache.NMS.IMessageProducer mProducer;

        public JMSQueue(string aServer)
        {
            mServer = aServer;
        }

        public bool Connect()
        {
            bool pReturn = false;
            try
            {
                Uri pURI = new Uri("activemq:tcp://" + mServer);
                IConnectionFactory pFactory = new NMSConnectionFactory(pURI);
                mConnection = pFactory.CreateConnection();
                mSession = mConnection.CreateSession();

                IDestination pDestinationConsumer = SessionUtil.GetDestination(mSession, "queue://MainOfficeRequest");
                IDestination pDestinationProducer = SessionUtil.GetDestination(mSession, "queue://MainOfficeResponse");
                mConnection.Start();
                mConsumer = mSession.CreateConsumer(pDestinationConsumer);
                mConsumer.Listener += OnMessage;
                mProducer = mSession.CreateProducer(pDestinationProducer);
                mProducer.DeliveryMode = MsgDeliveryMode.Persistent;
                mProducer.RequestTimeout = TimeSpan.FromSeconds(20);
                pReturn = true;
            }
            catch (Exception ex)
            {
                pReturn = false;
                Console.Error.WriteLine(ex.Message);
            }

            return pReturn;
        }

        public bool SendMessage(string aCorrolationID, string aMessage)
        {
            bool pReturn = false;
            try
            {
                if (mProducer != null)
                {
                    ITextMessage pMessage = mProducer.CreateTextMessage() as ITextMessage;
                    pMessage.NMSCorrelationID = aCorrolationID;
                    pMessage.Text = aMessage;
                    mProducer.Send(pMessage);
                }
                pReturn = true;
            }
            catch (Exception ex)
            {
                Console.Error.WriteLine(ex.Message);
            }

            return pReturn;
        }

        public void Dispose()
        {
            if (mConsumer != null)
            {
                mConsumer.Close();
                mConsumer.Dispose();
                mConsumer = null;
            }

            if (mProducer != null)
            {
                mProducer.Close();
                mProducer.Dispose();
                mProducer = null;
            }

            if (mSession != null)
            {
                mSession.Close();
                mSession.Dispose();
                mSession = null;

            }

            if (mConnection != null)
            {
                mConnection.Close();
                mConnection.Dispose();
                mConnection = null;
            }
        }
    }
}
