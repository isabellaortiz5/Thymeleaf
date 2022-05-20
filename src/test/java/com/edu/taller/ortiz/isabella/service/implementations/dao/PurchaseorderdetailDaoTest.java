package com.edu.taller.ortiz.isabella.service.implementations.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.edu.taller.ortiz.isabella.Application;
import com.edu.taller.ortiz.isabella.dao.implementations.PurchaseOrderDetailDaoImp;
import com.edu.taller.ortiz.isabella.dao.implementations.PurchaseOrderHeaderDaoImp;
import com.edu.taller.ortiz.isabella.model.hr.Employee;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;

@SpringBootTest
@ContextConfiguration(classes = Application.class)

class PurchaseorderdetailDaoTest {

	@Autowired
	private PurchaseOrderDetailDaoImp dao;
	
	@Autowired
	private PurchaseOrderHeaderDaoImp hDao;
	
	private Purchaseorderdetail p;
	
	private Purchaseorderheader h;
	
	private Employee e;
	
	@BeforeEach
	void setUpSave() {
		
		p = new Purchaseorderdetail();
		p.setDuedate(LocalDate.of(2022, 3, 11));
		p.setModifieddate(LocalDate.of(2022, 4, 26));
		p.setOrderqty(2);
		p.setProductid(456);
		p.setReceivedqty(BigDecimal.valueOf(8.0));
		p.setRejectedqty(BigDecimal.valueOf(3.0));
		p.setUnitprice(BigDecimal.valueOf(90.0));
		
		e =new Employee();
		e.setBusinessentityid(12);
		
		h = new Purchaseorderheader();
		h.setEmployeeid(e.getBusinessentityid());
		hDao.save(h);
		
		p.setPurchaseorderheader(h);
		
		

	}
	
	@Test
//	@Transactional
//	(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTest() {

		assertNotNull(dao);
		
		dao.save(p);

		Purchaseorderdetail saved = dao.findById(p.getId());
		
		assertNotNull(saved);
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(dao);
		
		dao.save(p);
		
		Purchaseorderdetail edited = dao.findById(p.getId());
		edited.setOrderqty(3);
		edited.setReceivedqty(BigDecimal.valueOf(10.0));
		edited.setRejectedqty(BigDecimal.valueOf(2.0));

		dao.update(p);
		
		
		
		assertAll("purchaseorderdetail update",
				() -> assertEquals(3, edited.getOrderqty()),
				() -> assertEquals(BigDecimal.valueOf(10.0), edited.getReceivedqty()),
				() -> assertEquals(BigDecimal.valueOf(2.0), edited.getRejectedqty())
				);
		
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {

		assertNotNull(dao);
		
		dao.save(p);
		
		dao.delete(p.getId());

		assertNull(dao.findById(p.getId()));
		
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findTest() {

		assertNotNull(dao);

		
		dao.save(p);
	
		assertEquals(p.getOrderqty(), dao.findById(p.getId()).getOrderqty());
		
	}

	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTest() {

		assertNotNull(dao);
		
		dao.save(p);

		assertTrue(dao.findAll().size()>0);
	}
}
