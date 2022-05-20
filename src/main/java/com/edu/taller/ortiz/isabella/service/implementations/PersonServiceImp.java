package com.edu.taller.ortiz.isabella.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.model.person.Person;
import com.edu.taller.ortiz.isabella.repository.interfaces.PersonRepository;
import com.edu.taller.ortiz.isabella.service.interfaces.PersonService;


@Service
@Transactional
public class PersonServiceImp implements PersonService{
	
	private PersonRepository pr;
	
	@Autowired
	public PersonServiceImp (PersonRepository pr) {
		this.pr = pr;
	}
	
	@Override
	public boolean add(Person d) {
		pr.save(d);
		return true;
	}

	@Override
	public Iterable<Person> findAll() {
		return pr.findAll();
	}

}
