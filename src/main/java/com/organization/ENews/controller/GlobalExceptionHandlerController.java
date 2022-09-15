package com.organization.ENews.controller;

import com.organization.ENews.entity.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandlerController {
	String status;
	ResponseStatus rs = new ResponseStatus();
	  

	public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseStatus> handleError(Exception ex) {
		LOGGER.info("start");
		status = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT) + ": " + "\t Reason Code : "
				+ HttpStatus.BAD_REQUEST.value() + "Error Message : " + ex.getMessage() + " ";
		LOGGER.debug("error : {} ", status);
		
		rs.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
		rs.setReasonCode(HttpStatus.BAD_REQUEST.value());
		rs.setErrorMessage(ex.getMessage());
		
		LOGGER.error(ex.getMessage());
		
		if (ex instanceof MethodArgumentNotValidException)
		{
			MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
			String message = "";
			java.util.List<FieldError> errors = exception.getBindingResult().getFieldErrors();
			for (FieldError err : errors) {
				message += err.getDefaultMessage() + ", ";
			}
			
			rs.setErrorMessage(message);
			return new ResponseEntity<ResponseStatus>(rs, HttpStatus.BAD_REQUEST);

		}
		if(ex instanceof ConstraintViolationException) {
			ConstraintViolationException constraintException = (ConstraintViolationException) ex;
			java.util.Set<ConstraintViolation<?>> set = constraintException.getConstraintViolations();
			String errorMessage = "Input validation failed: ";
			for(ConstraintViolation<?> constraintViolation : set)
				errorMessage += constraintViolation.getMessageTemplate()+" ";
			rs.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
			rs.setErrorMessage(errorMessage);
			rs.setReasonCode(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<ResponseStatus>(rs, HttpStatus.BAD_REQUEST);
			}

		
		 else {
			 	rs.setTimestamp(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
				rs.setReasonCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
				rs.setErrorMessage( "Something went Wrong..try again Later!");
				return new ResponseEntity<ResponseStatus>(rs, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
	}
	
	
	 

}


