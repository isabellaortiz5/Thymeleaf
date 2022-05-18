package com.edu.taller.ortiz.isabella.service.interfaces;

import com.edu.taller.ortiz.isabella.model.hr.Employee;

public interface EmployeeService {
	public boolean add(Employee d);
	public Iterable<Employee> findAll();
}
