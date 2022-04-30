package com.krugercorp.ec.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krugercorp.ec.dto.EmployeeAdmDTO;
import com.krugercorp.ec.model.Employee;
import com.krugercorp.ec.model.Roles;
import com.krugercorp.ec.model.Users;
import com.krugercorp.ec.service.EmployeeServiceImpl;
import com.krugercorp.ec.service.UsersServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin/employees")
@Slf4j
public class AdminController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@Autowired
	private UsersServiceImpl usersServiceImpl;

	@PostMapping
	public ResponseEntity<Object> saveEmployee(@RequestBody @Valid EmployeeAdmDTO employeeAdmDTO) {
		if (employeeService.existsByDni(employeeAdmDTO.getDni())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Dni is alredy in use!");
		}
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeAdmDTO, employee);
		// Create user and pass
		String ref = employee.getName().toLowerCase().substring(0, 1).concat(employee.getSurname().toLowerCase());
		Users usr = usersServiceImpl.save(new Users(ref, ref, Arrays.asList(new Roles("USER"))));
		employee.setUsers(usr);
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employee));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneEmployee(@PathVariable(value = "id") Integer id) {
		Optional<Employee> employeeOptional = employeeService.findById(id);
		if (!employeeOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employe not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(employeeOptional.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") Integer id) {
		Optional<Employee> employeeOptional = employeeService.findById(id);
		if (!employeeOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employe not found");
		}
		employeeService.delete(employeeOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateEmployee(@PathVariable(value = "id") Integer id,
			@RequestBody @Valid EmployeeAdmDTO employeeAdmDTO) {

		Optional<Employee> employeeOptional = employeeService.findById(id);
		if (!employeeOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employe not found");
		}

		Employee employee = employeeOptional.get();
		BeanUtils.copyProperties(employeeAdmDTO, employee);

		return ResponseEntity.status(HttpStatus.OK).body(employeeService.save(employee));
	}

	@GetMapping
	public ResponseEntity<Page<Employee>> getAllEmployees(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.findAll(pageable));
	}

	@Operation(summary = "Get employees based on vaccination status")
	@GetMapping("/byStateVaccine/{state}")
	public List<Employee> employesByStateVaccine(@PathVariable String state) {
		log.info("Start employesByStateVaccine");
		return employeeService.getEmployeByStateVaccine(state);
	}

	@Operation(summary = "Get employees based on type vaccine")
	@GetMapping("/byTypeVaccine/{type}")
	public List<Employee> employesByTypeVaccine(@PathVariable String type) {
		log.info("Start employesByTypeVaccine");
		return employeeService.getEmployeByTypeVaccine(type);
	}

	@Operation(summary = "Get employees based on a range of vaccination date")
	@GetMapping("/byRangeDate")
	public List<Employee> employesByRangeVaccinationDate(
			@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
			@RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate to) {
		log.info("Start employesByRangeVaccinationDate");
		return employeeService.getEmployesByRangeVaccinationDate(from, to);
	}

}
