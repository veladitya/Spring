package com.fedex.blogger.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Role {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	@ManyToMany(mappedBy="roles")
	private List<User> users;
	
	public Role(){
		
	}
	
	public Role(String role){
		this.name = role;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
