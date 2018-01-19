package com.infy.obp.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class AccountBalanaceData {
	
	private String bankID;
	private String accountNo;
	private String accountBalance;
	
	
	public AccountBalanaceData(String bankID, String accountNo, String accountBalance) {
		super();
		this.bankID = bankID;
		this.accountNo = accountNo;
		this.accountBalance = accountBalance;
	}
	
	

}
