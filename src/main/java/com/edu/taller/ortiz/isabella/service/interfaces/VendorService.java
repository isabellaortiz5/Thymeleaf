package com.edu.taller.ortiz.isabella.service.interfaces;


import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;

public interface VendorService {
	
	public boolean add(Vendor v);
	public boolean edit(Vendor v);
	public void delete(Integer id);
	public Iterable<Vendor> findAll();
	public Vendor findById(Integer id);

}
