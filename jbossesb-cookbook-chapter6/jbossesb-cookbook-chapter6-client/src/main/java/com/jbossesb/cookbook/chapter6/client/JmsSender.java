package com.jbossesb.cookbook.chapter6.client;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {
	private QueueConnection conn;
	private QueueSession session;
	private Queue queue;
	private Queue replyQueue;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JmsSender.class);

	@PostConstruct
	public void init() throws JMSException, NamingException {
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		properties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.naming:org.jnp.interfaces");
		properties.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1099");
		InitialContext iniCtx = new InitialContext(properties);
		Object tmp = iniCtx.lookup("ConnectionFactory");
		QueueConnectionFactory qcf = (QueueConnectionFactory) tmp;
		conn = qcf.createQueueConnection();

		queue = (Queue) iniCtx.lookup("queue/chapter6_Request_gw");
		replyQueue = (Queue) iniCtx.lookup("queue/chapter6_Request_gw_reply");

		session = conn.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
		conn.start();
		LOGGER.info("Connection Started:{}");
	}

	public void sendAMessage(String msg) throws JMSException {
		QueueSender send = session.createSender(queue);
		ObjectMessage tm = session.createObjectMessage(msg);
		tm.setJMSReplyTo(replyQueue);
		send.send(tm);
		send.close();
	}

	public void readReply() throws JMSException {
		QueueReceiver receiver = session.createReceiver(replyQueue);
		Message msg = receiver.receive();
		if (msg instanceof TextMessage) {
			LOGGER.info("receive message:{} ", ((TextMessage) msg).getText());
		}
	}

	@PreDestroy
	public void clean() throws JMSException {
		conn.stop();
		session.close();
		conn.close();
	}
}
