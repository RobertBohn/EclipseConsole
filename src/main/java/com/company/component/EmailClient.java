package com.company.component;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

@Component
public class EmailClient {

    @Autowired
    private Properties properties;

    private static Logger logger = Logger.getLogger(EmailClient.class);
	private Properties emailProperties = new Properties();
    private Session session;
    private Store store;
    private Folder inbox;

    public void Read() {    	
        try {  	
        	emailProperties.setProperty("mail.store.protocol", "imaps");
        	session = Session.getInstance(emailProperties, null);
        	store = session.getStore();
			store.connect(properties.getProperty("email.server"), properties.getProperty("email.user"), properties.getProperty("email.password"));
			inbox = store.getFolder(properties.getProperty("email.folder"));
			inbox.open(Folder.READ_ONLY);			
	        for (int i=inbox.getMessageCount(); i>0; i--) {
	        	Message message = inbox.getMessage(i);	                
	            logger.info("Email: " + message.getSubject());
	        }    	
		} catch (MessagingException e) {
	   		logger.error("Error Reading Email: " + e.getMessage());	   	 
		}    	
    }      
}
