package com.bridgelabz.lms_candidate_service.model;

import com.bridgelabz.lms_candidate_service.dto.CandidateDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * purpose:creating candidate model
 * @author Anuj Solanki
 */
@Entity
@Table(name = "candidateData")
@Data
public class CandidateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String cicId;
    private String fullName;
    private String email;
    private String mobileNo;
    private String hireDate;
    private String degree;
    private Double aggPer;
    private String city;
    private String state;
    private String preferredJobLocation;
    private String status;
    private String passOutYear;
    private String creatorUser;
    private String candidateStatus;
    Long techStackId;
    private LocalDateTime creationTimeStamp;
    private LocalDateTime updateTimeStamp;

    public CandidateModel(CandidateDTO candidateDTO) {
        this.fullName=candidateDTO.getFullName();
        this.cicId=candidateDTO.getCicId();
        this.email=candidateDTO.getEmail();
        this.mobileNo=candidateDTO.getMobileNo();
        this.hireDate=candidateDTO.getHireDate();
        this.degree=candidateDTO.getDegree();
        this.aggPer=candidateDTO.getAggPer();
        this.city=candidateDTO.getCity();
        this.state=candidateDTO.getState();
        this.preferredJobLocation=candidateDTO.getPreferredJobLocation();
        this.status=candidateDTO.getStatus();
        this.passOutYear=candidateDTO.getPassOutYear();
        this.creatorUser=candidateDTO.getCreatorUser();
        this.candidateStatus=candidateDTO.getCandidateStatus();
        this.techStackId=candidateDTO.getTechStackId();
    }

    public CandidateModel() {

    }
}
