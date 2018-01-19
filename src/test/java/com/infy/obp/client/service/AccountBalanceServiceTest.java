package com.infy.obp.client.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.infy.obp.AbstractTest;
import com.infy.obp.vo.AccountBalanaceData;
import com.infy.obp.vo.BankInfo;
import com.infy.obp.vo.CustomerCred;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountBalanceServiceTest extends AbstractTest	 {
	
	@Autowired private AccountBalanceService  accountBalanceService;
    
	@Test
	public void fetchAccountBalance() {
		
		List<AccountBalanaceData> balance = accountBalanceService.fetchAccountsBalance();
		System.out.println("Balance---->"+balance.size());
		
	}
	
	
	/*//@Test
    public void fetchAccountBalance() {
		System.out.println("authToken----->"+authToken);
		// for apisandbox.openbankproject
		//String balance = accountBalanceService.fetchAccountsBalance(authToken, "test-bank", "1000");
		// for BNP Paribas Sandbox
		String balance = accountBalanceService.fetchAccountsBalance(authToken, "warehousebank", "7009813099");
		System.out.println("Balance---->"+balance);
		
	}*/
    
   
	@Test
	public void insertData(){
		// Add Bank Info
		BankInfo bankInfo = new BankInfo();
    	bankInfo.setBankID("ahli.01.uk.uk");
    	// check condition and put consumer key  -- Bank and consumer key from mongodb
    	bankInfo.setConsumerKey("wvfxnvfsdxokq04eyg5pmqnehxz4yuvzjltu44ik");
    	bankInfo.setDirectlogin("/my/logins/direct");
    	bankInfo.setRootUrl("https://ahlisandbox.com");
    	bankInfo.setVersion("/obp/v3.0.0");    	
    	accountBalanceService.insertBankData(bankInfo);
    	
    	CustomerCred customer = new CustomerCred();
    	customer.setBankID("warehousebank");
    	customer.setUserName("test");
    	customer.setPassword("gene253@BNP");
    	customer.setAccountID("7009813099");
    	
    	accountBalanceService.insertCustomerData(customer);
    	
    	
	}
	
	/* @Test
    public void insertData(){
    	BankInfo bankInfo = new BankInfo();
    	bankInfo.setBankID("warehousebank");
    	bankInfo.setConsumerKey("qphg0vmrfcq3zzruxd4tabwb3pmvxu2qc2akeroh");
    	bankInfo.setDirectlogin("/my/logins/direct");
    	bankInfo.setRootUrl("https://bnpparibas-api.openbankproject.com");
    	bankInfo.setVersion("/obp/v3.0.0");
    	
    	accountBalanceService.insertBankData(bankInfo);
    	System.out.println("success---->");
    }*/
    
    /*@Test
    public void insertCustData(){
    	CustomerCred customer = new CustomerCred();
    	customer.setBankID("test-bank");
    	customer.setUserName("robert.uk.29@example.com");
    	customer.setPassword("d9c663");
    	customer.setAccountID("1000");
    	
    	accountBalanceService.insertCustomerData(customer);
    	System.out.println("success---->");
    }*/
    
    
	

}

