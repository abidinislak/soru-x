
package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AnswerRepo;
import com.example.demo.models.Answer;
import com.example.demo.models.Question;

@Service

public class AnswerService {

	@Autowired
	private AnswerRepo Arepo;

	public void save(Answer answer) {

		Arepo.save(answer);
	}

	public List<Answer> findbyQuestion(Question question) {

		return Arepo.findByQuestion(question);
	}

	public void deleteById(Integer id) {
		Arepo.deleteById(id);

	}

	public Optional<Answer> findById(Integer id) {

		return Arepo.findById(id);
	}

}
