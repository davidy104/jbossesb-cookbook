package com.jbossesb.cookbook.chapter3;

import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Body;
import org.jboss.soa.esb.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAction {

	protected ConfigTree _config;
	public String SYMBOL = "&";
	public int COUNT = 48;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyAction.class);

	public MyAction(ConfigTree config) {
		_config = config;
		String symbol = _config.getAttribute("symbol");
		if (symbol != null) {
			SYMBOL = symbol;
		}
		String count = _config.getAttribute("count");
		if (count != null) {
			COUNT = Integer.parseInt(count);
		}
	}

	public Message displayMessage(Message message) throws Exception {
		LOGGER.info("displayMessage start:{} ----------------------");
		Body body  = message.getBody();
		printLine();
		MyBean mybean = (MyBean)body.get();

		LOGGER.info("MyBean:{} ", mybean);
		Integer count = mybean.getCount()+1;
		mybean.setCount(count);
		printLine();

		LOGGER.info("From:{} ", message.getHeader().getCall().getFrom());
		LOGGER.info("To:{} ", message.getHeader().getCall().getTo());
		LOGGER.info("MessageID:{} ", message.getHeader().getCall()
				.getMessageID());
		LOGGER.info("displayMessage end:{} ----------------------");
		return message;
	}

	private void printLine() {
		StringBuffer line = new StringBuffer(COUNT);
		for (int i = 0; i < COUNT; i++) {
			line.append(SYMBOL);
		}
		LOGGER.info(line.toString());
	}
}
