package com.edu.taller.ortiz.isabella.repository.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.edu.taller.ortiz.isabella.model.person.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
