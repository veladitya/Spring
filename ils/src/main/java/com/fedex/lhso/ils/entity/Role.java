package com.fedex.lhso.ils.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonView;
import com.fedex.lhso.ils.views.View;


@Entity
public class Role {
	
	@JsonView(View.UserInfo.class)
	@Id
	@GeneratedValue
	private Integer id;
	@JsonView(View.UserInfo.class)
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
