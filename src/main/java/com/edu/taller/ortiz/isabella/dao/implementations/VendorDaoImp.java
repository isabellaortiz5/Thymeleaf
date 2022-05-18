package com.edu.taller.ortiz.isabella.dao.implementations;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.interfaces.VendorDao;
import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;

@Repository
@Scope("singleton") 
public class VendorDaoImp implements VendorDao{
	
	@PersistenceUnit
	private EntityManager entityManager;
	
	@Override
	public Vendor save(Vendor vendor) {
		entityManager.persist(vendor);
		return vendor;
	}
	
	@Override
	public Vendor update(Vendor vendor) {
		entityManager.merge(vendor);
		return vendor;
	}
	
	@Transactional
	@Override
	public void delete(Integer vendorId) {
		entityManager.remove(vendorId);
	}

	@Override
	public Vendor findById(Integer vendorId) {
		String jpql = "Select v from Vendor v where v.businessentityid=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", vendorId);
		
		Vendor vendor = null;
		
		try {
			vendor = (Vendor) query.getSingleResult();
			
		} catch (NoResultException e) {
			
		}
		
		return vendor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> findAll() {
		String query = "Select v from Vendor v";
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> findByCreditrating(Integer creditrating) {
		String jpql = "Select v from Vendor v where v.creditrating=:creditRating";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("creditRating", creditrating);
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> findByName(String name) {
		String jpql = "Select v from Vendor v where v.name=:name";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("name", name);
		
		return query.getResultList();
	}

}
