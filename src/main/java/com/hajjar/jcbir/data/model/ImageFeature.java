package com.hajjar.jcbir.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ImageFeature entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "image_feature", catalog = "jcbir")
public class ImageFeature implements java.io.Serializable {

	// Fields

	private Integer id;
	private Image image;
	private Feature feature;
	private Double value;

	// Constructors

	/** default constructor */
	public ImageFeature() {
	}

	/** full constructor */
	public ImageFeature(Image image, Feature feature, Double value) {
		this.image = image;
		this.feature = feature;
		this.value = value;
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
	@JoinColumn(name = "image_id", nullable = false)
	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feature_id", nullable = false)
	public Feature getFeature() {
		return this.feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	@Column(name = "value", nullable = false, precision = 22, scale = 0)
	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}