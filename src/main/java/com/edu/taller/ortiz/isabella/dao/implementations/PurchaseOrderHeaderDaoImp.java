package com.edu.taller.ortiz.isabella.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.interfaces.PurchaseOrderHeaderDao;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;

@Repository
@Scope("singleton") 
public class PurchaseOrderHeaderDaoImp implements PurchaseOrderHeaderDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Purchaseorderheader save(Purchaseorderheader purchaseorderheader) {
		entityManager.persist(purchaseorderheader);
		return purchaseorderheader;
	}
	
	@Override
	public Purchaseorderheader update(Purchaseorderheader  purchaseorderheader) {
		entityManager.merge(purchaseorderheader);
		return purchaseorderheader;
	}
	
	@Transactional
	@Override
	public void delete(Purchaseorderheader purchaseorderheader) {
		entityManager.remove(purchaseorderheader);
		
	}

	@Override
	public Purchaseorderheader findById(Integer purchaseorderheaderId) {
		String jpql = "Select purchaseorderheader from Purchaseorderheader purchaseorderheader where purchaseorderheader.purchaseorderid=:id";
		Query query = entityManager.createQuery(jpql);
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
		String query = "Select purchaseorderheader from Purchaseorderheader purchaseorderheader";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<Purchaseorderheader> findByShipmethodId(Integer shipmethodId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Purchaseorderheader> findByVendorId(Integer vendorId) {
		// TODO Auto-generated method stub
		return null;
	}

}
