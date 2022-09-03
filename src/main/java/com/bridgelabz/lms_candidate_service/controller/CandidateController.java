package com.bridgelabz.lms_candidate_service.controller;

import com.bridgelabz.lms_candidate_service.dto.CandidateDTO;
import com.bridgelabz.lms_candidate_service.model.CandidateModel;
import com.bridgelabz.lms_candidate_service.services.ICandidateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *  Purpose:candidate operation controller
 * @author Anuj Solanki
 */

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    ICandidateServices candidateServices;

    /**
     *  Purpose:adding candidate
     * @param candidateDTO
     * @param techStackId
     * @return
     */
    @PostMapping("/addCandidate")
    public CandidateModel addCandidate(@Valid @RequestBody CandidateDTO candidateDTO, @RequestParam List<Long> techStackId){
       return candidateServices.addCandidate(candidateDTO);
    }

    /**
     *  Purpose:getting candidate details
     * @param candidateId
     * @return
     */
    @GetMapping("/getCandidate")
    public CandidateModel getCandidate(@RequestParam long candidateId){
        return candidateServices.getCandidate(candidateId);
    }

    /**
     *  Purpose:getting candidate by status
     * @param candidateStatus
     * @return
     */
    @GetMapping("/getCandidateByStatus")
    public List<CandidateModel> getCandidateByStatus(@RequestParam String candidateStatus){

        return candidateServices.getCandidateByStatus(candidateStatus);
    }

    /**
     *  Purpose:changing candidate status
     * @param candidateStatus
     * @param candidateId
     * @return
     */
    @PutMapping("/changeCandidateStatus")
    public CandidateModel changeCandidateStatus(@RequestParam String candidateStatus,@RequestParam long candidateId){
      return   candidateServices.changeCandidateStatus(candidateStatus,candidateId);
    }

    /**
     *  Purpose:counting candidate by status
     * @param candidateStatus
     * @return
     */
    @GetMapping("/getCandidateCount")
    public  String countCandidateByStatus ( @RequestParam String candidateStatus){
        return candidateServices.countCandidateByStatus(candidateStatus);
    }

    /**
     *  Purpose:updating candidate details
     * @param id
     * @param candidateDTO
     * @return
     */

    @PutMapping("/updateCandidate")
    public CandidateModel updateCandidate(@RequestParam long id,@Valid @RequestBody CandidateDTO candidateDTO){
        return candidateServices.updateCandidate(id, candidateDTO);

    }

    /**
     *  Purpose:deleting candidate
     * @param candidateId
     * @return
     */
    @DeleteMapping("/deleteCandidate")
    public CandidateModel deleteCandidate(@RequestParam long candidateId){
        return candidateServices.deleteCandidate(candidateId);
    }
}
