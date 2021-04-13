package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int qId;
	private String qtext;

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	@ManyToOne
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "question")
	@OneToMany(mappedBy = "question")

	private List<Answer> answers = new ArrayList<>();

	public Question(String qtext) {
		super();
		this.qtext = qtext;
	}

	public String getQtext() {
		return qtext;
	}

	public void setQtext(String qtext) {
		this.qtext = qtext;
	}

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

}
