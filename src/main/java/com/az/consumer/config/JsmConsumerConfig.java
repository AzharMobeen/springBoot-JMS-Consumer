package com.az.consumer.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class JsmConsumerConfig {

	@Bean
	public MarshallingMessageConverter createMarshallingMessageConverter(final Jaxb2Marshaller jaxb2Marshaller) {
		
		return new MarshallingMessageConverter(jaxb2Marshaller);
	}
	
	@Bean
	public Jaxb2Marshaller createJaxb2Marshaller(@Value("${jaxb.package}") final String packageToScane) {		
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();		
		jaxb2Marshaller.setPackagesToScan(packageToScane);
		Map<String, Object> properties = new HashMap<>();
		properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxb2Marshaller.setMarshallerProperties(properties);		
		return jaxb2Marshaller;
	}		
}
