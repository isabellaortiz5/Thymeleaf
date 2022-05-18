package com.edu.taller.ortiz.isabella.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.taller.ortiz.isabella.dao.interfaces.VendorDao;
import com.edu.taller.ortiz.isabella.model.person.Businessentity;
import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;
import com.edu.taller.ortiz.isabella.repository.interfaces.BusinessentityRepository;
import com.edu.taller.ortiz.isabella.service.interfaces.VendorService;

@Service
public class VendorServiceImp implements VendorService {

	private VendorDao vd;
	private BusinessentityRepository br;
	
	@Autowired
	public VendorServiceImp(VendorDao vd, BusinessentityRepository br) {
		this.vd = vd;
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
		
		Optional<Businessentity> be = br.findById(v.getBusinessentityid());
		if (be.isEmpty()) 
			return false;
		
		v.setBusinessentityid(be.get().getBusinessentityid());
		
		vd.save(v);
		return true;
	}

	@Override
	public boolean edit(Vendor v) {
		Vendor vendor = vd.findById(v.getBusinessentityid());
		
		if (vendor == null) 
			return false;
		if (v.getCreditrating().intValue() < 0 ||
				!v.getPurchasingwebserviceurl().startsWith("https") ||
				v.getName() == null)
			return false;
		
		
		Optional<Businessentity> be = br.findById(v.getBusinessentityid());
		if (be.isEmpty()) 
			return false;
		
		
		vendor.setAccountnumber(v.getAccountnumber());
		vendor.setActiveflag(v.getActiveflag());
		vendor.setBusinessentityid(be.get().getBusinessentityid()); //be
		vendor.setCreditrating(v.getCreditrating());
		vendor.setModifieddate(v.getModifieddate());
		vendor.setName(v.getName());
		vendor.setPreferredvendorstatus(v.getPreferredvendorstatus());
		vendor.setProductvendors(v.getProductvendors());
		vendor.setPurchaseorderheaders(v.getPurchaseorderheaders());
		vendor.setPurchasingwebserviceurl(v.getPurchasingwebserviceurl());
		
		vd.update(vendor);
		
		return true;
	}


	@Override
	public Iterable<Vendor> findAll() {
		return vd.findAll();
	}

	@Override
	public Vendor findById(Integer id) {
		return vd.findById(id);
	}

	@Override
	public void delete(Integer id) {
		vd.delete(id);
	}

}
