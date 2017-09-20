package com.fedex.lhso.ils.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonView;
import com.fedex.lhso.ils.entity.annotations.UniqueUsername;
import com.fedex.lhso.ils.views.View;

@Entity(name="users")
public class User {

	@JsonView(View.UserInfo.class)
	@Id
	@GeneratedValue 
	private Integer id;
	
	@JsonView(View.UserInfo.class)
	@Size(min = 3, message = "Name is not valid")
	@Column(unique = true)
	@UniqueUsername
	private String name;
	
	@JsonView(View.UserInfo.class)
	@Size(min = 1)
	@NotNull
	@Email
	private String email;
	
	@Size(min = 3, message = "password is not valid")
	private String password;
	
	private boolean enabled;
	
	@JsonView(View.UserInfo.class)
	private String userRole;
	
	public User() {
	
	}
	
	public User(String name, String password, String email, String userRole) {
		this.name = name;
		this.password=password;
		this.email=email;
		this.userRole = userRole;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@JsonView(View.UserInfo.class)
	@ManyToMany
	@JoinTable
	private List<Role> roles;

	@JsonView(View.UserInfo.class)
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Blog> blogs;

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	
}
