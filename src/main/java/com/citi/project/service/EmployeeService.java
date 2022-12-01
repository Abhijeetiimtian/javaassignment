package com.citi.project.service;

import java.util.List;

import com.citi.project.model.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmployees();

	public void saveEmployee(Employee employee);

	public Employee getEmployeeById(long id);
	
	public Employee getEmployeeForLogin(String email, String password);

}
