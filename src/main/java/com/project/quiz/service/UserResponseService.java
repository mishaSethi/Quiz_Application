package com.project.quiz.service;

import com.project.quiz.model.UserResponse;
import com.project.quiz.repository.UserResponseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserResponseService {

    private final UserResponseRepository responseRepo;

    public UserResponseService(UserResponseRepository responseRepo) {
        this.responseRepo = responseRepo;
    }

    public List<UserResponse> getResponsesByAttempt(Integer attemptId) {
        return responseRepo.findByAttemptAttemptId(attemptId);
    }
}
