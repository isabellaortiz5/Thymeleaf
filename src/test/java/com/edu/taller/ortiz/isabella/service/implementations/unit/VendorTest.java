package com.edu.taller.ortiz.isabella.service.implementations.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.edu.taller.ortiz.isabella.Application;
import com.edu.taller.ortiz.isabella.model.person.Businessentity;
import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;
import com.edu.taller.ortiz.isabella.repository.interfaces.BusinessentityRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.VendorRepository;
import com.edu.taller.ortiz.isabella.service.implementations.VendorServiceImp;

@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
class VendorTest {

	@Mock
	private VendorRepository vr;
	@Mock
	private BusinessentityRepository br;
	
	@InjectMocks
	private VendorServiceImp vs;
	
	private Vendor v;
	
	
	@Test
	void addTest() {
		v = new Vendor();
		v.setCreditrating(0);
		v.setPurchasingwebserviceurl("https//:www.icesi.edu.co");
		v.setName("icesi");
		Businessentity b = new Businessentity();
		br.save(b);
		v.setBusinessentityid(b.getBusinessentityid());
		when(br.existsById(v.getBusinessentityid())).thenReturn(true);
		assertTrue(vs.add(v));
	}
	
	@Test
	void noAddByCreditTest() {
		v = new Vendor();
		v.setCreditrating(1);
		v.setPurchasingwebserviceurl("https//:www.icesi.edu.co");
		v.setName("icesi");
		Businessentity b = new Businessentity();
		br.save(b);
		v.setBusinessentityid(b.getBusinessentityid());
		assertFalse(vs.add(v));
	}
	
	@Test
	void noAddByURLTest() {
		v = new Vendor();
		v.setCreditrating(0);
		v.setPurchasingwebserviceurl("http//:www.icesi.edu.co");
		v.setName("icesi");
		Businessentity b = new Businessentity();
		br.save(b);
		v.setBusinessentityid(b.getBusinessentityid());
		assertFalse(vs.add(v));
	}
	
	@Test
	void noAddByNameTest() {
		v = new Vendor();
		v.setCreditrating(0);
		v.setPurchasingwebserviceurl("https//:www.icesi.edu.co");
		Businessentity b = new Businessentity();
		br.save(b);
		v.setBusinessentityid(b.getBusinessentityid());
		assertFalse(vs.add(v));
	}
	
	@Test
	void noAddByBusinessTest() {
		v = new Vendor();
		v.setCreditrating(0);
		v.setPurchasingwebserviceurl("https//:www.icesi.edu.co");
		v.setName("icesi");
		Businessentity b = new Businessentity();
		b.setBusinessentityid(1);
		v.setBusinessentityid(b.getBusinessentityid());
		when(br.existsById(v.getBusinessentityid())).thenReturn(false);
		assertFalse(vs.add(v));
	}
	
	
	
	
	void setUp() {
		v = new Vendor();
		v.setCreditrating(0);
		v.setPurchasingwebserviceurl("https//:www.icesi.edu.co");
		v.setName("icesi");
		Businessentity b = new Businessentity();
		br.save(b);
		v.setBusinessentityid(b.getBusinessentityid());
	}
	
	@Test
	void editTest() {
		setUp();
		v.setCreditrating(0);
		v.setPurchasingwebserviceurl("https//:www.icesi.edu.co");
		v.setName("icesi");
		Businessentity b = new Businessentity();
		br.save(b);
		v.setBusinessentityid(b.getBusinessentityid());
		when(br.existsById(v.getBusinessentityid())).thenReturn(true);
		when(vr.existsById(v.getBusinessentityid())).thenReturn(true);
		assertTrue(vs.edit(v));
	}
	
	@Test
	void noEditByCreditTest() {
		setUp();
		v.setCreditrating(1);
		v.setPurchasingwebserviceurl("https//:www.icesi.edu.co");
		v.setName("icesi");
		Businessentity b = new Businessentity();
		br.save(b);
		v.setBusinessentityid(b.getBusinessentityid());
		assertFalse(vs.edit(v));
	}
	
	@Test
	void noEditByURLTest() {
		setUp();
		v.setCreditrating(0);
		v.setPurchasingwebserviceurl("http//:www.icesi.edu.co");
		v.setName("icesi");
		Businessentity b = new Businessentity();
		br.save(b);
		v.setBusinessentityid(b.getBusinessentityid());
		assertFalse(vs.edit(v));
	}
	
	@Test
	void noEditByNameTest() {
		setUp();
		v.setCreditrating(0);
		v.setPurchasingwebserviceurl("https//:www.icesi.edu.co");
		Businessentity b = new Businessentity();
		br.save(b);
		v.setBusinessentityid(b.getBusinessentityid());
		assertFalse(vs.edit(v));
	}
	
	@Test
	void noEditByBusinessTest() {
		setUp();
		v.setCreditrating(0);
		v.setPurchasingwebserviceurl("https//:www.icesi.edu.co");
		v.setName("icesi");
		Businessentity b = new Businessentity();
		b.setBusinessentityid(1);
		v.setBusinessentityid(b.getBusinessentityid());
		when(br.existsById(v.getBusinessentityid())).thenReturn(false);
		assertFalse(vs.edit(v));
	}
	
	@Test
	void noEditTest() {
		v = new Vendor();
		v.setCreditrating(0);
		v.setPurchasingwebserviceurl("https//:www.icesi.edu.co");
		v.setName("icesi");
		Businessentity b = new Businessentity();
		b.setBusinessentityid(1);
		v.setBusinessentityid(b.getBusinessentityid());
		when(vr.existsById(v.getBusinessentityid())).thenReturn(false);
		assertFalse(vs.edit(v));
	}

}
