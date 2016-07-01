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
        	Main.main(new String[] {"-r", "-t", "Cmd Test"});
       	} catch(Exception e) {
            fail("cmd line call failed: " + e.getMessage());
        }
    }	
    
    public void testNoCmdLine() {      
       	try {
        	Main.main(new String[] {});
       	} catch(Exception e) {
            fail("no cmd line call failed: " + e.getMessage());
        }
    }	
    
    public void testBadCmdLine() {      
       	try {
        	Main.main(new String[] {"-x"});
       	} catch(Exception e) {
            fail("bad cmd line call failed: " + e.getMessage());
        }
    }    
}
