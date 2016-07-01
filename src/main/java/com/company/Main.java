package com.company;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.component.EmailClient;
import com.company.component.SnsClient;

/**
 * Main is the EclipseExample command line tool.
 * 
 * @author robert
 */
public class Main {

	@Option(name = "-t", aliases = "--text", usage = "sns text message.")
	private String text;

	@Option(name = "-r", aliases = "--read", usage = "read emails.")
	private boolean read;

	/**
	 * Parse command line parameters and send sns messages and/or list emails.
	 * 
	 * @param args     Command line arguments.
	 */
	public static void main(String[] args) {
		final Main main = new Main();
		final CmdLineParser cmdParser = new CmdLineParser(main);

		try {
			cmdParser.parseArgument(args);
			main.run();
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			cmdParser.printUsage(System.err);
		}
	}

	/**
	 * Initialize Spring application context, make requested calls.
	 */
	public void run() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		if (text != null) {
			SnsClient sns = context.getBean(SnsClient.class);
			sns.sendText(text);
		}

		if (read) {
			EmailClient email = context.getBean(EmailClient.class);
			email.Read();
		}
	}
}
