package com.edu.taller.ortiz.isabella;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.edu.taller.ortiz.isabella.service.implementations.BusinessentityServiceImp;
import com.edu.taller.ortiz.isabella.service.implementations.EmployeeServiceImp;
import com.edu.taller.ortiz.isabella.service.implementations.PersonServiceImp;
import com.edu.taller.ortiz.isabella.service.implementations.PurchaseorderdetailServiceImp;
import com.edu.taller.ortiz.isabella.service.implementations.PurchaseorderheaderServiceImp;
import com.edu.taller.ortiz.isabella.service.implementations.ShipmethodServiceImp;
import com.edu.taller.ortiz.isabella.service.implementations.UserServiceImp;
import com.edu.taller.ortiz.isabella.service.implementations.VendorServiceImp;
import com.edu.taller.ortiz.isabella.service.interfaces.UserService;
import com.edu.taller.ortiz.isabella.user.UserEntity;
import com.edu.taller.ortiz.isabella.user.UserType;

@SpringBootApplication
@ComponentScan(basePackages = {"com.edu.taller.ortiz.isabella"})	
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(Application.class, args);
		
		UserService us = c.getBean(UserServiceImp.class);
		BusinessentityServiceImp bs = c.getBean(BusinessentityServiceImp.class);
		EmployeeServiceImp es = c.getBean(EmployeeServiceImp.class);
		PersonServiceImp ps =c.getBean(PersonServiceImp.class);
		PurchaseorderdetailServiceImp pds = c.getBean(PurchaseorderdetailServiceImp.class);
		PurchaseorderheaderServiceImp phs = c.getBean(PurchaseorderheaderServiceImp.class);
		ShipmethodServiceImp ss =c.getBean(ShipmethodServiceImp.class);
		VendorServiceImp vs = c.getBean(VendorServiceImp.class);
		
		
		
		
		//USER ADMINISTRATOR
		
		UserEntity u1 = new UserEntity();
		u1.setId(111);
		u1.setUsername("admin");
		u1.setPassword("{noop}admin");
		u1.setType(UserType.ADMINISTRATOR);
		
		us.save(u1);
		
		//USER OPERATOR
		
		UserEntity u2 = new UserEntity();
		u2.setId(222);
		u2.setUsername("operator");
		u2.setPassword("{noop}operator");
		u2.setType(UserType.OPERATOR);
		
		us.save(u2);
	}
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

}
