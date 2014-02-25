package com.jbossesb.cookbook.chapter4.customaction;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.actions.ActionProcessingException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBasicAction extends AbstractActionLifecycle {

	protected ConfigTree _config;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyBasicAction.class);

	public MyBasicAction(ConfigTree config) {
		_config = config;
	}

	public Message noOperation(Message message) {
		return message;
	}

	public Message process(Message message) {
		LOGGER.info("The default method called");
		return message;
	}

	public Message displayMessage(Message message)
			throws ActionProcessingException {
		this.doDisplayMessage(message);
		return message;
	}

	private void doDisplayMessage(Message message) {
		String content = (String) message.getBody().get();
		logHeader();
		LOGGER.info("Body:{} ", content);
		logFooter();
		message.getBody().add(new String(content + " " + new java.util.Date()));
		
		//mock exception
//		throw new RuntimeException("mock error.");

	}

	public void exceptionHandler(Message message, Throwable exception) {
		logHeader();
		LOGGER.info("!ERROR!");
		LOGGER.info(exception.getMessage());
		LOGGER.info("For Message:{} ", message.getBody().get());
		logFooter();
	}

	// This makes it easier to read on the console
	private void logHeader() {
		LOGGER.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	}

	private void logFooter() {
		LOGGER.info("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
	}

}
