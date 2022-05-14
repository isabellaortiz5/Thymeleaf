package com.edu.taller.ortiz.isabella.service.interfaces;

import java.util.Optional;

import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;

public interface VendorService {
	
	public boolean add(Vendor v);
	public boolean edit(Vendor v);
	public Iterable<Vendor> findAll();
	public Optional<Vendor> findById(Integer id);

}
