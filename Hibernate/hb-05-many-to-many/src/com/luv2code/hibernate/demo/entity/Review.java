package com.luv2code.hibernate.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_increment")
	@SequenceGenerator(name="id_increment", sequenceName = "auto_increment", allocationSize=1)
	@Column(name="id")
	private int id;
	
	@Column(name="comments")
	private String comments;
	
	public Review () {
		
	}

	public Review(String comments) {
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", comments=" + comments + "]";
	}
	
}
