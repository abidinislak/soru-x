package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Category;
import com.example.demo.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	@Query(value = "SELECT * FROM sorux.question", nativeQuery = true)
	public List<Question> custom();

	public Question save(Question question);

	public List<Question> findByQtext(String qtext);

	public List<Question> findByCategory(Category category);

	// SELECT a.* FROM sorux.question AS a WHERE a.q_id IN (SELECT b.questions_q_id
	// FROM sorux.test_questions AS b WHERE b.test_t_id=141)

	@Query(value = "SELECT * FROM sorux.question  WHERE q_id IN (SELECT questions_q_id FROM sorux.test_questions WHERE test_t_id=  ?1 )", nativeQuery = true)
	public List<Question> getByTest(int id);

	@Query(value = "SELECT * FROM sorux.question  WHERE q_id not  IN (SELECT questions_q_id FROM sorux.test_questions WHERE test_t_id= ?1 ) and category_c_id = ?2 ", nativeQuery = true)
	public List<Question> questionsListByCategoryWithTest(int testId, int categoryId);

}
