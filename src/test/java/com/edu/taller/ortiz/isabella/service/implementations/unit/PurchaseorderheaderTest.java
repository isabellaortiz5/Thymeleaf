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
import com.edu.taller.ortiz.isabella.model.hr.Employee;
import com.edu.taller.ortiz.isabella.model.person.Businessentity;
import com.edu.taller.ortiz.isabella.model.person.Person;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;
import com.edu.taller.ortiz.isabella.repository.interfaces.EmployeeRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.PersonRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.PurchaseorderheaderRepository;
import com.edu.taller.ortiz.isabella.service.implementations.PurchaseorderheaderServiceImp;
@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
class PurchaseorderheaderTest {
	
	@Mock
	private PurchaseorderheaderRepository hr;
	@Mock
	private EmployeeRepository er;
	@Mock
	private PersonRepository pr;
	
	@InjectMocks
	private PurchaseorderheaderServiceImp hs;
	
	private Purchaseorderheader h;
	
	@SuppressWarnings("deprecation")
	void setUp() {
		h = new Purchaseorderheader();
//		int year = Calendar.getInstance().getTime().getYear();
//		int month = Calendar.getInstance().getTime().getMonth();
//		int day = Calendar.getInstance().getTime().getDay();
//		h.setOrderdate(new Timestamp(year, month, day, 0, 0, 0, 0));
		h.setSubtotal(new BigDecimal(0));
		Businessentity b = new Businessentity();
		b.setBusinessentityid(1);
		Person p = new Person();
		p.setBusinessentityid(b.getBusinessentityid());
		Employee e = new Employee();
		e.setBusinessentityid(b.getBusinessentityid());
		pr.save(p);
		er.save(e);
	}
	
	
	
	@Test
	void addTest() {
		setUp();
		when(er.existsById(h.getEmployeeid())).thenReturn(true);
		when(pr.existsById(h.getEmployeeid())).thenReturn(true);
		assertTrue(hs.add(h));
	}

	@Test
	void noAddByDateTest() {
		setUp();
		//h.setOrderdate(new Timestamp(0));
		assertFalse(hs.add(h));
	}
	
	@Test
	void noAddBySubtotalTest() {
		setUp();
		h.setSubtotal(new BigDecimal(-0.1));
		assertFalse(hs.add(h));
	}
	
	@Test
	void noAddByEmployeeTest() {
		setUp();
		Employee e = new Employee();
		e.setBusinessentityid(231);
		when(er.existsById(h.getEmployeeid())).thenReturn(false);
		assertFalse(hs.add(h));
	}
	
	@Test
	void noAddByPersonTest() {
		setUp();
		Person p = new Person();
		p.setBusinessentityid(123);
		when(pr.existsById(h.getEmployeeid())).thenReturn(false);
		assertFalse(hs.add(h));
	}
	
	@Test
	void editTest() {
		setUp();
		hr.save(h);
		h.setSubtotal(new BigDecimal(0.1));
		when(er.existsById(h.getEmployeeid())).thenReturn(true);
		when(pr.existsById(h.getEmployeeid())).thenReturn(true);
		when(hr.existsById(h.getPurchaseorderid())).thenReturn(true);
		assertTrue(hs.edit(h));
	}

	@Test
	void noEditByDateTest() {
		setUp();
		//h.setOrderdate(new Timestamp(0));
		assertFalse(hs.edit(h));
	}
	
	@Test
	void noEditBySubtotalTest() {
		setUp();
		h.setSubtotal(new BigDecimal(-0.1));
		assertFalse(hs.edit(h));
	}
	
	@Test
	void noEditByEmployeeTest() {
		setUp();
		Employee e = new Employee();
		e.setBusinessentityid(231);
		when(er.existsById(h.getEmployeeid())).thenReturn(false);
		assertFalse(hs.edit(h));
	}
	
	@Test
	void noEditByPersonTest() {
		setUp();
		Person p = new Person();
		p.setBusinessentityid(123);
		when(pr.existsById(h.getEmployeeid())).thenReturn(false);
		assertFalse(hs.edit(h));
	}
	
	@Test
	void noEditTest() {
		setUp();
		when(hr.existsById(h.getPurchaseorderid())).thenReturn(false);
		assertFalse(hs.edit(h));
	}
	
}
