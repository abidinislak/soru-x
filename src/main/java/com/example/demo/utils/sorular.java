package com.example.demo.utils;

import java.util.List;

import com.example.demo.models.Answer;
import com.example.demo.models.Question;

public class sorular {

	private int sorusirano;

	private Question soru;

	private List<Answer> cevaplar;

	private List<Integer> kullanıcıcevabıo;

	public int getSorusirano() {
		return sorusirano;
	}

	public void setSorusirano(int sorusirano) {
		this.sorusirano = sorusirano;
	}

	public Question getSoru() {
		return soru;
	}

	public void setSoru(Question soru) {
		this.soru = soru;
	}

	public List<Answer> getCevaplar() {
		return cevaplar;
	}

	public void setCevaplar(List<Answer> cevaplar) {
		this.cevaplar = cevaplar;
	}

	public List<Integer> getKullanıcıcevabıo() {
		return kullanıcıcevabıo;
	}

	public void setKullanıcıcevabıo(List<Integer> kullanıcıcevabıo) {
		this.kullanıcıcevabıo = kullanıcıcevabıo;
	}

}
