package com.jbossesb.cookbook.chapter3.test;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jbossesb.cookbook.chapter3.config.ApplicationConfiguration;
import com.jbossesb.cookbook.chapter3.esb.EsbSender;
import com.jbossesb.cookbook.chapter3.jms.JmsSender;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
//@Ignore("can only be tested when env ready")
public class MessageSendTest {

	@Resource
	private JmsSender jmsSender;

	@Resource
	private EsbSender esbSender;

	@Test
	public void testJms() throws Exception {
		jmsSender.sendAMessage("Chapter 3 JMS says Hello!");
	}

	@Test
//	@Ignore("need to fix dependencies issues")
	public void testEsb() throws Exception {
		esbSender.sendAMessage("Chapter 3 ESB says Hello!");
	}
}
