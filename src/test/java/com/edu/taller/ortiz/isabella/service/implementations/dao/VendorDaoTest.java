package com.edu.taller.ortiz.isabella.service.implementations.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.Application;
import com.edu.taller.ortiz.isabella.dao.implementations.VendorDaoImp;
import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;

@SpringBootTest
@ContextConfiguration(classes = Application.class)

class VendorDaoTest {

	@Autowired
	private VendorDaoImp dao;
	
	private Vendor v;
	
	@BeforeEach
	void setUpSave() {
		v = new Vendor();
		v.setAccountnumber("12345");
		v.setName("Carlos");
		v.setActiveflag("Yes");	
		v.setCreditrating(5);
		v.setModifieddate(LocalDate.of(2022, 4, 28));
		v.setPreferredvendorstatus("available");
		v.setPurchasingwebserviceurl("google.com");

	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTest() {

		assertNotNull(dao);

		dao.save(v);

		Vendor saved = dao.findById(v.getBusinessentityid());
		
		assertNotNull(saved);
		
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(dao);

		dao.save(v);
		
		Vendor edited = dao.findById(v.getBusinessentityid());
		edited.setName("Sofia");
		edited.setActiveflag("No");
		edited.setCreditrating(3);

		dao.update(v);
		
		
		assertAll("shipmethod update",
				() -> assertEquals("Sofia", edited.getName()),
				() -> assertEquals("No", edited.getActiveflag()),
				() -> assertEquals(3, edited.getCreditrating())
				);
		
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {

		assertNotNull(dao);
		
		dao.save(v);
		
		dao.delete(v.getBusinessentityid());

		assertNull(dao.findById(v.getBusinessentityid()));
		
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findTest() {

		assertNotNull(dao);

		dao.save(v);
	
		assertEquals(v.getName(), dao.findById(v.getBusinessentityid()).getName());
		
	}

	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(v);
	
		Vendor s1 = new Vendor();
		v.setName("Daniel");
		v.setActiveflag("Yes");
		v.setCreditrating(4);
		
		dao.save(s1);
		assertTrue(dao.findAll().size()>0);
	}
}
