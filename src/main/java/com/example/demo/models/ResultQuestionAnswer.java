package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ResultQuestionAnswer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	private int resultQuestionId;
	
	private int answerId;
	
	private boolean atrue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResultQuestionId() {
		return resultQuestionId;
	}

	public void setResultQuestionId(int resultQuestionId) {
		this.resultQuestionId = resultQuestionId;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public boolean isAtrue() {
		return atrue;
	}

	public void setAtrue(boolean atrue) {
		this.atrue = atrue;
	}
	
	
	
}
