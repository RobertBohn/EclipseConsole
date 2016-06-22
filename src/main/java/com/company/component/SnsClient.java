package com.company.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
public class SnsClient {

    @Autowired
    private Properties properties;
    
    private static Logger logger = Logger.getLogger(SnsClient.class);

    private String topic;
    
    public SnsClient() {  
    	logger.info("Constructor");
    }
    
    @PostConstruct
    private void initialize() {
        topic = properties.getProperty("sns.topic");
        logger.info("Topic:" + topic);
    }
}
