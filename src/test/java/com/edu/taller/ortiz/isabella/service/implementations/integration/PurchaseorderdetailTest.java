package com.edu.taller.ortiz.isabella.service.implementations.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.edu.taller.ortiz.isabella.Application;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;
import com.edu.taller.ortiz.isabella.repository.interfaces.PurchaseorderdetailRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.PurchaseorderheaderRepository;
import com.edu.taller.ortiz.isabella.service.interfaces.PurchaseorderdetailService;

@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
class PurchaseorderdetailTest {

	private PurchaseorderdetailRepository dr;

	private PurchaseorderheaderRepository hr;
	
	private Purchaseorderdetail d;
	


	private PurchaseorderdetailService ds;
	
	@Autowired
	public PurchaseorderdetailTest(PurchaseorderdetailService ds, PurchaseorderheaderRepository hr, PurchaseorderdetailRepository dr) {
		this.dr = dr;
		this.hr = hr;
		this.ds = ds;
	}
	
	void setUp() {
		d = new Purchaseorderdetail();
		d.setOrderqty(0);
		d.setUnitprice(new BigDecimal(0));
		Purchaseorderheader h = new Purchaseorderheader();
		hr.save(h);
		d.setPurchaseorderheader(h);
	}
	
	@Test
	void addTest() {
		setUp();
		assertTrue(ds.add(d));
	}
	
	
	
	@Test
	void noAddByHeaderTest() {
		setUp();
		Purchaseorderheader h = new Purchaseorderheader();
		h.setPurchaseorderid(123);
		d.setPurchaseorderheader(h);
		assertFalse(ds.add(d));
	}
	
	
	void setUpUpdate() {
		setUp();

		dr.save(d);
	}
	
	
	@Test
	void editTest() {
		setUpUpdate();
		assertTrue(ds.edit(d));
	}
	
	
	
	@Test
	void noEditByHeaderTest() {
		setUpUpdate();
		Purchaseorderheader h = new Purchaseorderheader();
		h.setPurchaseorderid(123);
		d.setPurchaseorderheader(h);		
		assertFalse(ds.add(d));
	}
	
	@Test
	void noEditTest() {
		setUp();
		d.setId(1235);
		assertFalse(ds.edit(d));
	}

}
