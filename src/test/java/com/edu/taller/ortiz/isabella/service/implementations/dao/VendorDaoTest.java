package com.edu.taller.ortiz.isabella.service.implementations.dao;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.implementations.VendorDaoImp;
import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;

class VendorDaoTest {

	@Autowired
	private VendorDaoImp dao;
	
	private Vendor v;
	

	void setUpSave() {
		v = new Vendor();
		v.setName("Carlos");
		v.setActiveflag("Yes");		v.setCreditrating(5);

	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(v);

		Vendor saved = dao.findById(v.getBusinessentityid());
		
		assertEquals(v.getName(), saved.getName());
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(v);
		
		v.setName("Sofia");
		v.setActiveflag("No");
		v.setCreditrating(3);

		dao.update(v);
		
		Vendor edited = dao.findById(v.getBusinessentityid());
		
		assertAll("shipmethod update",
				() -> assertEquals("Sofia", edited.getName()),
				() -> assertEquals("No", edited.getActiveflag()),
				() -> assertEquals(3, edited.getCreditrating())
				);
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(v);
		
		dao.delete(v.getBusinessentityid());

		assertNull(dao.findById(v.getBusinessentityid()));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(v);
	
		assertEquals(v.getName(), dao.findById(v.getBusinessentityid()).getName());
		
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(v);
	
		Vendor s1 = new Vendor();
		v.setName("Daniel");
		v.setActiveflag("Yes");
		v.setCreditrating(4);
		
		dao.save(s1);
		assertEquals(2, dao.findAll().size());
	}
}
