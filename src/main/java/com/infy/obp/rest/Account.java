package com.infy.obp.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.infy.obp.client.service.AccountBalanceService;
import com.infy.obp.vo.AccountBalanaceData;
import com.infy.obp.vo.CustomerCred;
import com.infy.obp.vo.ResponseData;

@Path("/obp")
@Component
public class Account {
	
	@Autowired private AccountBalanceService  accountBalanceService;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/accounts")
	public List<AccountBalanaceData> fetchAccountsBalance() {	
		System.out.println("===========In fetchAccountsBalance-----");
		return accountBalanceService.fetchAccountsBalance();
		
		
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/accounts")
	public ResponseData addAccount(CustomerCred accountInfo) {	
		System.out.println("===========In addAccount-----");
		 String msg= accountBalanceService.addAccount(accountInfo);
		 ResponseData data = new ResponseData();
		 data.setMessage(msg);
		 return data;
	}
	
	
}
