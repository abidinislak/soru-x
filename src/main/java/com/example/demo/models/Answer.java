package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String atext;

	@ManyToOne(fetch = FetchType.LAZY)
	private Question question;

	private boolean trueMu;

	public boolean isTrueMu() {
		return trueMu;
	}

	public void setTrueMu(boolean trueMu) {
		this.trueMu = trueMu;
	}

	public String getAtext() {
		return atext;
	}

	public void setAtext(String atext) {
		this.atext = atext;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
