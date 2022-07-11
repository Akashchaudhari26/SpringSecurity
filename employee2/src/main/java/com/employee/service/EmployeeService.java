package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.model.Employee;

@Service
public class EmployeeService {

	public List<Employee> getAllEmployee() {

		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1,"Akash"));
		empList.add(new Employee(2,"Ashish"));
		empList.add(new Employee(3,"Sakshi"));
		return empList;
	}

	public Employee getEmployee(int id) {
		// TODO Auto-generated method stub
		return new Employee(1,"Akash");
	}

}
