package com.hajjar.jcbir.data.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Series entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "series", catalog = "jcbir")
public class Series implements java.io.Serializable {

	// Fields

	private Integer id;
	private Patient patient;
	private String comments;
	private Set<Image> images = new HashSet<Image>(0);

	// Constructors

	/** default constructor */
	public Series() {
	}

	/** minimal constructor */
	public Series(Patient patient, String comments) {
		this.patient = patient;
		this.comments = comments;
	}

	/** full constructor */
	public Series(Patient patient, String comments, Set<Image> images) {
		this.patient = patient;
		this.comments = comments;
		this.images = images;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false)
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Column(name = "comments", nullable = false, length = 65535)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "series")
	public Set<Image> getImages() {
		return this.images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

}