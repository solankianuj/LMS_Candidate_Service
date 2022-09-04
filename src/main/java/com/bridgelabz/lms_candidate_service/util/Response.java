package com.bridgelabz.lms_candidate_service.util;

import com.bridgelabz.lms_candidate_service.model.CandidateModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * purpose: Creating a response class
 * @author Anuj Solanki
 */
@Data
@AllArgsConstructor
public class Response {
    private String statusMsg;
    private long statusCode;
    private Object object;

    public Response() {

    }
}
