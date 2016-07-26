package com.company;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.component.EmailClient;
import com.company.component.Parameters;
import com.company.component.SnsClient;
import com.company.component.SqsClient;

/**
 * Main is the EclipseExample command line tool.
 * 
 * @author robert
 */
public class Main {

	/**
	 * Parse command line parameters and send sns messages and/or list emails.
	 * 
	 * @param args     Command line arguments.
	 */
	public static void main(String[] args) {
		new Main().run(args);
	}

	/**
	 * Initialize Spring application context, make requested calls.
	 */
	public void run(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		Parameters parameters = context.getBean(Parameters.class);
		if (!parameters.parseArgument(args)) {
			return;
		}
		
		if (parameters.getText() != null) {
			((SnsClient)context.getBean(SnsClient.class)).sendText();
		}

		if (parameters.isRead()) {
			((EmailClient)context.getBean(EmailClient.class)).Read();
		}
		
		if (parameters.isQueue()) {
			((SqsClient)context.getBean(SqsClient.class)).listQueues();
		}
	}
}
