package com.edu.taller.ortiz.isabella.service.interfaces;

import com.edu.taller.ortiz.isabella.model.person.Businessentity;

public interface BusinessentityService {
	public boolean add(Businessentity d);
	public Iterable<Businessentity> findAll();
}
