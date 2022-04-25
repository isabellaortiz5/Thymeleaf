package com.edu.taller.ortiz.isabella.service.implementations.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.edu.taller.ortiz.isabella.Application;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;
import com.edu.taller.ortiz.isabella.repository.interfaces.PurchaseorderdetailRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.PurchaseorderheaderRepository;
import com.edu.taller.ortiz.isabella.service.implementations.PurchaseorderdetailServiceImp;

@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
class PurchaseorderdetailTest {

	@Mock
	private PurchaseorderdetailRepository dr;
	@Mock
	private PurchaseorderheaderRepository hr;
	
	private Purchaseorderdetail d;
	
	
	@InjectMocks
	private PurchaseorderdetailServiceImp ds;
	
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
		when(hr.existsById(d.getPurchaseorderheader().getPurchaseorderid())).thenReturn(true);
		assertTrue(ds.add(d));
	}
	
	@Test
	void noAddByQtyTest() {
		setUp();
		d.setOrderqty(-1);
		assertFalse(ds.add(d));
	}
	
	@Test
	void noAddByUnitTest() {
		setUp();
		d.setUnitprice(new BigDecimal(-0.1));
		assertFalse(ds.add(d));
	}
	
	@Test
	void noAddByHeaderTest() {
		setUp();
		Purchaseorderheader h = new Purchaseorderheader();
		h.setPurchaseorderid(123);
		d.setPurchaseorderheader(h);
		assertFalse(ds.add(d));
	}
	
	
	
	
	
	@Test
	void editTest() {
		setUp();
		dr.save(d);
		when(hr.existsById(d.getPurchaseorderheader().getPurchaseorderid())).thenReturn(true);
		when(dr.existsById(d.getId())).thenReturn(true);
		assertTrue(ds.edit(d));
	}
	
	@Test
	void noEditByQtyTest() {
		setUp();
		dr.save(d);
		d.setOrderqty(-1);
		assertFalse(ds.add(d));
	}
	
	@Test
	void noEditByUnitTest() {
		setUp();
		dr.save(d);
		d.setUnitprice(new BigDecimal(-0.1));
		assertFalse(ds.add(d));
	}
	
	@Test
	void noEditByHeaderTest() {
		setUp();
		dr.save(d);
		Purchaseorderheader h = new Purchaseorderheader();
		h.setPurchaseorderid(123);
		d.setPurchaseorderheader(h);
		when(hr.existsById(d.getPurchaseorderheader().getPurchaseorderid())).thenReturn(false);
		
		assertFalse(ds.add(d));
	}
	
	@Test
	void noEditTest() {
		setUp();
		when(dr.existsById(d.getId())).thenReturn(false);
		assertFalse(ds.edit(d));
	}

}
