package com.company;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.component.EmailClient;
import com.company.component.SnsClient;

public class Main {

    @Option(name = "-t", aliases = "--text", usage = "text message.")
    private String text;
    
    @Option(name="-r", aliases = "--read", usage="read emails.")
    private boolean read;

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

    public void run() {    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
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
