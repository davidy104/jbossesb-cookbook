package com.jbossesb.cookbook.chapter3.esb;

import org.jboss.soa.esb.client.ServiceInvoker;
import org.jboss.soa.esb.listeners.message.MessageDeliverException;
import org.jboss.soa.esb.message.Message;
import org.jboss.soa.esb.message.format.MessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EsbSender {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EsbSender.class);

	public void sendAMessage(String msg) throws MessageDeliverException {
		LOGGER.info("sendAMessage start:{} ", msg);
		System.setProperty("javax.xml.registry.ConnectionFactoryClass",
				"org.apache.ws.scout.registry.ConnectionFactoryImpl");

		Message esbMessage = MessageFactory.getInstance().getMessage();

		esbMessage.getBody().add(msg);

		new ServiceInvoker("Chapter3Sample", "Chapter3Service")
				.deliverAsync(esbMessage);
	}

}
