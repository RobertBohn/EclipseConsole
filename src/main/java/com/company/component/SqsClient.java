/**
 * 
 */
package com.company.component;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClient;

/*
 *  see: https://github.com/aws/aws-sdk-java/blob/master/src/samples/AmazonSimpleQueueService/SimpleQueueServiceSample.java
 */
@Component
public class SqsClient {

    @Autowired
    private Properties properties;
    
    private AmazonSQSClient sqs;
    
    private static Logger logger = Logger.getLogger(SqsClient.class);

	public SqsClient() {
		sqs = new AmazonSQSClient(new ProfileCredentialsProvider().getCredentials());
		sqs.setRegion(Region.getRegion(Regions.US_EAST_1));
	}
	
	public void listQueues() {
		for (String queueUrl : sqs.listQueues().getQueueUrls()) {
            logger.info("QueueUrl: " + queueUrl);
		}
	}
    
    
	
	
	
}
