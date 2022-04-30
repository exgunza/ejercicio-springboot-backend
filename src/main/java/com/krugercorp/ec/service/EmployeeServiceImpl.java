package com.krugercorp.ec.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.krugercorp.ec.model.Employee;
import com.krugercorp.ec.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	@Transactional
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public boolean existsByDni(String dni) {
		return employeeRepository.existsByDni(dni);
	}

	@Override
	public Optional<Employee> findById(Integer id) {
		return employeeRepository.findById(id);
	}

	@Override
	@Transactional
	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}
	
	@Override
	public Page<Employee> findAll(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}
	
	@Override
	public List<Employee> getEmployeByStateVaccine(String state) {
		return employeeRepository.findByStateVaccine(state);
	}

	@Override
	public List<Employee> getEmployeByTypeVaccine(String type) {
		return employeeRepository.findByTypeVaccine(type);
	}

	@Override
	public List<Employee> getEmployesByRangeVaccinationDate(LocalDate from, LocalDate to) {
		return employeeRepository.findByVaccinationDateBetween(from, to);
	}
}
