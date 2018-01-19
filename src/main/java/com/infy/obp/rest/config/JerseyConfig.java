package com.infy.obp.rest.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.infy.obp.rest.Account;

@Component
public class JerseyConfig extends ResourceConfig {

public JerseyConfig() {
		
		register(Account.class);
		
	}
}
