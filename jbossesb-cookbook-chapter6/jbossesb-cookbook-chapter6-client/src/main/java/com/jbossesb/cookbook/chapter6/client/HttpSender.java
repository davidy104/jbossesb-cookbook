package com.jbossesb.cookbook.chapter6.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class HttpSender {

	public void httpProcess(String content) throws Exception {
		String serverURL = "http://localhost:8080/jbossesbcookbook-chapter6/http/Chapter6Sample/Chapter6HttpService";
		HttpURLConnection connection = (HttpURLConnection) new URL(serverURL)
				.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.connect();
		PrintWriter out = new PrintWriter(new OutputStreamWriter(
				connection.getOutputStream()));
		out.println(content);
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}
		in.close();
	}
}
