package com.edu.taller.ortiz.isabella.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;
import com.edu.taller.ortiz.isabella.repository.interfaces.BusinessentityRepository;
import com.edu.taller.ortiz.isabella.repository.interfaces.VendorRepository;
import com.edu.taller.ortiz.isabella.service.interfaces.VendorService;

@Service
public class VendorServiceImp implements VendorService {

	private VendorRepository vr;
	
	private BusinessentityRepository br;
	
	@Autowired
	public VendorServiceImp(VendorRepository vr, BusinessentityRepository br) {
		this.vr = vr;
		this.br = br;
	}
	
	@Override
	public boolean add(Vendor v) {
		if (v == null) 
			return false;
		if (v.getCreditrating().intValue() < 0 ||
				!v.getPurchasingwebserviceurl().startsWith("https") ||
				v.getName() == null)
			return false;
		if (!br.existsById(v.getBusinessentityid()))
			return false;
		vr.save(v);
		return true;
	}

	@Override
	public boolean edit(Vendor v) {
		if (v == null) 
			return false;
		if (v.getCreditrating().intValue() < 0 ||
				!v.getPurchasingwebserviceurl().startsWith("https") ||
				v.getName() == null)
			return false;
		if (!br.existsById(v.getBusinessentityid()))
			return false;
		vr.save(v);
		return true;
	}

	@Override
	public Iterable<Vendor> findAll() {
		return vr.findAll();
	}

}
