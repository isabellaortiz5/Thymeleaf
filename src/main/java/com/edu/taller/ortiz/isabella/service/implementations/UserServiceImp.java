package com.edu.taller.ortiz.isabella.service.implementations;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.repository.interfaces.UserRepository;
import com.edu.taller.ortiz.isabella.user.UserEntity;
import com.edu.taller.ortiz.isabella.user.UserType;
import com.edu.taller.ortiz.isabella.service.interfaces.UserService;

@Service
@Transactional
public class UserServiceImp implements UserService {
	private UserRepository userRepo;

	public UserServiceImp(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public <Ue extends UserEntity> Ue save(Ue user) {
		if (user.getId() == 0 || user.getPassword().equals(null)) {
			if(user.getId() == 0) {
				throw new IllegalArgumentException("User id is empty.");
			} else if(user.getPassword() == null) {
				throw new IllegalArgumentException("The password is empty.");
			} 
		}
		userRepo.save(user);
		return user;
	}

	public Optional<UserEntity> findById(long id) {
		return userRepo.findById(id);
	}

	public void delete(UserEntity u) {
		userRepo.delete(u);
	}

	public void editPerson(long id, String userName, String password, UserType type) {
		if (id == 0 || password == null) {
			throw new IllegalArgumentException("Invalid arguments.");
		}
		UserEntity ur = userRepo.findById(id).get();
		ur.setUsername(userName);
		ur.setPassword(password);
		ur.setType(type);
		save(ur);
	}
}
