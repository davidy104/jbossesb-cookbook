package com.jbossesb.cookbook.chapter4.customaction;

import org.jboss.soa.esb.actions.AbstractActionLifecycle;
import org.jboss.soa.esb.actions.ActionProcessingException;
import org.jboss.soa.esb.helpers.ConfigTree;
import org.jboss.soa.esb.message.Message;

public class StatefulAction extends AbstractActionLifecycle {
	protected ConfigTree _config;
	int cnt = 0;

	public StatefulAction(ConfigTree config) {
		System.out.println("\n\nConstructor - " + this.getClass().getName()
				+ "\n");
		cnt++; // 1
		_config = config;
	}

	public Message methodOne(Message msg) throws ActionProcessingException {
		cnt++; // 2
		System.out.println("methodOne: " + cnt);
		return msg;
	}

	public Message methodTwo(Message msg) throws ActionProcessingException {
		cnt++; // 3
		System.out.println("methodTwo: " + cnt);
		return msg;
	}

	public Message displayCount(Message msg) throws ActionProcessingException {
		System.out.println("displayCount cnt=" + cnt + "\n");
		return msg;
	}

	public void exceptionHandler(Message message, Throwable exception) {
		logHeader();
		System.out.println("!ERROR!");
		System.out.println(exception.getMessage());
		System.out.println("For Message: ");
		System.out.println(message.getBody().get());
		logFooter();
	}

	// This makes it easier to read on the console
	private void logHeader() {
		System.out
				.println("\n&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	}

	private void logFooter() {
		System.out
				.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
	}
}
