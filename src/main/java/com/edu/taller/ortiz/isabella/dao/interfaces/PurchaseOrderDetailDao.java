package com.edu.taller.ortiz.isabella.dao.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;

public interface PurchaseOrderDetailDao {
	public Purchaseorderdetail save(Purchaseorderdetail purchaseorderdetail);
	public Purchaseorderdetail update(Purchaseorderdetail purchaseorderdetail);
	public void delete (Integer purchaseorderdetailId);
	public Purchaseorderdetail findById(Integer purchaseorderdetailId);
	public List<Purchaseorderdetail> findAll();
	public boolean existsById(Integer purchaseorderdetailId);
	public List<Purchaseorderdetail> findByUnitPrice(BigDecimal unitprice);
	public List<Purchaseorderdetail> findByProductId(Integer productid);
}
