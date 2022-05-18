package com.edu.taller.ortiz.isabella.service.interfaces;

import com.edu.taller.ortiz.isabella.model.person.Person;

public interface PersonService {
	public boolean add(Person d);
	public Iterable<Person> findAll();
}
