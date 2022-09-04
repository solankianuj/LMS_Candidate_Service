package com.bridgelabz.lms_candidate_service.services;

import com.bridgelabz.lms_candidate_service.dto.CandidateDTO;
import com.bridgelabz.lms_candidate_service.model.CandidateModel;
import com.bridgelabz.lms_candidate_service.util.Response;


import java.util.List;

public interface ICandidateServices {
    Response addCandidate(String  token, CandidateDTO candidateDTO);
    Response getCandidate(String token,long candidateId);
    Response updateCandidate(String token,long id,CandidateDTO candidateDTO);
    Response deleteCandidate(String token,long candidateId);
    Response getCandidateByStatus(String token, String candidateStatus);
    Response changeCandidateStatus(String token, String candidateStatus,long candidateId);
    Response countCandidateByStatus(String token, String candidateStatus);

}
