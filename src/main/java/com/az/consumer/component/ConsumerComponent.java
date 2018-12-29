package com.az.consumer.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.az.consumer.model.UserCollection;


@EnableJms
@Component
public class ConsumerComponent {

	private static final Logger log = LoggerFactory.getLogger(ConsumerComponent.class);
	
	@JmsListener(destination="${inbound.endpoint}")
	public void process(UserCollection userCollection) {
		if(userCollection!=null)
			displayResult(userCollection);
		else
			log.error("UserCollection :: Null");
	}
	
	private void displayResult(UserCollection userCollection) {
		if(!CollectionUtils.isEmpty(userCollection.getUserList()))
			userCollection.getUserList().forEach(user->{
				log.info("User {} ", user.toString());
			});
		else
			log.error("UserCollection.getUserList :: null");
	}
}
