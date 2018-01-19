package com.infy.obp.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.infy.obp.vo.CustomerCred;

public interface CustomerCredRepository extends MongoRepository<CustomerCred,String>{
	
	public CustomerCred findByUserName(String userName);

}
