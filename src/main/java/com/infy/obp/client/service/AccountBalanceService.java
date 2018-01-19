package com.infy.obp.client.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.infy.obp.auth.DirectAuthenticationService;
import com.infy.obp.mongo.repo.BankInfoRepository;
import com.infy.obp.mongo.repo.CustomerCredRepository;
import com.infy.obp.vo.Account;
import com.infy.obp.vo.AccountBalanaceData;
import com.infy.obp.vo.BankInfo;
import com.infy.obp.vo.CustomerCred;



@Component
public class AccountBalanceService {
	
	
    private RestTemplate restTemplate = new RestTemplate();
    
    @Autowired 
    private DirectAuthenticationService directAuthenticationService;
    
    @Autowired
	private BankInfoRepository bankRepository;
    
    @Autowired
	private CustomerCredRepository customerRepository;
    
    // only supports ahli.03.jo.jo,ahli.01.uk.uk and warehousebank
    public String addAccount(CustomerCred accountInfo){
    	String message="";
    	// verify Account ID---
    	BankInfo bankInfo = bankRepository.findByBankID(accountInfo.getBankID());
    	String token = directAuthenticationService.login(accountInfo.getUserName(), accountInfo.getPassword(),
				bankInfo.getConsumerKey(), bankInfo.getRootUrl()+bankInfo.getDirectlogin());
    	
    	// URL with Version
    	String apiUrl = bankInfo.getRootUrl()+bankInfo.getVersion();
    	List<Account> accounts=fetchAllBankAccounts(token,apiUrl);
    	
    	// check entered account exist or not
    	boolean validAccount = false;
    	for ( Account account : accounts) {
    		if(account.getId().equalsIgnoreCase(accountInfo.getAccountID())){
    			validAccount = true;
    			break;
    		}
    	}
    	
    	if(validAccount){
    		// save account
        	customerRepository.save(accountInfo);
        	message = "Success";
    	}else {
    		message = accountInfo.getAccountID()+" Not a Valid Account for bank "+accountInfo.getBankID();
    	}
    	
    	return message;
    }
    
    public String fetchAccountsBalance(String token,String apiUrl,String bankID,String accID) {
    	HttpEntity<Void> req = setTokenInRequest(token);
    	 String acctDetailsapi = String.format("%s/my/banks/%s/accounts/%s/account", apiUrl, bankID, accID);
    	
    	Account account = restTemplate.exchange(acctDetailsapi, HttpMethod.GET, req,  
    			Account.class).getBody();
    	return account.getBalance().toString();
    }
    
    // Fetching all the accounts for banks
    public List <Account> fetchAllBankAccounts(String token,String apiUrl){
    	HttpEntity<Void> req = setTokenInRequest(token);
    	String allAccountsapi = String.format("%s/my/accounts",apiUrl);
     	Account[] accounts = restTemplate.exchange(allAccountsapi, HttpMethod.GET, req,  
    			Account[].class).getBody();
     	return Arrays.asList(accounts);
    }
    
    
    public List<AccountBalanaceData> fetchAccountsBalance() {
    	List<CustomerCred> accounts = customerRepository.findAll();
    	List<AccountBalanaceData> balanceList = new ArrayList<>();
    	accounts.forEach(acct ->{
    		
    		BankInfo bankInfo = bankRepository.findByBankID(acct.getBankID());
    		String token = directAuthenticationService.login(acct.getUserName(), acct.getPassword(),
    				bankInfo.getConsumerKey(), bankInfo.getRootUrl()+bankInfo.getDirectlogin());
    		String apiUrl = bankInfo.getRootUrl()+bankInfo.getVersion();
    		String balance = fetchAccountsBalance(token,apiUrl,acct.getBankID(),acct.getAccountID());
    		AccountBalanaceData data = new AccountBalanaceData(acct.getBankID(),acct.getAccountID(),
    				balance);
    		System.out.println("Data--->"+data.toString());
    		balanceList.add(data);
    	});
    	
    	return balanceList;
    	
    }
    
    private HttpEntity<Void> setTokenInRequest(String token) {
        HttpHeaders authHeaders = new HttpHeaders();
        String tokenHeader = String.format("DirectLogin token=%s", token);
        authHeaders.add(HttpHeaders.AUTHORIZATION, tokenHeader);
        return new HttpEntity<>(null, authHeaders);
    }
    
    public void insertBankData(BankInfo bankInfo){
    	System.out.println("Details----"+bankInfo.toString());
    	bankRepository.save(bankInfo);
    	
    }
    
    public void insertCustomerData(CustomerCred customer){
    	System.out.println("Details----"+customer.toString());
    	customerRepository.save(customer);
    	
    }

}
