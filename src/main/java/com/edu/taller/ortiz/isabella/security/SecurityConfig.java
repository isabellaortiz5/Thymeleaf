package com.edu.taller.ortiz.isabella.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.edu.taller.ortiz.isabella.user.UserType;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		
		httpSecurity.cors().disable().csrf().disable().authorizeRequests()
		//admin permissions.
			// vendors.
		.antMatchers("/vendors*").permitAll().antMatchers("/vendors/add/**")
		.hasRole(UserType.ADMINISTRATOR.toString()).antMatchers("/vendors/edit/**")
		.hasRole(UserType.ADMINISTRATOR.toString())
		
			// ship-method.
		.antMatchers("/ship-method*").permitAll().antMatchers("/ship-method/add/**")
		.hasRole(UserType.ADMINISTRATOR.toString()).antMatchers("/ship-method/edit/**")
		.hasRole(UserType.ADMINISTRATOR.toString())
		
		//operator permissions.
		// purchase-order-detail.
		
		.antMatchers("/purchase-order-detail*").permitAll().antMatchers("/purchase-order-detail/add/**")
		.hasRole(UserType.OPERATOR.toString()).antMatchers("/purchase-order-detail/edit/**")
		.hasRole(UserType.OPERATOR.toString())
		
			// purchase-order-head.
		.antMatchers("/purchase-order-head*").permitAll().antMatchers("/purchase-order-head/add/**")
		.hasRole(UserType.OPERATOR.toString()).antMatchers("/purchase-order-head/edit/**")
		.hasRole(UserType.OPERATOR.toString())
		
		.antMatchers("/**")
		.authenticated().anyRequest().permitAll().and().formLogin().loginPage("/login").defaultSuccessUrl("/")
		.failureUrl("/login?error").permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
		.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		
	}
	
}
