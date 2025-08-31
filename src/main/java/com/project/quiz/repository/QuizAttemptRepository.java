package com.project.quiz.repository;

import com.project.quiz.model.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Integer> {
    List<QuizAttempt> findByUserUserId(Integer userId); // Get attempts by user
    List<QuizAttempt> findByQuizQuizId(Integer quizId); // Get attempts by quiz
}
