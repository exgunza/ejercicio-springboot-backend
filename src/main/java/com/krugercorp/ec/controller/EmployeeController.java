package com.krugercorp.ec.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krugercorp.ec.dto.EmployeeUsrDTO;
import com.krugercorp.ec.model.Employee;
import com.krugercorp.ec.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateEmployeeByAdm(@PathVariable(value = "id") Integer id,
			@RequestBody @Valid EmployeeUsrDTO employeeUsrDTO) {
		
		Optional<Employee> employeeOptional = employeeService.findById(id);
		if (!employeeOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employe not found");
		}

		Employee employee = employeeOptional.get();
		BeanUtils.copyProperties(employeeUsrDTO, employee);

		return ResponseEntity.status(HttpStatus.OK).body(employeeService.save(employee));
	}
}
