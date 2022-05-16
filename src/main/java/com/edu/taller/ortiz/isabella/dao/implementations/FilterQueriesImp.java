package com.edu.taller.ortiz.isabella.dao.implementations;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.edu.taller.ortiz.isabella.dao.interfaces.FilterQueries;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;

@Repository
@Scope("singleton")
public class FilterQueriesImp implements FilterQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Purchaseorderheader> findPohByDate(LocalDate startdate, LocalDate enddate) {

		String jpql = "SELECT purchaseorderheader FROM Purchaseorderheader purchaseorderheader WHERE purchaseorderheader.orderdate >= :startdate AND purchaseorderheader.orderdate <= :enddate AND (Select count(pod) from Purchaseorderheader poh"
				+ " WHERE pod.purchaseorderheader.purchaseorderid = poh.purchaseorderid AND pod.duedate >= :startdate AND pod.duedate <= :enddate) > 1 ORDER BY purchaseorderheader.orderdate ASC";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("startdate", startdate);
		query.setParameter("enddate", enddate);
		return query.getResultList();
	}
	

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Purchaseorderheader> findPohByPod() {
		String jpql = "SELECT purchaseorderheader FROM Purchaseorderheader purchaseorderheader WHERE SIZE(purchaseorderheader.purchaseorderdetail) > 2";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}


}
