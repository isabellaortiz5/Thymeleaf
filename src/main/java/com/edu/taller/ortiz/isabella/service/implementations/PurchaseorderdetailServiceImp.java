package com.edu.taller.ortiz.isabella.service.implementations;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.taller.ortiz.isabella.dao.interfaces.PurchaseOrderDetailDao;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;
import com.edu.taller.ortiz.isabella.repository.interfaces.PurchaseorderheaderRepository;
import com.edu.taller.ortiz.isabella.service.interfaces.PurchaseorderdetailService;
@Service
public class PurchaseorderdetailServiceImp implements PurchaseorderdetailService {

//	private PurchaseorderdetailRepository dr;
	private PurchaseorderheaderRepository hr;
	
	private PurchaseOrderDetailDao podDAO;

	@Autowired
	public PurchaseorderdetailServiceImp(PurchaseOrderDetailDao podDAO, PurchaseorderheaderRepository hr) {
		this.hr = hr;
		this.podDAO = podDAO;
	}
	
	@Override
	public boolean add(Purchaseorderdetail d) {
		if (d == null)
			return false;
		Optional<Purchaseorderheader> purchaseorderheader = hr.findById(d.getPurchaseorderheader().getPurchaseorderid());
		if (d.getOrderqty().compareTo(0) < 0 ||
				d.getUnitprice().compareTo(BigDecimal.ZERO) < 0)
			return false;
		if (!hr.existsById(d.getPurchaseorderheader().getPurchaseorderid()))
			return false;
		if (purchaseorderheader.isEmpty()) {
			return false;
		}
		d.setPurchaseorderheader(purchaseorderheader.get());
		podDAO.save(d);
		return true;
	}

	@Override
	public boolean edit(Purchaseorderdetail d) {
		if (d == null)
			return false;
		Purchaseorderdetail pod = podDAO.findById(d.getId());
		Optional<Purchaseorderheader> realPurchaseO = hr.findById(d.getPurchaseorderheader().getPurchaseorderid());
		
		if (d.getOrderqty().compareTo(0) < 0 ||
				d.getUnitprice().compareTo(BigDecimal.ZERO) < 0)
			return false;
		if (!hr.existsById(d.getPurchaseorderheader().getPurchaseorderid()) ||
				!podDAO.existsById(d.getId()))
			return false;
		if (realPurchaseO.isEmpty()) {
			return false;
		}
		
		pod.setPurchaseorderheader(realPurchaseO.get());
		pod.setDuedate(d.getDuedate());
		pod.setId(d.getId());
		pod.setModifieddate(d.getModifieddate());
		pod.setOrderqty(d.getOrderqty());
		pod.setProductid(d.getProductid());
		pod.setReceivedqty(d.getReceivedqty());
		pod.setRejectedqty(d.getRejectedqty());
		pod.setUnitprice(d.getUnitprice());
		
		podDAO.update(pod);
		
		return true;
	}

	@Override
	public Iterable<Purchaseorderdetail> findAll() {
		return podDAO.findAll();
	}

	@Override
	public void delete(Integer id) {
		Purchaseorderdetail pod = podDAO.findById(id);
		podDAO.delete(pod);
		
	}

	@Override
	public Purchaseorderdetail findById(Integer id) {
		return podDAO.findById(id);
	}
	
	

			
}
