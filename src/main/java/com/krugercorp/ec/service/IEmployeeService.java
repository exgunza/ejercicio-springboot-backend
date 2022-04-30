package com.krugercorp.ec.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.krugercorp.ec.model.Employee;

public interface IEmployeeService {

	public Employee save(Employee employee);
	
	public boolean existsByDni(String dni);
	
	public Optional<Employee> findById(Integer id);
	
	public void delete(Employee employee);
	
    public Page<Employee> findAll(Pageable pageable);

    public List<Employee> getEmployeByStateVaccine(String state);

    public List<Employee> getEmployeByTypeVaccine(String type);

    public List<Employee> getEmployesByRangeVaccinationDate(LocalDate from, LocalDate to);

}
