package com.edu.taller.ortiz.isabella.service.implementations.dao;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.implementations.PurchaseOrderHeaderDaoImp;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;



class PurchaseorderheaderDaoTest {

	@Autowired
	private PurchaseOrderHeaderDaoImp dao;
	
	private Purchaseorderheader p;
	

	void setUpSave() {
		p = new Purchaseorderheader();
		p.setFreight(BigDecimal.valueOf(3.0));
		p.setRevisionnumber(12);

		

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(p);

		Purchaseorderheader saved = dao.findById(p.getPurchaseorderid());
		
		assertEquals(p.getFreight(), saved.getFreight());
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(p);
		
		p.setFreight(BigDecimal.valueOf(4.0));
		p.setRevisionnumber(15);

		dao.update(p);
		
		Purchaseorderheader edited = dao.findById(p.getPurchaseorderid());
		
		assertAll("shipmethod update",
				() -> assertEquals(3, edited.getFreight()),
				() -> assertEquals(BigDecimal.valueOf(10.0), edited.getRevisionnumber())
				);
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(p);
		
		dao.delete(p.getPurchaseorderid());

		assertNull(dao.findById(p.getPurchaseorderid()));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(p);
	
		assertEquals(p.getFreight(), dao.findById(p.getPurchaseorderid()).getFreight());
		
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(p);
	
		Purchaseorderheader s1 = new Purchaseorderheader();
		p.setFreight(BigDecimal.valueOf(5.0));
		p.setRevisionnumber(20);
		
		dao.save(s1);
		assertEquals(2, dao.findAll().size());
	}

}
