package com.edu.taller.ortiz.isabella.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.interfaces.VendorDao;
import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;

@Repository
@Scope("singleton") 
public class VendorDaoImp implements VendorDao{
	
	@PersistenceContext
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
	public void delete(Vendor vendor) {
		entityManager.remove(vendor);
		
	}

	@Override
	public Vendor findById(Integer vendorId) {
		String jpql = "Select vendor from Vendor vendor where vendor.businessentityid=:id";
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
		String query = "Select vendor from Vendor vendor";
		return entityManager.createQuery(query).getResultList();
	}

	@Override
	public List<Vendor> findByCreditrating(Integer creditrating) {
		String jpql = "Select vendor from Vendor vendor where vendor.creditrating=:creditRating";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("creditRating", creditrating);
		
		return query.getResultList();
	}

	@Override
	public List<Vendor> findByName(String name) {
		String jpql = "Select vendor from Vendor vendor where vendor.name=:name";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("name", name);
		
		return query.getResultList();
	}

}
