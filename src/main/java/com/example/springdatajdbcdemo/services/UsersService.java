package com.example.springdatajdbcdemo.services;

import com.example.springdatajdbcdemo.models.UserOptimistic;
import com.example.springdatajdbcdemo.models.UserPessimistic;
import com.example.springdatajdbcdemo.repositories.UsersOptimisticRepository;
import com.example.springdatajdbcdemo.repositories.UsersPessimisticRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService {

    private UsersOptimisticRepository optimisticRepository;

    private UsersPessimisticRepository pessimisticRepository;

    public UsersService(UsersOptimisticRepository optimisticRepository, UsersPessimisticRepository pessimisticRepository) {
        this.optimisticRepository = optimisticRepository;
        this.pessimisticRepository = pessimisticRepository;
    }

    @Transactional(readOnly = true)
    public List<UserOptimistic> findAll() {

        List<UserOptimistic> userOptimistics = this.optimisticRepository.findAll();
        return userOptimistics;
    }

    @Transactional(readOnly = false, rollbackFor = { Exception.class })
    public UserOptimistic save(UserOptimistic userOptimistic) {

        UserOptimistic savedUserOptimistic = this.optimisticRepository.save(userOptimistic);
        return savedUserOptimistic;
    }

    @Transactional(readOnly = false, rollbackFor = { Exception.class })
    public UserPessimistic save(UserPessimistic userPessimistic) {

        UserPessimistic savedUserPessimistic = this.pessimisticRepository.save(userPessimistic);
        return savedUserPessimistic;
    }

    @Transactional(readOnly = false, rollbackFor = { Exception.class }, timeout = 20)
    public UserPessimistic updatePessimistic(UserPessimistic userPessimistic, Long delay) throws InterruptedException {

        if (null == userPessimistic && null == userPessimistic.getId())
            throw new RuntimeException("Not an update attempt");

        this.pessimisticRepository.findUserByIdForUpdate(userPessimistic.getId());

        // The idea is to make the lock and try to attempt a new update a little bit later to see if
        // it gets locked
        Thread.sleep(delay);

        UserPessimistic updatedUserPessimistic = this.pessimisticRepository.save(userPessimistic);
        return updatedUserPessimistic;
    }
}
