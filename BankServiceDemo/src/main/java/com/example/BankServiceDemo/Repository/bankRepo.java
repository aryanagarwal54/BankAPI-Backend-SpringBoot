package com.example.BankServiceDemo.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BankServiceDemo.Model.Bank;


@Repository
public interface bankRepo extends MongoRepository<Bank,String>{
	@Query("{userId:?0}")
	public List<Bank> getBankDetailsByUserId(int userId);
	
	@Query("{userId:?0 , accNo:?1}")
	public Bank getBankDetailsByUserIdAndAccNo(int userId, String accNo);
	
	@Query("{accNo:?0}")
	public Bank getBankDetailsByAccNo(String accNo);
}
