package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Test;

public interface TestRepo extends JpaRepository<Test, Integer> {

	
//	SELECT c_text FROM sorux.category where c_id in (select category_c_id from sorux.question where q_id in (SELECT questions_q_id FROM sorux.test_questions where test_t_id=3  ));
//	@Query("SELECT u FROM User u WHERE u.status = 1")
//	public List<Question> findAllActiveUsers();
	
	
	@Query(value = "SELECT c_text FROM sorux.category where c_id in (select category_c_id from sorux.question where q_id in (SELECT questions_q_id FROM sorux.test_questions where test_t_id= ?1  ))", nativeQuery = true)
	public List<String> categorytextBytest(int testid);
	
	

}
