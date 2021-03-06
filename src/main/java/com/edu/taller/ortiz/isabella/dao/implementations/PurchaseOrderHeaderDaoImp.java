package com.edu.taller.ortiz.isabella.dao.implementations;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.interfaces.PurchaseOrderHeaderDao;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;

@Repository
@Scope("singleton") 
public class PurchaseOrderHeaderDaoImp implements PurchaseOrderHeaderDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public Purchaseorderheader save(Purchaseorderheader purchaseorderheader) {
		entityManager.persist(purchaseorderheader);
		return purchaseorderheader;
	}
	
	@Transactional
	@Override
	public Purchaseorderheader update(Purchaseorderheader  purchaseorderheader) {
		entityManager.merge(purchaseorderheader);
		return purchaseorderheader;
	}
	
	@Transactional
	@Override
	public void delete(Integer purchaseorderheaderId) {
		Purchaseorderheader poh = findById(purchaseorderheaderId);
		entityManager.remove(poh);
	}

	@Override
	public Purchaseorderheader findById(Integer purchaseorderheaderId) {
//		String jpql = "Select p from Purchaseorderheader p where p.purchaseorderid=:id";
		Query query = entityManager.createQuery("Select p from Purchaseorderheader p where p.purchaseorderid=:id");
		query.setParameter("id", purchaseorderheaderId);
		
		Purchaseorderheader purchaseorderheader = null;
		
		try {
			purchaseorderheader = (Purchaseorderheader) query.getSingleResult();
			
		} catch (NoResultException e) {
			
		}
		
		return purchaseorderheader;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchaseorderheader> findAll() {
		String query = "Select p from Purchaseorderheader p";
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchaseorderheader> findByShipmethodId(Integer shipmethodid) {
		String jpql = "Select p from Purchaseorderheader p where p.shipmethodid=:shipMethodID";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("shipMethodID", shipmethodid);
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchaseorderheader> findByVendorId(Integer vendorId) {
		String jpql = "Select p from Purchaseorderheader p where p.vendorid=:vendorID";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("vendorID", vendorId);
		
		return query.getResultList();
	}
	
	@Override
	public boolean existsById(Integer purchaseorderheaderId) {
		Purchaseorderheader poh = findById(purchaseorderheaderId);
		if (poh == null) {
			return false;
		}
		return true;
	}

}
