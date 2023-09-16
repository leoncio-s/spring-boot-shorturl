package com.leoncio.shorturl.handler;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {
    
    @Autowired
    private Logger logger;

    private ApiError apiErrorGenericStatus(String message){
        ApiError apiError = new ApiError();

        apiError.setMessage(message);

        return apiError;
    }

    private ApiError apiError(HttpStatus status, String message){
        ApiError apiError = new ApiError();

        apiError.setMessage(message);
        apiError.setStatus(status);

        return apiError;
    }

    private HttpHeaders headers(){
        HttpHeaders header = new HttpHeaders();

        header.setContentType(MediaType.APPLICATION_JSON);;

        return header;
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiError> globalHandler(Exception e, WebRequest request) throws NoSuchMessageException, Exception{
        ApiError error = apiErrorGenericStatus(e.getMessage());
        logger.error("> [ERROR]: ", e.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers()).body(error);
    }

    @ExceptionHandler(ShortUrlNotFoundException.class)
    private ResponseEntity<ApiError> handleShortUrlNotFoundException(ShortUrlNotFoundException e, WebRequest request){
        ApiError error = apiError(HttpStatus.NOT_FOUND, e.getMessage());
        logger.error("> [ERROR]: ", e.getCause());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers()).body(error);
    }

    @ExceptionHandler(CodeUrlInvalidoException.class)
    private ResponseEntity<ApiError> handleCodeUrlInvalidoException(CodeUrlInvalidoException e, WebRequest request){
        ApiError error = apiError(HttpStatus.BAD_REQUEST, e.getMessage());
        logger.error("> [ERROR]: ", e.getCause());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
