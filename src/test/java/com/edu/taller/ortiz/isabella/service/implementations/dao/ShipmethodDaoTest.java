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
import com.edu.taller.ortiz.isabella.dao.implementations.ShipMethodDaoImp;
import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;

@SpringBootTest
@ContextConfiguration(classes = Application.class)
class ShipmethodDaoTest {

	@Autowired
	private ShipMethodDaoImp dao;
	
	private Shipmethod s;
	
	@BeforeEach
	void setUpSave() {
		s = new Shipmethod();
		s.setModifieddate(LocalDate.of(2022, 3, 26));
		s.setName("carro");
		s.setRowguid(4);
		s.setShipbase(BigDecimal.valueOf(10000.0));
		s.setShiprate(BigDecimal.valueOf(11000.0));
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveTest() {

		assertNotNull(dao);
		
		dao.save(s);

		Shipmethod saved = dao.findById(s.getShipmethodid());
		
		assertNotNull(saved);
		
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTest() {

		assertNotNull(dao);

		dao.save(s);
		
		Shipmethod edited = dao.findById(s.getShipmethodid());
		edited.setName("bus");
		edited.setRowguid(8);

		dao.update(s);
		
		
		
		assertAll("shipmethod update",
				() -> assertEquals(8, edited.getRowguid()),
				() -> assertEquals("bus", edited.getName())
				);
		
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTest() {

		assertNotNull(dao);
		
		dao.save(s);
		
		dao.delete(s.getShipmethodid());

		assertNull(dao.findById(s.getShipmethodid()));
		
	}
	
	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findTest() {

		assertNotNull(dao);

		dao.save(s);
	
		assertEquals(s.getName(), dao.findById(s.getShipmethodid()).getName());
		
	}

	@Test
//	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void findAllTest() {

		assertNotNull(dao);

		dao.save(s);

		assertTrue(dao.findAll().size()>0);
	}
	
	

}
