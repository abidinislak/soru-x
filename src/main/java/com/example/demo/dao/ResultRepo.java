package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Result;

public interface ResultRepo   extends JpaRepository<Result, Integer> {

}
