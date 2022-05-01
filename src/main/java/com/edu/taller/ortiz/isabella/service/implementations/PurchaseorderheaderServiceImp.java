package com.edu.taller.ortiz.isabella.service.implementations;
import java.math.BigDecimal;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;
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
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean add(Purchaseorderheader h) {
		if (h == null)
			return false;
		int year = Calendar.getInstance().getTime().getYear();
		int month = Calendar.getInstance().getTime().getMonth();
		int day = Calendar.getInstance().getTime().getDay();
		int y = h.getOrderdate().getYear();
		int m = h.getOrderdate().getMonth()+1; //A FECHA DE 27/03/2022 SIRVE ASI
		int d = h.getOrderdate().getDay()-1;
		if (y != year ||
				m != month ||
				d != day ||
				h.getSubtotal().compareTo(BigDecimal.ZERO) < 0)
			return false;
		if (!er.existsById(h.getEmployeeid()) ||
				!pr.existsById(h.getEmployeeid()))
			return false;
		hr.save(h);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean edit(Purchaseorderheader h) {
		if (h == null)
			return false;
		int year = Calendar.getInstance().getTime().getYear();
		int month = Calendar.getInstance().getTime().getMonth();
		int day = Calendar.getInstance().getTime().getDay();
		int y = h.getOrderdate().getYear();
		int m = h.getOrderdate().getMonth()+1; //A FECHA DE 27/03/2022 SIRVE ASI
		int d = h.getOrderdate().getDay()-1;
		if (y != year ||
				m != month ||
				d != day ||
				h.getSubtotal().compareTo(BigDecimal.ZERO) < 0)
			return false;
		if (!er.existsById(h.getEmployeeid()) ||
				!pr.existsById(h.getEmployeeid()) ||
				!hr.existsById(h.getPurchaseorderid()))
			return false;
		hr.save(h);
		return true;
	}

	@Override
	public Iterable<Purchaseorderheader> findAll() {
		return hr.findAll();
	}

}
