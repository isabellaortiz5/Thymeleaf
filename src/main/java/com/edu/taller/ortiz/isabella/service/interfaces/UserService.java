package com.edu.taller.ortiz.isabella.service.interfaces;

import java.util.Optional;

import com.edu.taller.ortiz.isabella.user.UserEntity;
import com.edu.taller.ortiz.isabella.user.UserType;

public interface UserService {
	public <Ue extends UserEntity> Ue save(Ue user);

	public Optional<UserEntity> findById(long id);

	public void delete(UserEntity us);
	
	public void editPerson(long id, String username, String password, UserType type);
}
