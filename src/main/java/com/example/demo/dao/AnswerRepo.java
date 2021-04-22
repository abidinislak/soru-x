package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Answer;
import com.example.demo.models.Question;

public interface AnswerRepo extends JpaRepository<Answer, Integer> {

	public Answer save(Answer answer);

	public List<Answer> findByQuestion(Question question);
	
	@Query(value = "SELECT id FROM sorux.answer where true_mu=true and question_q_id = ?1", nativeQuery = true)
	public List<Integer> trueAnswers(int soruid);
	
	
	

}
