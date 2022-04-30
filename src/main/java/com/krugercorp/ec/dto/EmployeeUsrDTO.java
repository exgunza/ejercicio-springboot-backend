package com.krugercorp.ec.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EmployeeUsrDTO {
	
	@NotBlank(message = "no debe ser vacío")
	@Pattern(regexp = "^[0-9]{10}$", message = "debe ser 10 dígitos sin letras")
    private String dni;
    
	@NotBlank(message = "no debe ser vacío")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "debe ser solo letras")
    private String name;
    
	@NotBlank(message = "no debe ser vacío")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "debe ser solo letras")
    private String surname;
    
	@NotBlank(message = "no debe ser vacío")
	@Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;
    
	@Past
    private LocalDate birthday;
    
	private String address;
    
	@Pattern(regexp = "^([0-9]{7}|[0-9]{10})$")
    private String phone;
    
	@Pattern(regexp = "^A|E$", message = "debe ser A ó E")
    private String state;
    
    @Pattern(regexp = "^(NV)|V$", message = "debe ser NV ó V")
    @Column(name = "state_vaccine")
    private String stateVaccine;
    
	@Column(name = "type_vaccine")
    private String typeVaccine;
    
	@PastOrPresent
    @Column(name = "vaccination_date")
	private LocalDate vaccinationDate;
    
	@Min(0) 
    @Max(4)
    private byte doses;

}
