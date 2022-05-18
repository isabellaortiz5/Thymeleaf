package com.edu.taller.ortiz.isabella.service.interfaces;

import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;

public interface PurchaseorderdetailService {
	
	public boolean add(Purchaseorderdetail d);
	public boolean edit(Purchaseorderdetail d);
	public void delete(Integer id);
	public Iterable<Purchaseorderdetail> findAll();
	public Purchaseorderdetail findById(Integer id);

}
