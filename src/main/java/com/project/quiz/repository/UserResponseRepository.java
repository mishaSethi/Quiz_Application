package com.project.quiz.repository;

import com.project.quiz.model.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Integer> {
    List<UserResponse> findByAttemptAttemptId(Integer attemptId); // Responses of a quiz attempt
    List<UserResponse> findByQuestionQuestionId(Integer questionId); // Responses of a question
}
