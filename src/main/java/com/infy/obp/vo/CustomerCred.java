package com.infy.obp.vo;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class CustomerCred {
	
	String bankID;
	@Id
	String accountID;	
	String userName;
	String password;

}
