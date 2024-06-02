package com.example.BankServiceDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
"com.example.BankServiceDemo.Controller", "com.example.BankServiceDemo.Model", "com.example.BankServiceDemo.Repository", "com.example.BankServiceDemo.Service","com.example.BankServiceDemo.RabbitMqConfig"})

public class BankServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankServiceDemoApplication.class, args);
		System.out.println("Bank Services started...");
	}

}
