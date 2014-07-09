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
import javax.persistence.UniqueConstraint;

/**
 * SecPermissions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sec_permissions", catalog = "jcbir", uniqueConstraints = @UniqueConstraint(columnNames = {
		"permission_name", "role_id" }))
public class SecPermissions implements java.io.Serializable {

	// Fields

	private Integer permissionId;
	private SecRoles secRoles;
	private String permissionName;

	// Constructors

	/** default constructor */
	public SecPermissions() {
	}

	/** full constructor */
	public SecPermissions(SecRoles secRoles, String permissionName) {
		this.secRoles = secRoles;
		this.permissionName = permissionName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "permission_id", unique = true, nullable = false)
	public Integer getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	public SecRoles getSecRoles() {
		return this.secRoles;
	}

	public void setSecRoles(SecRoles secRoles) {
		this.secRoles = secRoles;
	}

	@Column(name = "permission_name", nullable = false, length = 64)
	public String getPermissionName() {
		return this.permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

}