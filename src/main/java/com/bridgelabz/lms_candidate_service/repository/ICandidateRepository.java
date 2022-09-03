package com.bridgelabz.lms_candidate_service.repository;

import com.bridgelabz.lms_candidate_service.model.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * purpose:creating candidate repository
 */
@Repository
public interface ICandidateRepository extends JpaRepository<CandidateModel,Long> {
    Optional<CandidateModel> findByCandidateStatus(String status);
}
