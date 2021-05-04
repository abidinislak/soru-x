package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ResultQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int resultId;

	private int question;

	private boolean qisTrue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getQuestion() {
		return question;
	}

	public void setQuestion(int question) {
		this.question = question;
	}

	public boolean isQisTrue() {
		return qisTrue;
	}

	public void setQisTrue(boolean qisTrue) {
		this.qisTrue = qisTrue;
	}

}
