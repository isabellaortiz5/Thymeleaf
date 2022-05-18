package com.edu.taller.ortiz.isabella.dao.interfaces;

import java.util.List;

import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;

public interface VendorDao {
	public Vendor save(Vendor vendor);
	public Vendor update(Vendor vendor);
	public void delete (Integer vendorId);
	public Vendor findById(Integer vendorId);
	public List<Vendor> findAll();
	public List<Vendor> findByCreditrating(Integer creditrating);
	public List<Vendor> findByName(String name);
}
