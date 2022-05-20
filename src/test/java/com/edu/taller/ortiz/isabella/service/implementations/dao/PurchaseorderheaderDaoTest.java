package com.edu.taller.ortiz.isabella.service.implementations.dao;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.Application;
import com.edu.taller.ortiz.isabella.dao.implementations.PurchaseOrderHeaderDaoImp;
import com.edu.taller.ortiz.isabella.model.hr.Employee;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;

@SpringBootTest
@ContextConfiguration(classes = Application.class)

class PurchaseorderheaderDaoTest {

	@Autowired
	private PurchaseOrderHeaderDaoImp dao;
	
	private Purchaseorderheader p;
	
	private Employee e;
	
	@BeforeEach
	void setUpSave() {
		p = new Purchaseorderheader();
		p.setFreight(BigDecimal.valueOf(3.0));
		p.setRevisionnumber(12);
		p.setOrderdate(LocalDate.of(2022, 3, 16));
		p.setModifieddate(LocalDate.of(2022, 4, 26));
		p.setShipdate(LocalDate.of(2022, 5, 2));
		p.setStatus(2);
		p.setSubtotal(BigDecimal.valueOf(35.0));
		p.setTaxamt(BigDecimal.valueOf(3.0));
		
		e =new Employee();
		e.setBusinessentityid(12);
		
		p.setEmployeeid(e.getBusinessentityid());
	

	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTest() {

		assertNotNull(dao);
		
		dao.save(p);

		Purchaseorderheader saved = dao.findById(p.getPurchaseorderid());
		
		assertNotNull(saved);		
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(dao);
		
		dao.save(p);
		
		Purchaseorderheader edited = dao.findById(p.getPurchaseorderid());
		
		edited.setStatus(6);
		edited.setRevisionnumber(15);
		

		dao.update(p);
		
		
		assertAll("purchaseorderheadder update",
				() -> assertEquals(6, edited.getStatus()),
				() -> assertEquals(15, edited.getRevisionnumber())
				);
		
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {

		assertNotNull(dao);
		
		dao.save(p);
		
		dao.delete(p.getPurchaseorderid());

		assertNull(dao.findById(p.getPurchaseorderid()));
		
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findTest() {

		assertNotNull(dao);

		dao.save(p);
	
		assertEquals(p.getRevisionnumber(), dao.findById(p.getPurchaseorderid()).getRevisionnumber());
		
	}

	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTest() {

		assertNotNull(dao);

		dao.save(p);
		assertTrue(dao.findAll().size()>0);
	}

}
