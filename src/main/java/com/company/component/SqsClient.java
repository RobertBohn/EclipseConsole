/**
 * 
 */
package com.company.component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
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
		
		
		
		
		
        // Send a message
		String myQueueUrl = "https://sqs.us-east-1.amazonaws.com/551383545569/Bonz";
		
        System.out.println("Sending a message to MyQueue.\n");
                
        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        messageAttributes.put("attributeName", new MessageAttributeValue().withDataType("String").withStringValue("string-value-attribute-value"));

        SendMessageRequest request = new SendMessageRequest();
        request.withMessageBody("A test message body.");
        request.withQueueUrl(myQueueUrl);
        request.withMessageAttributes(messageAttributes);
        sqs.sendMessage(request);
                
        // Receive messages
        System.out.println("Receiving messages from MyQueue.\n");
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
           
        List<Message> messages = sqs.receiveMessage(receiveMessageRequest.withMessageAttributeNames("attributeName")).getMessages();   

        for (Message message : messages) {
            System.out.println("  Message");
            System.out.println("    MessageId:     " + message.getMessageId());
            System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("    Body:          " + message.getBody());            
            System.out.println("    attributeName: " + message.getMessageAttributes().get("attributeName").getStringValue());
        }
        System.out.println();

        // Delete a message
        System.out.println("Deleting a message.\n");
        String messageReceiptHandle = messages.get(0).getReceiptHandle();
        sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl, messageReceiptHandle));
	}
}
