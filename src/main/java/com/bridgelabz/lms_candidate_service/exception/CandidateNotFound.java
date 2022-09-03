package com.bridgelabz.lms_candidate_service.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * purpose:creating custom candidate not found exception
 * @author Anuj Solanki
 */
@ResponseStatus
public class CandidateNotFound extends RuntimeException{
    private long errorCode;
    private String statusMessage;

    public CandidateNotFound(long errorCode, String statusMessage) {
        super(statusMessage);
        this.errorCode = errorCode;
        this.statusMessage = statusMessage;
    }
}
