package com.edu.taller.ortiz.isabella.service.implementations.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.edu.taller.ortiz.isabella.Application;
import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;
import com.edu.taller.ortiz.isabella.repository.interfaces.ShipmethodRespository;
import com.edu.taller.ortiz.isabella.service.interfaces.ShipmethodService;

@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
class ShipmethodTest {
	
	private ShipmethodRespository sr;
	
	private ShipmethodService ss;
	
	private Shipmethod s;
	
	@Autowired
	public ShipmethodTest(ShipmethodRespository sr, ShipmethodService ss) {
		this.sr = sr;
		this.ss = ss;
	}
	
	
	void setUp() {
		s = new Shipmethod();
		s.setShipbase(new BigDecimal(0));
		s.setShiprate(new BigDecimal(0));
		s.setName("rapido");
	}
	
	@Test
	void addTest() {
		setUp();
		assertTrue(ss.add(s));
	}
	
	void setUpEdit() 
	{
		s = new Shipmethod();
		s.setShipbase(new BigDecimal(0));
		s.setShiprate(new BigDecimal(0));
		s.setName("rapido");
		sr.save(s);
	}
	
	
	@Test
	void editTest() {
		setUpEdit();
		s.setShipbase(new BigDecimal(0.1));
		s.setShiprate(new BigDecimal(0.1));
		s.setName("fast");
		assertTrue(ss.edit(s));
	}
	
	
	
	@Test
	void noEditTest() {
		s = new Shipmethod();
		s.setShipmethodid(156);
		s.setShipbase(new BigDecimal(0));
		s.setShiprate(new BigDecimal(0));
		s.setName("rapido");
		assertFalse(ss.edit(s));
	}

}
