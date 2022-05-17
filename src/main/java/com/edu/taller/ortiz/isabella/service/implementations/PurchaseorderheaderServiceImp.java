package com.edu.taller.ortiz.isabella.service.implementations;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import com.edu.taller.ortiz.isabella.dao.interfaces.PurchaseOrderHeaderDao;
import com.edu.taller.ortiz.isabella.model.hr.Employee;
import com.edu.taller.ortiz.isabella.model.person.Person;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;
import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;
import com.edu.taller.ortiz.isabella.repository.interfaces.EmployeeRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.PersonRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.PurchaseorderheaderRepository;
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
		Purchaseorderheader poh = pohDAO.findById(id);

		pohDAO.delete(poh);
		
	}

}
