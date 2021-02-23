package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Test;

public interface TestRepo extends JpaRepository<Test, Integer> {

//	@Query("SELECT u FROM User u WHERE u.status = 1")
//	public List<Question> findAllActiveUsers();

}
