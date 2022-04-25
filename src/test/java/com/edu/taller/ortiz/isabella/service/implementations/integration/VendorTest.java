package com.edu.taller.ortiz.isabella.service.implementations.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.edu.taller.ortiz.isabella.Application;
import com.edu.taller.ortiz.isabella.model.person.Businessentity;
import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;
import com.edu.taller.ortiz.isabella.repository.interfaces.BusinessentityRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.VendorRepository;
import com.edu.taller.ortiz.isabella.service.interfaces.VendorService;

@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
class VendorTest {

	private VendorRepository vr;
	private BusinessentityRepository br;
	
	private VendorService vs;
	
	private Vendor v;
	
	@Autowired
	public VendorTest(VendorService vs, VendorRepository vr, BusinessentityRepository br) {
		this.vr = vr;
		this.br = br;
		this.vs = vs;
	}
	
	@Test
	void addTest() {
		v = new Vendor();
		v.setCreditrating(0);
		v.setPurchasingwebserviceurl("https//:www.icesi.edu.co");
		v.setName("icesi");
		Businessentity b = new Businessentity();
		br.save(b);
		v.setBusinessentityid(b.getBusinessentityid());
		assertTrue(vs.add(v));
	}
	
	@Test
	void noAddByBusinessTest() {
		v = new Vendor();
		v.setCreditrating(0);
		v.setPurchasingwebserviceurl("https//:www.icesi.edu.co");
		v.setName("icesi");
		Businessentity b = new Businessentity();
		b.setBusinessentityid(1456);
		v.setBusinessentityid(b.getBusinessentityid());
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

		vr.save(v);
	}
	
	@Test
	void editTest() {
		setUp();
		v.setCreditrating(1);
		v.setPurchasingwebserviceurl("https//:www.google.co");
		v.setName("google");
		
		assertTrue(vs.edit(v));
	}
	

	


}
