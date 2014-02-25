package com.jbossesb.cookbook.chapter6.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jbossesb.cookbook.chapter6.client.FileSender;
import com.jbossesb.cookbook.chapter6.client.HttpSender;
import com.jbossesb.cookbook.chapter6.client.JmsSender;
import com.jbossesb.cookbook.chapter6.config.ApplicationConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
public class MultipleGwTest {

	@Resource
	private JmsSender jmsSender;

	@Resource
	private FileSender fileSender;

	@Resource
	private HttpSender httpSender;

	@Test
	public void testSendJms() throws Exception {
		jmsSender.sendAMessage("Hello JMS Gateway!");
		jmsSender.readReply();
	}

	@Test
	public void testSendFile() throws Exception {
		fileSender.processFile();
	}

	@Test
	public void testSendHttp() throws Exception {
		httpSender.httpProcess("Hello Http Gateway!");
	}

}
