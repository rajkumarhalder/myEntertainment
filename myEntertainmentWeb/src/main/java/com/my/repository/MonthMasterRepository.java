package com.my.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.my.model.MonthMaster;

@Repository
public interface MonthMasterRepository extends MongoRepository<MonthMaster, Long>{

}
