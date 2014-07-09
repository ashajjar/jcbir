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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SecUsers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sec_users", catalog = "jcbir", uniqueConstraints = @UniqueConstraint(columnNames = "user_loginname"))
public class SecUsers implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String userLoginname;
	private String userPassword;
	private String name;
	private Boolean enabled;
	private String email;
	private String mobile;
	private Set<SecRoles> secRoleses = new HashSet<SecRoles>(0);

	// Constructors

	/** default constructor */
	public SecUsers() {
	}

	/** minimal constructor */
	public SecUsers(String userLoginname, String userPassword, String name,
			Boolean enabled, String email, String mobile) {
		this.userLoginname = userLoginname;
		this.userPassword = userPassword;
		this.name = name;
		this.enabled = enabled;
		this.email = email;
		this.mobile = mobile;
	}

	/** full constructor */
	public SecUsers(String userLoginname, String userPassword, String name,
			Boolean enabled, String email, String mobile,
			Set<SecRoles> secRoleses) {
		this.userLoginname = userLoginname;
		this.userPassword = userPassword;
		this.name = name;
		this.enabled = enabled;
		this.email = email;
		this.mobile = mobile;
		this.secRoleses = secRoleses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "user_loginname", unique = true, nullable = false, length = 32)
	public String getUserLoginname() {
		return this.userLoginname;
	}

	public void setUserLoginname(String userLoginname) {
		this.userLoginname = userLoginname;
	}

	@Column(name = "user_password", nullable = false, length = 254)
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "name", nullable = false, length = 254)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "enabled", nullable = false)
	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "email", nullable = false, length = 254)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "mobile", nullable = false, length = 45)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "sec_user_roles", catalog = "jcbir", uniqueConstraints = @UniqueConstraint(columnNames = {
			"role_id", "user_id" }), joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public Set<SecRoles> getSecRoleses() {
		return this.secRoleses;
	}

	public void setSecRoleses(Set<SecRoles> secRoleses) {
		this.secRoleses = secRoleses;
	}

}