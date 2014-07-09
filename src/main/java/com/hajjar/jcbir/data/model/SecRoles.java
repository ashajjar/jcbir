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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SecRoles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sec_roles", catalog = "jcbir", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class SecRoles implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String name;
	private Set<SecPermissions> secPermissionses = new HashSet<SecPermissions>(
			0);
	private Set<SecUsers> secUserses = new HashSet<SecUsers>(0);

	// Constructors

	/** default constructor */
	public SecRoles() {
	}

	/** minimal constructor */
	public SecRoles(String name) {
		this.name = name;
	}

	/** full constructor */
	public SecRoles(String name, Set<SecPermissions> secPermissionses,
			Set<SecUsers> secUserses) {
		this.name = name;
		this.secPermissionses = secPermissionses;
		this.secUserses = secUserses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "name", unique = true, nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "secRoles")
	public Set<SecPermissions> getSecPermissionses() {
		return this.secPermissionses;
	}

	public void setSecPermissionses(Set<SecPermissions> secPermissionses) {
		this.secPermissionses = secPermissionses;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "secRoleses")
	public Set<SecUsers> getSecUserses() {
		return this.secUserses;
	}

	public void setSecUserses(Set<SecUsers> secUserses) {
		this.secUserses = secUserses;
	}

}