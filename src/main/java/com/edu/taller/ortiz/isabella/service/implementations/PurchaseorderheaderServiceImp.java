package com.edu.taller.ortiz.isabella.service.implementations;
import java.math.BigDecimal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.taller.ortiz.isabella.dao.interfaces.PurchaseOrderHeaderDao;
import com.edu.taller.ortiz.isabella.model.hr.Employee;
import com.edu.taller.ortiz.isabella.model.person.Person;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;
import com.edu.taller.ortiz.isabella.repository.interfaces.EmployeeRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.PersonRepository;
import com.edu.taller.ortiz.isabella.service.interfaces.PurchaseorderheaderService;

@Service
public class PurchaseorderheaderServiceImp implements PurchaseorderheaderService {
	private PurchaseOrderHeaderDao pohDAO;
	private EmployeeRepository er;
	private PersonRepository pr;
	
	@Autowired
	public PurchaseorderheaderServiceImp(EmployeeRepository er, PersonRepository pr,PurchaseOrderHeaderDao pohDAO) {
		this.pohDAO = pohDAO;
		this.er = er;
		this.pr = pr;
	}
	
	@Override
	public boolean add(Purchaseorderheader h) {
		if (h == null)
			return false;
		if (h.getSubtotal().compareTo(BigDecimal.ZERO) < 0)
			return false;
		if (!er.existsById(h.getEmployeeid()) ||
				!pr.existsById(h.getEmployeeid()))
			return false;
		
		Optional<Employee> e = er.findById(h.getEmployeeid());
		if (e.isEmpty()) 
			return false;
		Optional<Person> p = pr.findById(e.get().getBusinessentityid());
		if (e.isEmpty()) 
			return false;
		
		h.setEmployeeid(e.get().getBusinessentityid());
		
		pohDAO.save(h);
		return true;
	}

	@Override
	public boolean edit(Purchaseorderheader h) {
		if (h == null)
			return false;
		Purchaseorderheader poh = pohDAO.findById(h.getPurchaseorderid());
		Optional<Employee> employee = er.findById(h.getEmployeeid());
		Optional<Person> person = pr.findById(employee.get().getBusinessentityid());
				//findById(d.getPurchaseorderheader().getPurchaseorderid());

		if (h.getSubtotal().compareTo(BigDecimal.ZERO) < 0)
			return false;
		if (!er.existsById(h.getEmployeeid()) ||
				!pr.existsById(h.getEmployeeid()) ||
				!pohDAO.existsById(h.getPurchaseorderid()))
			return false;
		
		poh.setEmployeeid(employee.get().getBusinessentityid());
		poh.setFreight(h.getFreight());
		poh.setModifieddate(h.getModifieddate());
		poh.setOrderdate(h.getOrderdate());
		poh.setPurchaseorderdetails(h.getPurchaseorderdetails());
		poh.setPurchaseorderid(h.getPurchaseorderid());
		poh.setRevisionnumber(h.getRevisionnumber());
		poh.setShipdate(h.getShipdate());
		poh.setShipmethod(h.getShipmethod());
		poh.setStatus(h.getStatus());
		poh.setSubtotal(h.getSubtotal());
		poh.setTaxamt(h.getTaxamt());
		poh.setVendor(h.getVendor());
		
		pohDAO.update(poh);
		
		return true;
	}

	@Override
	public Iterable<Purchaseorderheader> findAll() {
		return pohDAO.findAll();
	}
	
	@Override
	public Purchaseorderheader findById(Integer id) {
		return pohDAO.findById(id);
	}

	@Override
	public void delete(Integer id) {
		pohDAO.delete(id);
	}

}
