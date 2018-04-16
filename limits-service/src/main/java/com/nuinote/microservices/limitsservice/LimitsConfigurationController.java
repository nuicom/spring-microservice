package com.nuinote.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nuinote.microservices.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {

	@Autowired
	private Configuration configuration;
	
	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfigurations() {
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
	}
	
	@GetMapping("fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallbackrRetrieveConfiguration")
	public LimitConfiguration retrieveConfiguration() {
		throw new RuntimeException("Not available");
	}
	
	public LimitConfiguration fallbackrRetrieveConfiguration() {
		return new LimitConfiguration(9, 999);
	}
	
}
