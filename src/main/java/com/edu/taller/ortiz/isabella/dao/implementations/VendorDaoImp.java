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

import com.edu.taller.ortiz.isabella.dao.interfaces.VendorDao;
import com.edu.taller.ortiz.isabella.model.prchasing.Vendor;

@Repository
@Scope("singleton") 
public class VendorDaoImp implements VendorDao{
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public Vendor save(Vendor vendor) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(vendor);
		entityManager.getTransaction().commit();
		entityManager.close();
		return vendor;
	}
	
	@Override
	public Vendor update(Vendor vendor) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(vendor);
		entityManager.getTransaction().commit();
		entityManager.close();
		return vendor;
	}
	
	@Transactional
	@Override
	public void delete(Vendor vendor) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(vendor);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public Vendor findById(Integer vendorId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
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
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String query = "Select vendor from Vendor vendor";
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> findByCreditrating(Integer creditrating) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select vendor from Vendor vendor where vendor.creditrating=:creditRating";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("creditRating", creditrating);
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vendor> findByName(String name) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select vendor from Vendor vendor where vendor.name=:name";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("name", name);
		
		return query.getResultList();
	}

}
