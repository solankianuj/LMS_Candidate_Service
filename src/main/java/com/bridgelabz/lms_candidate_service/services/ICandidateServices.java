package com.bridgelabz.lms_candidate_service.services;

import com.bridgelabz.lms_candidate_service.dto.CandidateDTO;
import com.bridgelabz.lms_candidate_service.model.CandidateModel;


import java.util.List;

public interface ICandidateServices {
    CandidateModel addCandidate(CandidateDTO candidateDTO);
    CandidateModel getCandidate(long candidateId);
    CandidateModel updateCandidate(long id,CandidateDTO candidateDTO);
    CandidateModel deleteCandidate(long candidateId);
    List<CandidateModel> getCandidateByStatus( String candidateStatus);
    CandidateModel changeCandidateStatus( String candidateStatus,long candidateId);
    String countCandidateByStatus( String candidateStatus);

}
