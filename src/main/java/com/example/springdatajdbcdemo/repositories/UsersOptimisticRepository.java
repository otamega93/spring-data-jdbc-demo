package com.example.springdatajdbcdemo.repositories;

import com.example.springdatajdbcdemo.models.UserOptimistic;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersOptimisticRepository extends CrudRepository<UserOptimistic, Integer> {

    List<UserOptimistic> findAll();

}
