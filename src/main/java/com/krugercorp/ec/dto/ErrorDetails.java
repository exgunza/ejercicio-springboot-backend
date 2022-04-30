package com.krugercorp.ec.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
	
	private LocalDateTime timestamp = LocalDateTime.now();
	@NonNull
	private String message;
	@NonNull
	private String details;
	
}
