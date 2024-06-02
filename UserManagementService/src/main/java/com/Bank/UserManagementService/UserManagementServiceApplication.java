package com.Bank.UserManagementService;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.Bank.UserManagementService.Filter.TokenFilter;


@SpringBootApplication
public class UserManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementServiceApplication.class, args);
		System.out.println("User Management Service Started.......");
	}
	
	@Bean
	public FilterRegistrationBean getFilter() {
		
		FilterRegistrationBean filterReg= new FilterRegistrationBean();
		filterReg.setFilter(new TokenFilter());
		

		
		filterReg.addUrlPatterns("/BankApi/get-all-users");
		filterReg.addUrlPatterns("/BankApi/user/*") ; //getuserbyid
		filterReg.addUrlPatterns("/BankApi/update-user/*");
		
		return filterReg;
	}
}
