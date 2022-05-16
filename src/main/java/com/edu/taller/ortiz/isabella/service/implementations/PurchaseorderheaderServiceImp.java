package com.edu.taller.ortiz.isabella.service.implementations;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;
import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;
import com.edu.taller.ortiz.isabella.repository.interfaces.EmployeeRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.PersonRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.PurchaseorderheaderRepository;
import com.edu.taller.ortiz.isabella.service.interfaces.PurchaseorderheaderService;

@Service
public class PurchaseorderheaderServiceImp implements PurchaseorderheaderService {

	private PurchaseorderheaderRepository hr;
	private EmployeeRepository er;
	private PersonRepository pr;
	
	@Autowired
	public PurchaseorderheaderServiceImp(EmployeeRepository er, PersonRepository pr,PurchaseorderheaderRepository hr) {
		this.hr = hr;
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
		hr.save(h);
		return true;
	}

	@Override
	public boolean edit(Purchaseorderheader h) {
		if (h == null)
			return false;
		Optional<Purchaseorderheader> realPoHead = hr.findById(h.getPurchaseorderid());
		Purchaseorderheader poHead = realPoHead.get();
		if (h.getSubtotal().compareTo(BigDecimal.ZERO) < 0)
			return false;
		if (!er.existsById(h.getEmployeeid()) ||
				!pr.existsById(h.getEmployeeid()) ||
				!hr.existsById(h.getPurchaseorderid()))
			return false;
		hr.deleteById(poHead.getPurchaseorderid());
		hr.save(h);
		return true;
	}

	@Override
	public Iterable<Purchaseorderheader> findAll() {
		return hr.findAll();
	}
	
	@Override
	public Optional<Purchaseorderheader> findById(Integer id) {

		return hr.findById(id);
	}

	@Override
	public void delete(Integer id) {
		hr.deleteById(id);
		
	}

}
