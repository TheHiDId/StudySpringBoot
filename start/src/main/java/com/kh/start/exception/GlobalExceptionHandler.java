package com.kh.start.exception;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MemberIdDuplicateException.class)
	public ResponseEntity<?> handleMemberIdDuplicate(MemberIdDuplicateException e) {
		
		Map<String, String> error = new HashMap<>();
		
		error.put("cause", e.getMessage());
		
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(CustomAuthenticationException.class)
	public ResponseEntity<?> handleCustomAuthentication(CustomAuthenticationException e) {
		
		Map<String, String> error = new HashMap<>();
		
		error.put("cause", e.getMessage());
		
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleArgumentNotValid(MethodArgumentNotValidException e) {
		
		Map<String, String> errors = new HashMap<>();
		
		e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		return ResponseEntity.badRequest().body(errors);
	}

}
