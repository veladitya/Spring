package com.fedex.blogger.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import com.fedex.blogger.entity.annotations.UniqueUsername;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min = 3, message = "Name is not valid")
	@Column(unique = true)
	@UniqueUsername
	private String name;
	
	@Size(min = 1)
	@Email
	private String email;
	
	@Size(min = 3, message = "password is not valid")
	private String password;
	
	private boolean enabled;
	
	public User() {
	
	}
	
	public User(String name, String password, String email) {
		this.name = name;
		this.password=password;
		this.email=email;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany
	@JoinTable
	private List<Role> roles;

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

}