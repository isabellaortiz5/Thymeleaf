package com.edu.taller.ortiz.isabella.dao.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.dao.interfaces.FilterQueries;
import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;

@Repository
@Scope("singleton")
public class FilterQueriesImp implements FilterQueries {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList findPohByDate(LocalDate startdate, LocalDate enddate) {

		String jpql = "SELECT p FROM Purchaseorderheader p WHERE p.orderdate >= :startdate AND p.orderdate <= :enddate AND (Select count(pod) from Purchaseorderheader poh"
				+ " WHERE pod.p.purchaseorderid = poh.purchaseorderid AND pod.duedate >= :startdate AND pod.duedate <= :enddate) > 1 ORDER BY p.orderdate ASC";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("startdate", startdate);
		query.setParameter("enddate", enddate);
		List<Purchaseorderheader> lista1= (List<Purchaseorderheader>) query.getResultList().get(0);
		Integer suma = (Integer) query.getResultList().get(1);
		
		ArrayList result = new ArrayList();
		result.add(lista1);
		result.add(suma);
		return result;
		

	}
	

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Purchaseorderheader> findPohByPod() {
		String jpql = "SELECT p FROM Purchaseorderheader p WHERE SIZE(p.purchaseorderdetail) > 2";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}


}
