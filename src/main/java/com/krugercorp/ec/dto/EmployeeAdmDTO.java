package com.krugercorp.ec.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class EmployeeAdmDTO {
	
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

}
