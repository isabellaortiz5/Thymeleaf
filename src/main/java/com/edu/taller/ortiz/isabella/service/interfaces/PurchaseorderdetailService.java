package com.edu.taller.ortiz.isabella.service.interfaces;

import java.util.Optional;

import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;

public interface PurchaseorderdetailService {
	
	public boolean add(Purchaseorderdetail d);
	public boolean edit(Purchaseorderdetail d);
	public void delete(Integer id);
	public Iterable<Purchaseorderdetail> findAll();
	public Purchaseorderdetail findById(Integer id);

}
