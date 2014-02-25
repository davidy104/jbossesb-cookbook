package com.jbossesb.cookbook.chapter6;

import java.util.Map;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAction extends AbstractActionLifecycle {
	protected ConfigTree _config;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MyAction.class);

	public MyAction(ConfigTree config) {
		_config = config;
	}

	@SuppressWarnings("unchecked")
	public Message process(Message message) throws Exception {
		LOGGER.info("MyAction process start:{}", message);
		Object obj = message.getBody().get();
		String request = "";
		if (obj instanceof String) {
			LOGGER.info("string type request:{} ");
			request = (String) obj;
		} else if (obj instanceof byte[]) {
			LOGGER.info("byte type request:{} ");
			request = new String((byte[]) obj);
		} else if (obj instanceof Map) {
			LOGGER.info("map type request:{} ");
			Map<String, Object> rowData = (Map<String, Object>) obj;
			for (Map.Entry<String, Object> curr : rowData.entrySet()) {
				Object value = curr.getValue();
				if (value instanceof String) {
					request = (String) value;
				}
			}
		} else if (obj instanceof GatewayRecord) {
			LOGGER.info("GatewayRecord type request:{} ");
			request = ((GatewayRecord) obj).getData();
		}
		LOGGER.info("request:{} ", request);

		String response = request.replace("Hello", "").replace("!",
				" says Hello!");
		message.getBody().add(response);
		LOGGER.info("MyAction process end:{}");
		return message;
	}
}
