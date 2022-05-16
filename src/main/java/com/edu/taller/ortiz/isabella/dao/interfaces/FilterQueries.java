package com.edu.taller.ortiz.isabella.dao.interfaces;

import java.time.LocalDate;
import java.util.List;

import com.edu.taller.ortiz.isabella.model.prchasing.Purchaseorderheader;

public interface FilterQueries {
	
	public List<Purchaseorderheader> findPohByDate(LocalDate startdate, LocalDate enddate);
	public List<Purchaseorderheader> findPohByPod();

}
