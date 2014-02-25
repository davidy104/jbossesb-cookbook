package com.jbossesb.cookbook.chapter6.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileSender {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FileSender.class);

	public void processFile() throws Exception {
		String tmpDir = System.getProperty("java.io.tmpdir");
		File file = new File(tmpDir, "file.msg");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file)));
		writer.write("Hello File Gateway!");
		writer.close();
		Thread.sleep(300);
		File result = new File(tmpDir, "results.log");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(result)));
		String line = null;
		LOGGER.info("print response content:{} ");
		while ((line = reader.readLine()) != null) {
			LOGGER.info(line);
		}
		reader.close();
	}
}
