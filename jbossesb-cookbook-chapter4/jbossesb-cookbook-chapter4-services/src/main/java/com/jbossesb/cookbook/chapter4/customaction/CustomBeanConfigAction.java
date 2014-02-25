package com.jbossesb.cookbook.chapter4.customaction;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.actions.ActionProcessingException;
import org.jboss.soa.esb.actions.BeanConfiguredAction;
import org.jboss.soa.esb.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomBeanConfigAction extends AbstractActionLifecycle implements
		BeanConfiguredAction {

	private String information;

	private Integer repeatCount;

	private String serviceCategory;

	private String serviceName;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomBeanConfigAction.class);

	public void setInformation(String information) {
		this.information = information;
	}

	public void setRepeatCount(Integer repeatCount) {
		this.repeatCount = repeatCount;
	}

	public Message process(Message message) throws ActionProcessingException {
		LOGGER.info("[" + serviceCategory + ":" + serviceName
				+ "] Repeat message: " + information + " " + repeatCount
				+ " times:");
		for (int i = 0; i < repeatCount; i++) {
			LOGGER.info(information);
		}
		return message;
	}

	public void setServiceCategory(final String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}

}
