package com.edu.taller.ortiz.isabella.service.implementations;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;
import com.edu.taller.ortiz.isabella.repository.interfaces.ShipmethodRespository;
import com.edu.taller.ortiz.isabella.service.interfaces.ShipmethodService;

@Service
public class ShipmethodServiceImp implements ShipmethodService {

	private ShipmethodRespository sr;
	
	@Autowired
	public ShipmethodServiceImp(ShipmethodRespository sr) {
		this.sr = sr;
	}
	
	@Override
	public boolean add(Shipmethod s) {
		if (s == null)
			return false;
		if (s.getShipbase().compareTo(BigDecimal.ZERO) < 0 ||
				s.getShiprate().compareTo(BigDecimal.ZERO) < 0 ||
				s.getName().length() < 4)
			return false;
		sr.save(s);
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
		if (!sr.existsById(s.getShipmethodid()))
			return false;
		sr.save(s);
		return true;
	}

	@Override
	public Iterable<Shipmethod> findAll() {
		return sr.findAll();
	}

	@Override
	public Optional<Shipmethod> findById(Integer id) {
		return sr.findById(id);
	}

}
