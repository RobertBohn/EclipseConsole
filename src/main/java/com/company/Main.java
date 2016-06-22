package com.company;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    @Option(name = "-u", aliases = "--user", required = true, usage = "user name.")
    private String user;

    @Option(name = "-s", aliases = "--password", usage = "password.")
    private String password;

    public static void main(String[] args) {
        final Main main = new Main();
        final CmdLineParser cmdParser = new CmdLineParser(main);

        try {
            cmdParser.parseArgument(args);     
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            cmdParser.printUsage(System.err);
            System.exit(1);
        }
        main.run();
    }

    public void run() {    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    	
    	
    }
}
