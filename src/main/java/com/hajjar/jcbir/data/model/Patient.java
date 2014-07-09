package com.hajjar.jcbir.data.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Patient entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "patient", catalog = "jcbir")
public class Patient implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Timestamp dob;
	private String comments;
	private Set<Series> serieses = new HashSet<Series>(0);

	// Constructors

	/** default constructor */
	public Patient() {
	}

	/** minimal constructor */
	public Patient(String name, Timestamp dob, String comments) {
		this.name = name;
		this.dob = dob;
		this.comments = comments;
	}

	/** full constructor */
	public Patient(String name, Timestamp dob, String comments,
			Set<Series> serieses) {
		this.name = name;
		this.dob = dob;
		this.comments = comments;
		this.serieses = serieses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "dob", nullable = false, length = 19)
	public Timestamp getDob() {
		return this.dob;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	@Column(name = "comments", nullable = false, length = 65535)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<Series> getSerieses() {
		return this.serieses;
	}

	public void setSerieses(Set<Series> serieses) {
		this.serieses = serieses;
	}

}