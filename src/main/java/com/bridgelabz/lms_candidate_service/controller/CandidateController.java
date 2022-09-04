package com.bridgelabz.lms_candidate_service.controller;

import com.bridgelabz.lms_candidate_service.dto.CandidateDTO;
import com.bridgelabz.lms_candidate_service.model.CandidateModel;
import com.bridgelabz.lms_candidate_service.services.ICandidateServices;
import com.bridgelabz.lms_candidate_service.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *  Purpose:candidate operation controller
 * @author Anuj Solanki
 * @date-03/09/2022
 */

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    ICandidateServices candidateServices;

    /**
     *  Purpose:adding candidate
     * @param candidateDTO
     * @param token
     * @return
     */
    @PostMapping("/addCandidate")
    public ResponseEntity<Response> addCandidate(@RequestHeader String token , @Valid @RequestBody CandidateDTO candidateDTO){

        Response response=candidateServices.addCandidate(token, candidateDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *  Purpose:getting candidate details
     * @param token
     * @param candidateId
     * @return
     */
    @GetMapping("/getCandidate")
    public ResponseEntity<Response>  getCandidate(@RequestHeader String token,@RequestParam long candidateId){
        Response response= candidateServices.getCandidate(token,candidateId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *  Purpose:getting candidate by status
     * @param candidateStatus
     * @param token
     * @return
     */
    @GetMapping("/getCandidateByStatus")
    public ResponseEntity<Response> getCandidateByStatus(@RequestHeader String token,@RequestParam String candidateStatus){

        Response response= candidateServices.getCandidateByStatus(token, candidateStatus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *  Purpose:changing candidate status
     * @param candidateStatus
     * @param token
     * @param candidateId
     * @return
     */
    @PutMapping("/changeCandidateStatus")
    public ResponseEntity<Response>  changeCandidateStatus(@RequestHeader String token,@RequestParam String candidateStatus,@RequestParam long candidateId){
      Response response=  candidateServices.changeCandidateStatus(token,candidateStatus,candidateId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *  Purpose:counting candidate by status
     * @param candidateStatus
     * @param token
     * @return
     */
    @GetMapping("/getCandidateCount")
    public  ResponseEntity<Response>  countCandidateByStatus (@RequestHeader String token, @RequestParam String candidateStatus){
        Response response= candidateServices.countCandidateByStatus(token,candidateStatus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *  Purpose:updating candidate details
     * @param id
     * @param candidateDTO
     * @param token
     * @return
     */

    @PutMapping("/updateCandidate")
    public  ResponseEntity<Response>  updateCandidate(@RequestHeader String token,@RequestParam long id,@Valid @RequestBody CandidateDTO candidateDTO){
        Response response= candidateServices.updateCandidate(token,id, candidateDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /**
     *  Purpose:deleting candidate
     * @param candidateId
     * @param token
     * @return
     */
    @DeleteMapping("/deleteCandidate")
    public ResponseEntity<Response>  deleteCandidate(@RequestHeader String token,@RequestParam long candidateId){
        Response response= candidateServices.deleteCandidate(token,candidateId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
