package com.edu.taller.ortiz.isabella.service.implementations.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.implementations.ShipMethodDaoImp;
import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;


class ShipmethodDaoTest {

	@Autowired
	private ShipMethodDaoImp dao;
	
	private Shipmethod s;
	

	void setUpSave() {
		s = new Shipmethod();
		s.setShipbase(BigDecimal.valueOf(10000.0));
		s.setShiprate(BigDecimal.valueOf(11000.0));
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(s);

		Shipmethod saved = dao.findById(s.getShipmethodid());
		
		assertEquals(s.getShipbase(), saved.getShipbase());
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(s);
		
		s.setShipbase(BigDecimal.valueOf(10.0));
		s.setShiprate(BigDecimal.valueOf(11.0));

		dao.update(s);
		
		Shipmethod edited = dao.findById(s.getShipmethodid());
		
		assertAll("shipmethod update",
				() -> assertEquals("Cambiada", edited.getShipbase()),
				() -> assertEquals("Tambien cambiado", edited.getShiprate())
				);
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(s);
		
		dao.delete(s.getShipmethodid());

		assertNull(dao.findById(s.getShipmethodid()));
		
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(s);
	
		assertEquals(s.getShipbase(), dao.findById(s.getShipmethodid()).getShipbase());
		
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTest() {

		assertNotNull(dao);
		
		setUpSave();
		
		dao.save(s);
	
		Shipmethod s1 = new Shipmethod();
		s.setShipbase(BigDecimal.valueOf(10.0));
		s.setShiprate(BigDecimal.valueOf(11.0));

		
		dao.save(s1);
		assertEquals(2, dao.findAll().size());
	}
	
	

}
