package com.company.component;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.springframework.stereotype.Component;

@Component
public class Parameters {

	private CmdLineParser cmdParser;
	
	@Option(name = "-t", aliases = "--text", usage = "sns text message.")
	private String text;

	@Option(name = "-r", aliases = "--read", usage = "read emails.")
	private boolean read;

	@Option(name = "-q", aliases = "--queue", usage = "read sqs queues.")
	private boolean queue;

	public Parameters() {
		cmdParser = new CmdLineParser(this);
	}
	
	public boolean parseArgument(String[] args) {
		try {
			cmdParser.parseArgument(args);
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			cmdParser.printUsage(System.err);
			return false;
		}
		return true;
	}

	public String getText() {
		return text;
	}

	public boolean isRead() {
		return read;
	}

	public boolean isQueue() {
		return queue;
	}	
}
