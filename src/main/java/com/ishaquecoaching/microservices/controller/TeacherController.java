package com.ishaquecoaching.microservices.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ishaquecoaching.microservices.model.Teacher;
import com.ishaquecoaching.microservices.exception.*;
import com.ishaquecoaching.microservices.repository.TeacherRepo;
import com.ishaquecoaching.microservices.service.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/teachers")
public class TeacherController {

	@Autowired
	private TeacherRepo teacherRepository;

	@Autowired
	private CandidateSequenceGeneratorService sequenceGeneratorService;

	@GetMapping("")
	public List<Teacher> getAllteachers() {
		return teacherRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Teacher> getTeahcerById(@PathVariable(value = "id") Long teacherId)
			throws ResourceNotFoundException {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + teacherId));
		return ResponseEntity.ok().body(teacher);
	}

	@PostMapping("")
	public Teacher createteacher(@Valid @RequestBody Teacher teacher) {
		teacher.setId(sequenceGeneratorService.generateSequence(Teacher.SEQUENCE_NAME));
		return teacherRepository.save(teacher);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Teacher> updateteacher(@PathVariable(value = "id") Long teacherId,
			@Valid @RequestBody Teacher employeeDetails) throws ResourceNotFoundException {
		Teacher employee = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + teacherId));
		final Teacher updatedEmployee = teacherRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteteacher(@PathVariable(value = "id") Long teacherId)
			throws ResourceNotFoundException {
		Teacher teacher = teacherRepository.findById(teacherId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + teacherId));

		teacherRepository.delete(teacher);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
