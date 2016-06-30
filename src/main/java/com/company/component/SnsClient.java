package com.company.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import java.util.Properties;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;

@Component
public class SnsClient {

    @Autowired
    private Properties properties;
    
    private static Logger logger = Logger.getLogger(SnsClient.class);
    private AmazonSNSClient sns;
    private String topic;
    
    public SnsClient() {
    	try {
    		sns = new AmazonSNSClient(new ProfileCredentialsProvider().getCredentials());
    		sns.setRegion(Region.getRegion(Regions.US_EAST_1));
    	} catch (Exception e) {
    		logger.error("Error Constructing AmazonSNSClient: " + e.getMessage());
    	}
    }
    
    @PostConstruct
    private void initialize() {
        topic = properties.getProperty("sns.topic");
		logger.info("sns topic: " + topic);
    }
    
    public void sendText(String message) {
        sns.publish(new PublishRequest(topic, message));
		logger.info("sns sent: " + message);
    }
}
