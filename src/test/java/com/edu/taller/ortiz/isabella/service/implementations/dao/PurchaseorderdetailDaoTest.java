package com.edu.taller.ortiz.isabella.service.implementations.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.implementations.PurchaseOrderDetailDaoImp;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;

class PurchaseorderdetailDaoTest {

	@Autowired
	private PurchaseOrderDetailDaoImp dao;
	
	private Purchaseorderdetail p;
	

	void setUpSave() {
		p = new Purchaseorderdetail();
		p.setOrderqty(2);
		p.setReceivedqty(BigDecimal.valueOf(8.0));
		p.setRejectedqty(BigDecimal.valueOf(3.0));
		

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(p);

		Purchaseorderdetail saved = dao.findById(p.getId());
		
		assertEquals(p.getOrderqty(), saved.getOrderqty());
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(p);
		
		p.setOrderqty(3);
		p.setReceivedqty(BigDecimal.valueOf(10.0));
		p.setRejectedqty(BigDecimal.valueOf(2.0));

		dao.update(p);
		
		Purchaseorderdetail edited = dao.findById(p.getId());
		
		assertAll("shipmethod update",
				() -> assertEquals(3, edited.getOrderqty()),
				() -> assertEquals(BigDecimal.valueOf(10.0), edited.getReceivedqty()),
				() -> assertEquals(BigDecimal.valueOf(2.0), edited.getRejectedqty())
				);
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(p);
		
		dao.delete(p.getId());

		assertNull(dao.findById(p.getId()));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(p);
	
		assertEquals(p.getOrderqty(), dao.findById(p.getId()).getOrderqty());
		
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(p);
	
		Purchaseorderdetail s1 = new Purchaseorderdetail();
		p.setOrderqty(13);
		p.setReceivedqty(BigDecimal.valueOf(9.0));
		p.setRejectedqty(BigDecimal.valueOf(1.0));
		
		dao.save(s1);
		assertEquals(2, dao.findAll().size());
	}
}
