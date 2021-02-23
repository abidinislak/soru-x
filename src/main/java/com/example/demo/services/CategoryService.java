package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryRepo;
import com.example.demo.models.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepo Crepo;

	public void Save(Category category) {
		Crepo.save(category);
	}

	public void deleteById(Integer id) {
		Crepo.deleteById(id);

	}

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return Crepo.findAll();
	}

	public Optional<Category> findById(int id) {
		return Crepo.findById(id);
	}

}
