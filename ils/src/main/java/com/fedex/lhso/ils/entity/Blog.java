package com.fedex.lhso.ils.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonView;
import com.fedex.lhso.ils.views.View;

@Entity
public class Blog {
	@JsonView(View.UserInfo.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonView(View.UserInfo.class)
	@Size(min=3, message="Blog Name is not valid")
	private String name;
	
	@JsonView(View.UserInfo.class)
	@Size(min=1)
	@URL
	private String url;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@JsonView(View.UserInfo.class)
	@OneToMany(mappedBy="blog", cascade=CascadeType.REMOVE)
	private List<Item> items;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
