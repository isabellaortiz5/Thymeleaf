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

import com.edu.taller.ortiz.isabella.dao.interfaces.ShipMethodDao;
import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;

@Repository
@Scope("singleton") 
public class ShipMethodDaoImp implements ShipMethodDao{
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public Shipmethod save(Shipmethod shipmethod) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(shipmethod);
		entityManager.getTransaction().commit();
		entityManager.close();
		return shipmethod;
	}
	
	@Override
	public Shipmethod update(Shipmethod shipmethod) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(shipmethod);
		entityManager.getTransaction().commit();
		entityManager.close();
		return shipmethod;
	}
	
	@Transactional
	@Override
	public void delete(Shipmethod shipmethod) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(shipmethod);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}

	@Override
	public Shipmethod findById(Integer shipmethodId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select shipmethod from Shipmethod shipmethod where shipmethod.shipmethodid=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", shipmethodId);
		
		Shipmethod shipmethod = null;
		
		try {
			shipmethod = (Shipmethod) query.getSingleResult();
			
		} catch (NoResultException e) {
			
		}
		
		return shipmethod;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Shipmethod> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String query = "Select shipmethod from Shipmethod shipmethod";
		return entityManager.createQuery(query).getResultList();
	}

}
