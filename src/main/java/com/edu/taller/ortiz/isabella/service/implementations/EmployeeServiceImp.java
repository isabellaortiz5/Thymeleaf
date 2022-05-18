package com.edu.taller.ortiz.isabella.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.taller.ortiz.isabella.model.hr.Employee;
import com.edu.taller.ortiz.isabella.repository.interfaces.EmployeeRepository;
import com.edu.taller.ortiz.isabella.service.interfaces.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService{
	
	private EmployeeRepository er;
	
	@Autowired
	public EmployeeServiceImp(EmployeeRepository er) {
		this.er = er;
	}
	
	@Override
	public boolean add(Employee d) {
		er.save(d);
		return true;
	}

	@Override
	public Iterable<Employee> findAll() {
		return er.findAll();
	}

}
