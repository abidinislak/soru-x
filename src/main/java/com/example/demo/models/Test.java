package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tId;

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	private String testTitleText;

	@OneToMany
	private List<Question> questions = new ArrayList<>();

	public String getTestTitleText() {
		return testTitleText;
	}

	public void setQuestion(Question question) {
		questions.add(question);
	}

	public void setTestTitleText(String testTitleText) {
		this.testTitleText = testTitleText;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Test() {
		// TODO Auto-generated constructor stub
	}

}
