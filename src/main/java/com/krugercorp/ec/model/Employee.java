package com.krugercorp.ec.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "employee	")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	@Column(nullable = false, length = 10)
    private String dni;
    
	@Column(nullable = false)
    private String name;
    
	@Column(nullable = false)
    private String surname;
    
	@Column(nullable = false)
    private String email;
    
    private LocalDate birthday;
    
	private String address;
    
    private String phone;
    
    private String state;
    
    @Column(name = "state_vaccine")
    private String stateVaccine;
    
	@Column(name = "type_vaccine")
    private String typeVaccine;
    
    @Column(name = "vaccination_date")
	private LocalDate vaccinationDate;
    
    private Byte doses;
    
    @OneToOne
    @JoinColumn(name = "empl_fk_user", referencedColumnName = "id")
    private Users users;

}
