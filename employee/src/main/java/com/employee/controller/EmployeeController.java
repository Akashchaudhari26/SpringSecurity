package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hi USER";
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee> empList = empService.getAllEmployee();
		return new ResponseEntity<List<Employee>>(empList,HttpStatus.OK);
		
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id){
		Employee emp = empService.getEmployee(id);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		
	}
}
