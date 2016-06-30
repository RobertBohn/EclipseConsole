package com.company;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.component.SnsClient;

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

    public void testString() {
    	
       	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
       	try {
       		SnsClient sns = context.getBean(SnsClient.class);
    		sns.sendText("Testing.");
       	} catch(Exception e) {
            fail("sns send failed: " + e.getMessage());
        }
    }
	
}
