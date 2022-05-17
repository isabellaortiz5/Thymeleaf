package com.edu.taller.ortiz.isabella.dao.implementations;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.interfaces.PurchaseOrderHeaderDao;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;

@Repository
@Scope("singleton") 
public class PurchaseOrderHeaderDaoImp implements PurchaseOrderHeaderDao{
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public Purchaseorderheader save(Purchaseorderheader purchaseorderheader) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(purchaseorderheader);
		entityManager.getTransaction().commit();
		entityManager.close();
		return purchaseorderheader;
	}
	
	@Override
	public Purchaseorderheader update(Purchaseorderheader  purchaseorderheader) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(purchaseorderheader);
		entityManager.getTransaction().commit();
		entityManager.close();
		return purchaseorderheader;
	}
	
	@Transactional
	@Override
	public void delete(Purchaseorderheader purchaseorderheader) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(purchaseorderheader);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public Purchaseorderheader findById(Integer purchaseorderheaderId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
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
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String query = "Select purchaseorderheader from Purchaseorderheader purchaseorderheader";
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchaseorderheader> findByShipmethodId(Integer shipmethodid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select purchaseorderheader from Purchaseorderheader purchaseorderheader where purchaseorderheader.shipmethodid=:shipMethodID";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("shipMethodID", shipmethodid);
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchaseorderheader> findByVendorId(Integer vendorId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select purchaseorderheader from Purchaseorderheader purchaseorderheader where purchaseorderheader.vendorid=:vendorID";
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
