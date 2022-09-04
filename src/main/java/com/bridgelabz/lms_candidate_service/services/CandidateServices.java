package com.bridgelabz.lms_candidate_service.services;

import com.bridgelabz.lms_candidate_service.dto.CandidateDTO;
import com.bridgelabz.lms_candidate_service.exception.CandidateNotFound;
import com.bridgelabz.lms_candidate_service.model.CandidateModel;
import com.bridgelabz.lms_candidate_service.repository.ICandidateRepository;
import com.bridgelabz.lms_candidate_service.util.Response;
import com.bridgelabz.lms_candidate_service.util.Token;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *  Purpose:creating different candidate services
 * @author Anuj Solanki
 * @date-03/09/2022
 */
@Service

public class CandidateServices implements ICandidateServices{

    @Autowired
    ICandidateRepository candidateRepository;
    @Autowired
    Token tokenutil;
    @Autowired
    private  RabbitTemplate rabbitTemplate;
    @Autowired
    RestTemplate restTemplate;


    /**
     *  Purpose:adding candidate
     *  @param token
     * @param candidateDTO
     * @return
     */
    @Override
    public Response addCandidate(String token, CandidateDTO candidateDTO) {

        boolean isUserPresent=restTemplate.getForObject("http://localhost:8082/admin/validatingUser/"+token, Boolean.class);
        if(isUserPresent){
        CandidateModel candidateModel=new CandidateModel(candidateDTO);
        candidateModel.setCreationTimeStamp(LocalDateTime.now());
        candidateRepository.save(candidateModel);
        return new Response("Candidate added",200,candidateModel);
        }
        throw new CandidateNotFound(200,"admin not found !");
    }

    /**
     *  Purpose:getting candidate details
     * @param candidateId
     * @param token
     * @return
     */
    @Override
    public Response getCandidate(String token,long candidateId) {

        boolean isUserPresent=restTemplate.getForObject("http://localhost:8082/admin/validatingUser/"+token, Boolean.class);
        if(isUserPresent) {
            Optional<CandidateModel> candidateModel = candidateRepository.findById(candidateId);
            if (candidateModel.isPresent()) {
                return new Response("Getting Candidate",200,candidateModel.get());
            }
            throw new CandidateNotFound(200, "Candidate not found !");
        }
        throw new CandidateNotFound(200,"Admin Not found !");
    }

    /**
     *  Purpose:updating candidate details
     * @param candidateId
     * @param candidateDTO
     * @param token
     * @return
     */
    @Override
    public Response updateCandidate(String token,long candidateId, CandidateDTO candidateDTO) {

        boolean isUserPresent=restTemplate.getForObject("http://localhost:8082/admin/validatingUser/"+token, Boolean.class);
        if(isUserPresent) {
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
                return new Response("Updating Candidate",200,candidateModel.get());
            }
            throw new CandidateNotFound(200, "Candidate Not Found !");
        }
        throw new CandidateNotFound(200,"Admin Not found !");

    }

    /**
     *  Purpose:deleting candidate
     * @param candidateId
     * @param token
     * @return
     */
    @Override
    public Response deleteCandidate(String token,long candidateId) {

        boolean isUserPresent=restTemplate.getForObject("http://localhost:8082/admin/validatingUser/"+token, Boolean.class);
        if(isUserPresent) {
            Optional<CandidateModel> candidateModel = candidateRepository.findById(candidateId);
            if (candidateModel.isPresent()) {
                candidateRepository.delete(candidateModel.get());
                return new Response("Deleting Candidate",200,candidateModel.get());
            }
            throw new CandidateNotFound(200, "Candidate Not Found !");
        }
        throw new CandidateNotFound(200, "Admin Not Found !");

    }


    /**
     *  Purpose:getting candidate by status
     * @param candidateStatus
     * @param token
     * @return
     */
    @Override
    public Response getCandidateByStatus(String token, String candidateStatus) {

        boolean isUserPresent=restTemplate.getForObject("http://localhost:8082/admin/validatingUser/"+token, Boolean.class);
        if(isUserPresent) {
            List<CandidateModel> candidateModel = candidateRepository.findAll();
            List<CandidateModel> list= candidateModel.stream().filter(x -> x.getCandidateStatus().equals(candidateStatus)).collect(Collectors.toList());
            return new Response(" Candidate List",200,list);
        }
        throw new CandidateNotFound(200, "Admin Not Found !");

    }

    /**
     *  Purpose:changing candidate status
     * @param candidateStatus
     * @param candidateId
     * @param token
     * @return
     */
    @Override
    public Response changeCandidateStatus(String token, String candidateStatus,long candidateId) {

        boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/admin/validatingUser/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<CandidateModel> candidateModel = candidateRepository.findById(candidateId);
            if (candidateModel.isPresent()) {
                candidateModel.get().setCandidateStatus(candidateStatus);
                candidateRepository.save(candidateModel.get());
                return new Response("Changing Candidate Status", 200, candidateModel.get());
            }
            throw new CandidateNotFound(200, "Admin Not Found !");

        }
        return null;
    }

        /**
         *  Purpose:counting candidate by status
         * @param candidateStatus
         * @param token
         * @return
         */
        @Override
        public Response countCandidateByStatus (String token, String candidateStatus){

            boolean isUserPresent = restTemplate.getForObject("http://localhost:8082/admin/validatingUser/" + token, Boolean.class);
            if (isUserPresent) {
                List<CandidateModel> candidateModel = candidateRepository.findAll();
                List<CandidateModel> list= candidateModel.stream().filter(x -> x.getCandidateStatus().equals(candidateStatus)).collect(Collectors.toList());
                Long candidateCount = list.stream().count();
                String candidatCounting=candidateStatus + "- " + candidateCount;
                return new Response("Counting Candidate ", 200, candidatCounting);
            }
            throw new CandidateNotFound(200, "Admin Not Found !");

        }



}
