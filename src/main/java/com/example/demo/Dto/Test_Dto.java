package com.example.demo.Dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.models.Question;

public class Test_Dto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tId;

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	private int testnumber;

	public int getTestnumber() {
		return testnumber;
	}

	public void setTestnumber(int testnumber) {
		this.testnumber = testnumber;
	}

	private String testTitleText;
	private String testAdi;

	public String getTestAdi() {
		return testAdi;
	}

	public void setTestAdi(String testAdi) {
		this.testAdi = testAdi;
	}

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

	public Test_Dto() {
	}
}