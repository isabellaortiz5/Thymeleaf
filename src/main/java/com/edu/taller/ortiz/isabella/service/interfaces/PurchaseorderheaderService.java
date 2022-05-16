package com.edu.taller.ortiz.isabella.service.interfaces;

import java.util.Optional;

import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;

public interface PurchaseorderheaderService {
	
	public boolean add(Purchaseorderheader h);
	public boolean edit(Purchaseorderheader h);
	public Iterable<Purchaseorderheader> findAll();
	Optional<Purchaseorderheader> findById(Integer id);
	void delete(Integer id);
}
