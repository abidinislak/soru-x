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
	
	
	
}
