package com.example.demo.utils;

import java.util.List;

import com.example.demo.models.Test;

public class Deneme {

	private Test test;
	
	private int [] sayi;

	public int[] getSayi() {
		return sayi;
	}

	public void setSayi(int[] sayi) {
		this.sayi = sayi;
	}

	private List<sorular> sorular;

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public List<sorular> getSorular() {
		return sorular;
	}

	public void setSorular(List<sorular> sorular) {
		this.sorular = sorular;
	}

}
