package com.edu.taller.ortiz.isabella.service.implementations;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;
import com.edu.taller.ortiz.isabella.repository.interfaces.PurchaseorderdetailRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.PurchaseorderheaderRepository;
import com.edu.taller.ortiz.isabella.service.interfaces.PurchaseorderdetailService;
@Service
public class PurchaseorderdetailServiceImp implements PurchaseorderdetailService {

	private PurchaseorderdetailRepository dr;
	private PurchaseorderheaderRepository hr;

	@Autowired
	public PurchaseorderdetailServiceImp(PurchaseorderheaderRepository hr, PurchaseorderdetailRepository dr) {
		this.dr = dr;
		this.hr = hr;
	}
	
	@Override
	public boolean add(Purchaseorderdetail d) {
		if (d == null)
			return false;
		if (d.getOrderqty().compareTo(0) < 0 ||
				d.getUnitprice().compareTo(BigDecimal.ZERO) < 0)
			return false;
		if (!hr.existsById(d.getPurchaseorderheader().getPurchaseorderid()))
			return false;
		dr.save(d);
		return true;
	}

	@Override
	public boolean edit(Purchaseorderdetail d) {
		if (d == null)
			return false;
		if (d.getOrderqty().compareTo(0) < 0 ||
				d.getUnitprice().compareTo(BigDecimal.ZERO) < 0)
			return false;
		if (!hr.existsById(d.getPurchaseorderheader().getPurchaseorderid()) ||
				!dr.existsById(d.getId()))
			return false;
		
		dr.save(d);
		
		return true;
	}

	@Override
	public Iterable<Purchaseorderdetail> findAll() {
		return dr.findAll();
	}
	

}
