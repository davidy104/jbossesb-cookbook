package com.jbossesb.cookbook.chapter4.customaction;

import java.util.Set;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.actions.ActionProcessingException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomConfigAction extends AbstractActionLifecycle {
	protected ConfigTree _config;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CustomConfigAction.class);

	public CustomConfigAction(ConfigTree config) {
		_config = config;
	}

	public Message displayConfig(Message msg) throws ActionProcessingException {
		// Note: in and out message is being ignored

		Set<String> names = _config.getAttributeNames();
		LOGGER.info("****************************");
		for (String attrName : names) {
			String value = _config.getAttribute(attrName);
			LOGGER.info("Attribute: " + attrName + " Value: " + value);
		}
		LOGGER.info("****************************");

		ConfigTree[] subElements = _config.getAllChildren();
		// Note: even a sub-element can have attributes but trying to keep this
		// simple
		LOGGER.info("############################");
		for (ConfigTree child : subElements) {
			LOGGER.info("SubElement: " + child.getName() + "Body: "
					+ child.getWholeText());
		}
		LOGGER.info("############################");
		return msg;
	}
}
