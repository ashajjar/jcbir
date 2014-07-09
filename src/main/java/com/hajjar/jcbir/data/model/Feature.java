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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Feature entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "feature", catalog = "jcbir")
public class Feature implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String category;
	private Set<ImageFeature> imageFeatures = new HashSet<ImageFeature>(0);

	// Constructors

	/** default constructor */
	public Feature() {
	}

	/** minimal constructor */
	public Feature(String name, String category) {
		this.name = name;
		this.category = category;
	}

	/** full constructor */
	public Feature(String name, String category, Set<ImageFeature> imageFeatures) {
		this.name = name;
		this.category = category;
		this.imageFeatures = imageFeatures;
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

	@Column(name = "category", nullable = false, length = 45)
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "feature")
	public Set<ImageFeature> getImageFeatures() {
		return this.imageFeatures;
	}

	public void setImageFeatures(Set<ImageFeature> imageFeatures) {
		this.imageFeatures = imageFeatures;
	}

}