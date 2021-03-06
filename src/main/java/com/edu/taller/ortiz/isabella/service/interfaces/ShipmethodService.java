package com.edu.taller.ortiz.isabella.service.interfaces;

import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;

public interface ShipmethodService {
	
	public boolean add(Shipmethod s);
	public boolean edit(Shipmethod s);
	public void delete(Integer id);
	public Iterable<Shipmethod> findAll();
	public Shipmethod findById(Integer id);
}
