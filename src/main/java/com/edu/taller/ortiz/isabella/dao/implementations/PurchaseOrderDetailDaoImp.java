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
	private EntityManager entityManager;
	
	@Override
	public Purchaseorderdetail save(Purchaseorderdetail purchaseorderdetail) {
		entityManager.persist(purchaseorderdetail);
		return purchaseorderdetail;
	}
	
	
	@Override
	public Purchaseorderdetail update(Purchaseorderdetail purchaseorderdetail) {
		entityManager.merge(purchaseorderdetail);
		return purchaseorderdetail;
	}
	
	@Transactional
	@Override
	public void delete(Integer purchaseorderdetailId) {
		entityManager.remove(purchaseorderdetailId);
	}

	@Override
	public Purchaseorderdetail findById(Integer purchaseorderdetailId) {
		String jpql = "Select p from Purchaseorderdetail p where p.shipmethodid=:id";
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
		String query = "Select p from Purchaseorderdetail p";
		return entityManager.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchaseorderdetail> findByUnitPrice(BigDecimal unitprice) {
		String jpql = "Select p from Purchaseorderdetail p where p.unitprice=:unitPrice";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("unitPrice", unitprice);	
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Purchaseorderdetail> findByProductId(Integer productid) {
		String jpql = "Select p from Purchaseorderdetail p where p.productid=:productID";
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
