package com.my.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.my.model.Expanditur;

public interface ExpanditurRepository extends MongoRepository<Expanditur, Long>{

}
