package com.jbossesb.cookbook.chapter3;

import java.util.UUID;

import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAction1 {
	protected ConfigTree _config;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyAction.class);

	public MyAction1(ConfigTree config) {
		_config = config;
	}

	public Message messageToBean(Message message) {
		LOGGER.info("messageToBean start:{} *****************************");
		try {
			Object requestMessage = message.getBody().get();
			String beanId = UUID.randomUUID().toString();
			MyBean myBean = MyBean.getBuilder(beanId, 1,
					String.valueOf(requestMessage)).build();
			LOGGER.info("after convert:{} ",myBean);
			message.getBody().add(myBean);
			message.getBody().add("Something", "Unknown");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		LOGGER.info("messageToBean end:{} *****************************");
		return message;
	}

}
