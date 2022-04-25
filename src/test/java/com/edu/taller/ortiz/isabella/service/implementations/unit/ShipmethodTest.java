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
import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;
import com.edu.taller.ortiz.isabella.repository.interfaces.ShipmethodRespository;
import com.edu.taller.ortiz.isabella.service.implementations.ShipmethodServiceImp;

@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
class ShipmethodTest {
	
	@Mock
	private ShipmethodRespository sr;
	
	@InjectMocks
	private ShipmethodServiceImp ss;
	
	private Shipmethod s;
	
	/*@Autowired
	public ShipmethodTest(ShipmethodRespository sr, ShipmethodService ss) {
		this.sr = sr;
		this.ss = ss;
	}*/
	
	
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
	
	@Test
	void noAddByBaseTest() {
		setUp();
		s.setShipbase(new BigDecimal(-0.1));
		assertFalse(ss.add(s));
	}
	
	@Test
	void noAddByRateTest() {
		setUp();
		s.setShiprate(new BigDecimal(-0.1));
		assertFalse(ss.add(s));
	}
	
	@Test
	void noAddByNameTest() {
		setUp();
		s.setName("nam");
		assertFalse(ss.add(s));
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
		when(sr.existsById(s.getShipmethodid())).thenReturn(true);
		assertTrue(ss.edit(s));
	}
	
	@Test
	void noEditByBaseTest() {
		setUpEdit();
		s.setShipbase(new BigDecimal(-0.1));
		assertFalse(ss.edit(s));
	}
	
	@Test
	void noEditByRateTest() {
		setUpEdit();
		s.setShiprate(new BigDecimal(-0.1));
		assertFalse(ss.edit(s));
	}
	
	@Test
	void noEditByNameTest() {
		setUpEdit();
		s.setName("nam");
		assertFalse(ss.edit(s));
	}
	
	@Test
	void noEditTest() {
		s = new Shipmethod();
		s.setShipbase(new BigDecimal(0));
		s.setShiprate(new BigDecimal(0));
		s.setName("rapido");
		when(sr.existsById(s.getShipmethodid())).thenReturn(false);
		assertFalse(ss.edit(s));
	}

}
