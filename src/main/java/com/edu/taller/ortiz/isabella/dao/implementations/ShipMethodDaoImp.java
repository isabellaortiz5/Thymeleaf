package com.edu.taller.ortiz.isabella.dao.implementations;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import com.edu.taller.ortiz.isabella.dao.interfaces.ShipMethodDao;
import com.edu.taller.ortiz.isabella.model.prchasing.Shipmethod;

@Repository
@Scope("singleton") 
public class ShipMethodDaoImp implements ShipMethodDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@Override
	public Shipmethod save(Shipmethod shipmethod) {
		entityManager.persist(shipmethod);
		return shipmethod;
	}
	
	@Transactional
	@Override
	public Shipmethod update(Shipmethod shipmethod) {
		entityManager.merge(shipmethod);
		return shipmethod;
	}
	
	@Transactional
	@Override
	public void delete(Integer shipmethodId) {
		Shipmethod s = findById(shipmethodId);
		entityManager.remove(s);
	}

	@Override
	public Shipmethod findById(Integer shipmethodId) {
		String jpql = "Select s from Shipmethod s where s.shipmethodid=:id";
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
		String query = "Select s from Shipmethod s";
		return entityManager.createQuery(query).getResultList();
	}
	
	@Override
	public boolean existsById(Integer shipmethodId) {
		Shipmethod sm = findById(shipmethodId);
		if (sm == null) {
			return false;
		}
		return true;
	}
	
}
