package com.bridgelabz.lms_candidate_service.services;

import com.bridgelabz.lms_candidate_service.dto.CandidateDTO;
import com.bridgelabz.lms_candidate_service.exception.CandidateNotFound;
import com.bridgelabz.lms_candidate_service.model.CandidateModel;
import com.bridgelabz.lms_candidate_service.repository.ICandidateRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  Purpose:creating different candidate services
 * @author Anuj Solanki
 */
@Service
public class CandidateServices implements ICandidateServices{

    @Autowired
    ICandidateRepository candidateRepository;
//    @Autowired
//    Token tokenutil;
    @Autowired
    private  RabbitTemplate rabbitTemplate;


    /**
     *  Purpose:adding candidate
     * @param candidateDTO
     * @return
     */
    @Override
    public CandidateModel addCandidate(CandidateDTO candidateDTO) {

        CandidateModel candidateModel=new CandidateModel(candidateDTO);
        candidateModel.setCreationTimeStamp(LocalDateTime.now());
        candidateRepository.save(candidateModel);
        return candidateModel;
    }

    /**
     *  Purpose:getting candidate details
     * @param candidateId
     * @return
     */
    @Override
    public CandidateModel getCandidate(long candidateId) {
            Optional<CandidateModel> candidateModel = candidateRepository.findById(candidateId);
            if (candidateModel.isPresent()) {
                return candidateModel.get();
            }
       throw new CandidateNotFound(200,"Candidate not found !");
    }

    /**
     *  Purpose:updating candidate details
     * @param candidateId
     * @param candidateDTO
     * @return
     */
    @Override
    public CandidateModel updateCandidate(long candidateId, CandidateDTO candidateDTO) {
            Optional<CandidateModel> candidateModel = candidateRepository.findById(candidateId);
            if (candidateModel.isPresent()) {
                candidateModel.get().setFullName(candidateDTO.getFullName());
                candidateModel.get().setCicId(candidateDTO.getCicId());
                candidateModel.get().setEmail(candidateDTO.getEmail());
                candidateModel.get().setMobileNo(candidateDTO.getMobileNo());
                candidateModel.get().setHireDate(candidateDTO.getHireDate());
                candidateModel.get().setDegree(candidateDTO.getDegree());
                candidateModel.get().setAggPer(candidateDTO.getAggPer());
                candidateModel.get().setCandidateStatus(candidateDTO.getCandidateStatus());
                candidateModel.get().setCity(candidateDTO.getCity());
                candidateModel.get().setState(candidateDTO.getState());
                candidateModel.get().setPreferredJobLocation(candidateDTO.getPreferredJobLocation());
                candidateModel.get().setStatus(candidateDTO.getStatus());
                candidateModel.get().setPassOutYear(candidateDTO.getPassOutYear());
                candidateModel.get().setUpdateTimeStamp(LocalDateTime.now());
                candidateRepository.save(candidateModel.get());
                return candidateModel.get();
            }
            throw new CandidateNotFound(200, "Candidate Not Found !");

    }

    /**
     *  Purpose:deleting candidate
     * @param candidateId
     * @return
     */
    @Override
    public CandidateModel deleteCandidate(long candidateId) {
            Optional<CandidateModel> candidateModel = candidateRepository.findById(candidateId);
            if (candidateModel.isPresent()){
            candidateRepository.delete(candidateModel.get());
            return candidateModel.get();
            }
            throw new CandidateNotFound(200,"Candidate Not Found !");
    }


    /**
     *  Purpose:getting candidate by status
     * @param candidateStatus
     * @return
     */
    @Override
    public List<CandidateModel> getCandidateByStatus( String candidateStatus) {

            List<CandidateModel> candidateModel = candidateRepository.findAll();
            return candidateModel.stream().filter(x -> x.getStatus().equals(candidateStatus)).collect(Collectors.toList());
    }

    /**
     *  Purpose:changing candidate status
     * @param candidateStatus
     * @param candidateId
     * @return
     */
    @Override
    public CandidateModel changeCandidateStatus( String candidateStatus,long candidateId) {

            CandidateModel candidateModel = this.getCandidate( candidateId);
            candidateModel.setCandidateStatus(candidateStatus);
            candidateRepository.save(candidateModel);
            return candidateModel;
    }

    /**
     *  Purpose:counting candidate by status
     * @param candidateStatus
     * @return
     */
    @Override
    public String countCandidateByStatus(  String candidateStatus) {

            List<CandidateModel> candidateModel = this.getCandidateByStatus(candidateStatus);
            Long candidateCount = candidateModel.stream().count();
            return candidateStatus + "- " + candidateCount;

    }




}
