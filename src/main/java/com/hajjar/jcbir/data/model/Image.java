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
 * Image entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "image", catalog = "jcbir")
public class Image implements java.io.Serializable {

	// Fields

	private Integer id;
	private Series series;
	private String filename;
	private String imageInfo;
	private Set<ImageFeature> imageFeatures = new HashSet<ImageFeature>(0);

	// Constructors

	/** default constructor */
	public Image() {
	}

	/** minimal constructor */
	public Image(Series series, String filename, String imageInfo) {
		this.series = series;
		this.filename = filename;
		this.imageInfo = imageInfo;
	}

	/** full constructor */
	public Image(Series series, String filename, String imageInfo,
			Set<ImageFeature> imageFeatures) {
		this.series = series;
		this.filename = filename;
		this.imageInfo = imageInfo;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id", nullable = false)
	public Series getSeries() {
		return this.series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

	@Column(name = "filename", nullable = false, length = 45)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "imageInfo", nullable = false, length = 65535)
	public String getImageInfo() {
		return this.imageInfo;
	}

	public void setImageInfo(String imageInfo) {
		this.imageInfo = imageInfo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "image")
	public Set<ImageFeature> getImageFeatures() {
		return this.imageFeatures;
	}

	public void setImageFeatures(Set<ImageFeature> imageFeatures) {
		this.imageFeatures = imageFeatures;
	}

}