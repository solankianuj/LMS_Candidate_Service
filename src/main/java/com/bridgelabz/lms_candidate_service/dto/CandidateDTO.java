package com.bridgelabz.lms_candidate_service.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * purpose:creating candidate dto and adding validation on candidate dto
 * @author Anuj Solanki
 */
@Data
public class CandidateDTO {

    @NotNull(message = "CICID can't be empty.")
    private String cicId;
    @Pattern(regexp = "^[A-Z]{1}[a-z A-Z \\s]{2,}$",message = "name is not valid")
    private String fullName;
//    @Pattern(regexp = "^[a-z]{1,}[@]{1}[a-z]{2,}[.]{1}[a-z]{3,}",message = "invalid email id ")
    private String email;
    @Pattern(regexp = "^[6-9]{1}[0-9]{9}",message = "Invalid Mobile Number !")
    private String mobileNo;
    @NotNull(message = "Hiring Date Can Not Be Noll ")
    private String hireDate;
    @NotEmpty(message = "degree required")
    private String degree;
    @NotNull(message = "aggregate percentage required")
    private Double aggPer;
    @NotEmpty(message = "city required")
    private String city;
    @NotEmpty(message = "state required")
    private String state;
    @NotEmpty(message = "job location required")
    private String preferredJobLocation;
    @NotEmpty(message = " status required")
    private String status;
    @NotEmpty(message = " passOutYear required")
    private String passOutYear;
    @NotEmpty(message = "creatorUser required")
    private String creatorUser;
    @NotEmpty(message = "required")
    private String candidateStatus;
    Long techStackId;
}
