package org.formation.proxibanque.rest;

import org.formation.proxibanque.dao.DaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DaoExceptionHandlerAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(DaoExceptionHandlerAdvice.class);
	
	@ExceptionHandler(DaoException.class)
    public ResponseEntity handleException(DaoException e) {
		
		LOGGER.error("Exception : " + e.getMessage());
		
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(e.getMessage());
    }    
}
