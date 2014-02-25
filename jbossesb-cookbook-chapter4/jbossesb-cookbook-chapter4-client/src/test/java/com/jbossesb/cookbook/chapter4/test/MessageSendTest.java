package com.jbossesb.cookbook.chapter4.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jbossesb.cookbook.chapter4.config.ApplicationConfiguration;
import com.jbossesb.cookbook.chapter4.jms.JmsCustomActionSender;
import com.jbossesb.cookbook.chapter4.jms.JmsGroovyscriptSender;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
// @Ignore("can only be tested when env ready")
public class MessageSendTest {

	@Resource
	private JmsCustomActionSender jmsCustomActionSender;

	@Resource
	private JmsGroovyscriptSender jmsGroovyscriptSender;

	@Test
	public void testCustomActionJms() throws Exception {
		jmsCustomActionSender
				.sendAMessage("Chapter 4 JMS For Customaction says Hello!");
	}

	@Test
	public void testGroovyActionJms() throws Exception {
		jmsGroovyscriptSender
				.sendAMessage("Chapter 4 JMS For Groovy says Hello!");
	}
}
