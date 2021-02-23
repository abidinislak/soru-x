package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionRepository;
import com.example.demo.models.Category;
import com.example.demo.models.Question;

@Service
public class QuestionService {

	public List<Question> questionsListByCategoryWithTest(int testId, int categoryId) {

		return qrepo.questionsListByCategoryWithTest(testId, categoryId);

	}

	public List<Question> getByTest(int id) {

		return qrepo.getByTest(id);
	}

	public List<Question> custom() {

		return qrepo.custom();
	}

	public void save(Question question) {

		qrepo.save(question);
	}

	public Optional<Question> findById(Integer id) {

		return qrepo.findById(id);
	}

	public void deleteById(int id) {

		qrepo.deleteById(id);
	}

	public List<Question> findAll() {

		return qrepo.findAll();
	}

	@Autowired
	QuestionRepository qrepo;

	public List<Question> findByCategory(Category category) {

		return qrepo.findByCategory(category);
	}

	public List<Question> findByQtext(String qtext) {

		return qrepo.findByQtext(qtext);
	}

	public Question getQuestionByid(Integer id) {

		Optional<Question> questionEdit = qrepo.findById(id);
		Question question = null;
		if (questionEdit.isPresent()) {

			question = questionEdit.get();
		} else {

			throw new RuntimeException("uupps.. somtrihi, wrong");
		}

		return question;

	}

}
