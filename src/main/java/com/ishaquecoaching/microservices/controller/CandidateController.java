package com.ishaquecoaching.microservices.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ishaquecoaching.microservices.model.Candidate;
import com.ishaquecoaching.microservices.exception.*;
import com.ishaquecoaching.microservices.repository.CandidateRepo;
import com.ishaquecoaching.microservices.service.CandidateSequenceGeneratorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/candidates")
public class CandidateController {

	Logger log = LoggerFactory.getLogger(CandidateController.class);

	@Autowired
	private CandidateRepo canidateRepository;

	@Autowired
	private CandidateSequenceGeneratorService sequenceGeneratorService;

	@GetMapping("")
	public List<Candidate> getAllcandidates() {
		return canidateRepository.findAll();
	}

	@GetMapping("/param")
	public ResponseEntity<?> getCandidateById(@RequestParam(value = "id", required = false) Long candidateId,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "mobile", required = false) String mobile) throws ResourceNotFoundException {

		if (candidateId != null) {
			log.info("request Recieved for findByID with id:{} ", candidateId);
			Candidate candidate = canidateRepository.findById(candidateId).orElseThrow(() -> {
				log.error("candidate not found for the id: {}", candidateId);
				return new ResourceNotFoundException("Candidate not found for this id :: " + candidateId);
			});
			return ResponseEntity.ok().body(candidate);
		} else if (email != null ||mobile!=null) {
			log.info("request Recieved for findByEmail with email:{} ", email);
			List<Candidate> candidates = canidateRepository.findByEmailOrMobile(email,mobile);
			if (candidates == null || candidates.size() == 0) {
				log.error("candidate not found for the email: {} or Mobile:{}", email,mobile);
				throw new ResourceNotFoundException("Candidate not found for this email :"+email+" or Mobile:"+mobile);
			} else {
				return ResponseEntity.ok().body(candidates);
			}
		}
		return null;
	}

	@PostMapping("")
	public Candidate createCandidate(@Valid @RequestBody Candidate candidate) {
		
		log.info("request recieved for the candidate create with detail: {}",candidate);
		
		candidate.setId(sequenceGeneratorService.generateSequence(Candidate.SEQUENCE_NAME));
		return canidateRepository.save(candidate);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Candidate> updateCandidate(@PathVariable(value = "id") Long candidateId,
			@Valid @RequestBody Candidate employeeDetails) throws ResourceNotFoundException {
		Candidate employee = canidateRepository.findById(candidateId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + candidateId));

		final Candidate updatedEmployee = canidateRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteCandidate(@PathVariable(value = "id") Long candidateId)
			throws ResourceNotFoundException {
		Candidate candidate = canidateRepository.findById(candidateId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + candidateId));

		canidateRepository.delete(candidate);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
