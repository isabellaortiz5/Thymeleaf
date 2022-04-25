package com.edu.taller.ortiz.isabella.repository.interfaces;
import org.springframework.data.repository.CrudRepository;

import com.edu.taller.ortiz.isabella.user.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Long>{
	UserEntity findByUsername(String userName);
	
}
