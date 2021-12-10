package com.example.springdatajdbcdemo.repositories;

import com.example.springdatajdbcdemo.models.UserOptimistic;
import com.example.springdatajdbcdemo.models.UserPessimistic;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersPessimisticRepository extends CrudRepository<UserPessimistic, Long> {

    List<UserPessimistic> findAll();

    @Query("SELECT * FROM USERS_PESS WHERE id = :id FOR UPDATE")
    UserPessimistic findUserByIdForUpdate(Long id);
}
