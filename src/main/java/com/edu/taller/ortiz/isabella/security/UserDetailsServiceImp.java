package com.edu.taller.ortiz.isabella.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.edu.taller.ortiz.isabella.repository.interfaces.UserRepository;
import com.edu.taller.ortiz.isabella.user.UserEntity;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImp(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByUsername(username);

		if (userEntity != null) {
			User.UserBuilder builder = User.withUsername(username).password(userEntity.getPassword()).roles(userEntity.getType().toString());
			return builder.build();
			
		} else {
			throw new UsernameNotFoundException("Username not found.");
		}
	}
}