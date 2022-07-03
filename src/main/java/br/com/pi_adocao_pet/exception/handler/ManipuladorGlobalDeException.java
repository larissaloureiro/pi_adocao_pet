package br.com.pi_adocao_pet.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import br.com.pi_adocao_pet.exception.ExceptionResponse;

@ControllerAdvice
@RestController
public class ManipuladorGlobalDeException {

	public final ResponseEntity<ExceptionResponse> invalidJwtAuthenticationException(Exception ex, WebRequest request) {

		
			ExceptionResponse exceptionResponse = 
					new ExceptionResponse(
						new Date(), 
						ex.getMessage(),
					request.getDescription(false));
			return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
		}
	}