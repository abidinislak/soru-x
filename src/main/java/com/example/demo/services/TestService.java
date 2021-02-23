package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TestRepo;
import com.example.demo.models.Test;

@Service
public class TestService {
	@Autowired
	private TestRepo testRepo;

	public void save(Test test) {

		testRepo.save(test);

	}

	public List<Test> findAll() {
		return testRepo.findAll();
	}

	public Test findById(Integer id) {

		Optional<Test> testEdit = testRepo.findById(id);
		Test test = null;
		if (testEdit.isPresent()) {

			test = testEdit.get();
		} else {

			throw new RuntimeException("uupps.. somtrihi, wrong");
		}

		return test;

	}

	public void deleteById(int id) {

		testRepo.deleteById(id);
	}

}
