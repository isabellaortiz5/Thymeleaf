package com.edu.taller.ortiz.isabella.dao.implementations;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.interfaces.PurchaseOrderDetailDao;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderdetail;

@Repository
@Scope("singleton") 
public class PurchaseOrderDetailDaoImp implements PurchaseOrderDetailDao{
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public Purchaseorderdetail save(Purchaseorderdetail purchaseorderdetail) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(purchaseorderdetail);
		entityManager.getTransaction().commit();
		entityManager.close();
		return purchaseorderdetail;
	}
	
	
	@Override
	public Purchaseorderdetail update(Purchaseorderdetail purchaseorderdetail) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(purchaseorderdetail);
		entityManager.getTransaction().commit();
		entityManager.close();
		return purchaseorderdetail;
	}
	
	@Transactional
	@Override
	public void delete(Integer purchaseorderdetailId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Purchaseorderdetail purchaseorderdetail = entityManager.find(Purchaseorderdetail.class, purchaseorderdetailId);
		entityManager.getTransaction().begin();
		entityManager.remove(purchaseorderdetail);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public Purchaseorderdetail findById(Integer purchaseorderdetailId) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select purchaseorderdetail from Purchaseorderdetail purchaseorderdetail where purchaseorderdetail.shipmethodid=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", purchaseorderdetailId);
		
		Purchaseorderdetail purchaseorderdetail = null;
		
		try {
			purchaseorderdetail = (Purchaseorderdetail) query.getSingleResult();
			
		} catch (NoResultException e) {
			
		}
		
		return purchaseorderdetail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchaseorderdetail> findAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String query = "Select purchaseorderdetail from Purchaseorderdetail purchaseorderdetail";
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchaseorderdetail> findByUnitPrice(BigDecimal unitprice) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select purchaseorderdetail from Purchaseorderdetail purchaseorderdetail where purchaseorderdetail.unitprice=:unitPrice";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("unitPrice", unitprice);	
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchaseorderdetail> findByProductId(Integer productid) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "Select purchaseorderdetail from Purchaseorderdetail purchaseorderdetail where purchaseorderdetail.productid=:productID";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("productID", productid);	
		
		return query.getResultList();
	}


	@Override
	public boolean existsById(Integer purchaseorderdetailId) {
		Purchaseorderdetail pod = findById(purchaseorderdetailId);
		if (pod == null) {
			return false;
		}
		return true;
	}

}
