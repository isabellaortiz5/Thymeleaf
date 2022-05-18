package com.edu.taller.ortiz.isabella.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.model.person.Businessentity;
import com.edu.taller.ortiz.isabella.repository.interfaces.BusinessentityRepository;
import com.edu.taller.ortiz.isabella.service.interfaces.BusinessentityService;

@Service
@Transactional
public class BusinessentityServiceImp implements BusinessentityService{
	private BusinessentityRepository ber;
	
	@Autowired
	public BusinessentityServiceImp (BusinessentityRepository ber) {
		this.ber = ber; 
	}
	
	@Override
	public boolean add(Businessentity d) {
		ber.save(d);
		return true;
	}

	@Override
	public Iterable<Businessentity> findAll() {
		return ber.findAll();
	}

}
