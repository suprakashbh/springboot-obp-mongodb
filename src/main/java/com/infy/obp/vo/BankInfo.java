package com.infy.obp.vo;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
public class BankInfo {
	@Id
	String bankID;
	String bankName;
	String rootUrl;
	String version;
	String directlogin;
	String consumerKey;
}
