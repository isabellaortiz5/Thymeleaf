package com.edu.taller.ortiz.isabella.service.implementations.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.edu.taller.ortiz.isabella.Application;
import com.edu.taller.ortiz.isabella.model.hr.Employee;
import com.edu.taller.ortiz.isabella.model.person.Businessentity;
import com.edu.taller.ortiz.isabella.model.person.Person;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;
import com.edu.taller.ortiz.isabella.repository.interfaces.EmployeeRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.PersonRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.PurchaseorderheaderRepository;
import com.edu.taller.ortiz.isabella.service.interfaces.PurchaseorderheaderService;
@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
class PurchaseorderheaderTest {
	
	private PurchaseorderheaderRepository hr;
	private EmployeeRepository er;
	private PersonRepository pr;
	
	private PurchaseorderheaderService hs;
	
	private Purchaseorderheader h;
	
	@Autowired
	public PurchaseorderheaderTest(PurchaseorderheaderService hs, EmployeeRepository er, PersonRepository pr,PurchaseorderheaderRepository hr) {
		this.hr = hr;
		this.hs = hs;
		this.er = er;
		this.pr = pr;
	}
	
	void setUp() {
		h = new Purchaseorderheader();
		h.setSubtotal(new BigDecimal(0));
		Businessentity b = new Businessentity();
		b.setBusinessentityid(1);
		Person p = new Person();
		p.setBusinessentityid(b.getBusinessentityid());
		Employee e = new Employee();
		e.setBusinessentityid(b.getBusinessentityid());
		h.setEmployeeid(e.getBusinessentityid());
		pr.save(p);
		er.save(e);
	}
	
	
	
	@Test
	void addTest() {
		setUp();
		assertTrue(hs.add(h));
	}


	@Test
	void noAddByEmployeeTest() {
		setUp();
		Employee e = new Employee();
		e.setBusinessentityid(231);
		h.setEmployeeid(e.getBusinessentityid());
		assertFalse(hs.add(h));
	}
	
	@Test
	void noAddByPersonTest() {
		setUp();
		Person p = new Person();
		p.setBusinessentityid(123);
		h.setEmployeeid(p.getBusinessentityid());
		assertFalse(hs.add(h));
	}
	
	@Test
	void editTest() {
		setUp();
		hr.save(h);
		h.setSubtotal(new BigDecimal(0.1));
		assertTrue(hs.edit(h));
	}


	
	@Test
	void noEditByEmployeeTest() {
		setUp();
		Employee e = new Employee();
		e.setBusinessentityid(231);
		h.setEmployeeid(e.getBusinessentityid());
		assertFalse(hs.edit(h));
	}
	
	@Test
	void noEditByPersonTest() {
		setUp();
		Person p = new Person();
		p.setBusinessentityid(123);
		h.setEmployeeid(p.getBusinessentityid());
		assertFalse(hs.edit(h));
	}
	
	@Test
	void noEditTest() {
		setUp();
		h.setPurchaseorderid(231165);
		assertFalse(hs.edit(h));
	}
	
}
