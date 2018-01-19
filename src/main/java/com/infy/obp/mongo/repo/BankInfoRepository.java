package com.infy.obp.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.infy.obp.vo.BankInfo;

public interface BankInfoRepository extends MongoRepository<BankInfo,String>{
	
	public BankInfo findByBankID(String bankID);

}
