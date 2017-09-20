package com.fedex.lhso.ils.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.fedex.lhso.ils.views.View;

@Entity
public class Item {
	
	@JsonView(View.UserInfo.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonView(View.UserInfo.class)
	@Size(min = 3, message = "Title  is not valid")
	@Column(length = 1000)
	private String title;

	@JsonView(View.UserInfo.class)
	@Lob
	@Column(length = Integer.MAX_VALUE)
	@Size(min = 1, message = "Description is not valid")
	private String description;

	@JsonView(View.UserInfo.class)
	@Column(name = "published_data")
	private Date publishedDate;
	
	@JsonView(View.UserInfo.class)
	@Column(length = 1000)
	private String link;

	@ManyToOne
	@JoinColumn(name = "blog_id")
	private Blog blog;

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
}
