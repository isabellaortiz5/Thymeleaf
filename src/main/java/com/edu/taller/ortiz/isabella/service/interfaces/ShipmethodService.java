package com.edu.taller.ortiz.isabella.service.interfaces;

import java.util.Optional;

import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;

public interface ShipmethodService {
	
	public boolean add(Shipmethod s);
	public boolean edit(Shipmethod s);
	public Iterable<Shipmethod> findAll();
	public Optional<Shipmethod> findById(Integer id);
}
