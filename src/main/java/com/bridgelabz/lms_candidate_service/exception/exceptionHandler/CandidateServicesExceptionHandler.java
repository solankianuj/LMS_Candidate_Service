package com.bridgelabz.lms_candidate_service.exception.exceptionHandler;

import com.bridgelabz.lms_candidate_service.exception.CandidateNotFound;
import com.bridgelabz.lms_candidate_service.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CandidateServicesExceptionHandler {

    @ExceptionHandler(CandidateNotFound.class)
    public ResponseEntity<Response> handleCandidateException(CandidateNotFound candidate){
        Response response=new Response();
        response.setErrorCode(400);
        response.setErrorMsg(candidate.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleException(MethodArgumentNotValidException ad) {
        List<ObjectError> objectErrors=ad.getBindingResult().getAllErrors();
        List<String> Message =objectErrors.stream().map(objErr-> objErr.getDefaultMessage()).collect(Collectors.toList());
        Response response = new Response();
        response.setErrorMsg(Message.toString());
        response.setErrorCode(400);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
