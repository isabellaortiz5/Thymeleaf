package com.edu.taller.ortiz.isabella.dao.implementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.interfaces.ShipMethodDao;
import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;

@Repository
@Scope("singleton") 
public class ShipMethodDaoImp implements ShipMethodDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Shipmethod save(Shipmethod shipmethod) {
		entityManager.persist(shipmethod);
		return shipmethod;
	}
	
	@Override
	public Shipmethod update(Shipmethod shipmethod) {
		entityManager.merge(shipmethod);
		return shipmethod;
	}
	
	@Transactional
	@Override
	public void delete(Shipmethod shipmethod) {
		entityManager.remove(shipmethod);
		
	}

	@Override
	public Shipmethod findById(Integer shipmethodId) {
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
		String query = "Select shipmethod from Shipmethod shipmethod";
		return entityManager.createQuery(query).getResultList();
	}

}