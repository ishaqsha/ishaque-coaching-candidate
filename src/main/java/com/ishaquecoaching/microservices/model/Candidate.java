package com.ishaquecoaching.microservices.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection = "Candidate")
public class Candidate {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private long id;

	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String name;
	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String email;
	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String mobile;
	private String address;
	private String password;

	private String course;
	private String medium;
	private String degreeName;

	private String confirmPassword;
	private String degreeCompleteYear;
	private String techDetais;


	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getDegreeCompleteYear() {
		return degreeCompleteYear;
	}

	public void setDegreeCompleteYear(String degreeCompleteYear) {
		this.degreeCompleteYear = degreeCompleteYear;
	}

	public String getTechDetais() {
		return techDetais;
	}

	public void setTechDetais(String techDetais) {
		this.techDetais = techDetais;
	}

	@Override
	public String toString() {
		return "Candidate [id=" + id + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", address="
				+ address + ", password=" + password + ", course=" + course + ", medium=" + medium + ", degreeName="
				+ degreeName + ", confirmPassword=" + confirmPassword + ", degreeCompleteYear=" + degreeCompleteYear
				+ ", techDetais=" + techDetais + "]";
	}


	
	
	

}
