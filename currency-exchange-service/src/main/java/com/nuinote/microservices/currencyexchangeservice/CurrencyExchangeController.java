package com.nuinote.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment enviroment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue  =
//		= new ExchangeValue(100L, from, to, BigDecimal.valueOf(65));
		repository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(enviroment.getProperty("local.server.port")));
		logger.info("{}",exchangeValue);
		return exchangeValue;
	}
}
