package com.edu.taller.ortiz.isabella.service.implementations;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.interfaces.ShipMethodDao;
import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;
import com.edu.taller.ortiz.isabella.service.interfaces.ShipmethodService;

@Service
public class ShipmethodServiceImp implements ShipmethodService {

	private ShipMethodDao smd;
	
	@Autowired
	public ShipmethodServiceImp(ShipMethodDao smd) {
		this.smd = smd;
	}
	
	@Override
	public boolean add(Shipmethod s) {
		if (s == null)
			return false;
		if (s.getShipbase().compareTo(BigDecimal.ZERO) < 0 ||
				s.getShiprate().compareTo(BigDecimal.ZERO) < 0 ||
				s.getName().length() < 4)
			return false;
		smd.save(s);
		return true;
	}

	@Override
	public boolean edit(Shipmethod s) {

		if (s == null)
			return false;
		if (s.getShipbase().compareTo(BigDecimal.ZERO) < 0 ||
				s.getShiprate().compareTo(BigDecimal.ZERO) < 0 ||
				s.getName().length() < 4)
			return false;
		if (!smd.existsById(s.getShipmethodid()))
			return false;
		
		Shipmethod shipmethod = smd.findById(s.getShipmethodid());
		
		shipmethod.setModifieddate(s.getModifieddate());
		shipmethod.setName(s.getName());
		shipmethod.setPurchaseorderheaders(s.getPurchaseorderheaders());
		shipmethod.setRowguid(s.getRowguid());
		shipmethod.setShipbase(s.getShipbase());
		shipmethod.setShipmethodid(s.getShipmethodid());
		shipmethod.setShiprate(s.getShipbase());
		
		smd.update(shipmethod);
		return true;
	}

	@Override
	public Iterable<Shipmethod> findAll() {
		return smd.findAll();
	}

	@Override
	public Shipmethod findById(Integer id) {
		return smd.findById(id);
	}

	@Override
	public void delete(Integer id) {
		smd.delete(id);
		
	}

}
