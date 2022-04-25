package com.edu.taller.ortiz.isabella;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import com.edu.taller.ortiz.isabella.service.implementations.UserServiceImp;
import com.edu.taller.ortiz.isabella.service.interfaces.UserService;
import com.edu.taller.ortiz.isabella.user.UserEntity;
import com.edu.taller.ortiz.isabella.user.UserType;

@SpringBootApplication
@ComponentScan(basePackages = {"com.edu.taller.ortiz.isabella"})	
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext c = SpringApplication.run(Application.class, args);
		
		UserService u = c.getBean(UserServiceImp.class);
		
		//USER ADMINISTRATOR
		
		UserEntity u1 = new UserEntity();
		u1.setId(111);
		u1.setUsername("admin");
		u1.setPassword("{noop}admin");
		u1.setType(UserType.ADMINISTRATOR);
		
		u.save(u1);
		
		//USER OPERATOR
		
		UserEntity u2 = new UserEntity();
		u2.setId(222);
		u2.setUsername("operator");
		u2.setPassword("{noop}operator");
		u2.setType(UserType.OPERATOR);
		
		u.save(u2);
	}

}
