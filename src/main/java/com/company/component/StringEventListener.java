package com.company.component;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StringEventListener {

    private static Logger logger = Logger.getLogger(StringEventListener.class);

	@Autowired
    private ApplicationEventPublisher publisher;

	// can be event of any pojo type, listener and be a method inb any component
	@EventListener
	public void fired(String str) {
        logger.info("String Event Fired: " + str);
	}
	
	@EventListener
	public void fired2(String str) {
        logger.info("Hey I'm Listening Too - " + str);
	}

	public void fire(String str) {
		publisher.publishEvent(str);
	}
}
