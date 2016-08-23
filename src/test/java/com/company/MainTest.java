package com.company;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MainTest extends TestCase {

    public MainTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MainTest.class);
    }

    public void testCmdLine() {      
       	try {
        	Main.main(new String[] {"-t"});
       	} catch(Exception e) {
            fail("text read: " + e.getMessage());
        }
    }    
}
