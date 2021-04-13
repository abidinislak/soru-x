package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Answer;
import com.example.demo.models.Question;

public interface AnswerRepo extends JpaRepository<Answer, Integer> {

	public Answer save(Answer answer);

	public List<Answer> findByQuestion(Question question);

}
