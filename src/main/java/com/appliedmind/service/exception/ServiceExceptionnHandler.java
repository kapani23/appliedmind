package com.appliedmind.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.appliedmind.dto.user.ValidationResponse;
import com.appliedmind.service.ServiceException;

@ControllerAdvice
public class ServiceExceptionnHandler extends ResponseEntityExceptionHandler{
	  
	    @ExceptionHandler(ServiceException.class)
		public final ResponseEntity<ValidationResponse> handleServiceLayerException(ServiceException ex) {
	    	return new ResponseEntity<>(ex.getValidationResponse(),HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}


