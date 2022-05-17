package com.edu.taller.ortiz.isabella.dao.interfaces;

import java.util.List;

import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;

public interface PurchaseOrderHeaderDao {
	public Purchaseorderheader save(Purchaseorderheader purchaseorderheader);
	public Purchaseorderheader update(Purchaseorderheader purchaseorderheader);
	public void delete (Purchaseorderheader purchaseorderheader);
	public Purchaseorderheader findById(Integer purchaseorderheaderId);
	public List<Purchaseorderheader> findAll();
	public List<Purchaseorderheader> findByShipmethodId(Integer shipmethodId);
	public List<Purchaseorderheader> findByVendorId(Integer vendorId);
	public boolean existsById(Integer purchaseorderheaderId);
}
