package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

@Entity
@Validated
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;

	@NotNull
	@NotEmpty(message = "Categor isim kısmı boş olamaz kısmı boş olamaz")
	private String cText;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcText() {
		return cText;
	}

	public void setcText(String cText) {
		this.cText = cText;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return getcText();
	}

}
