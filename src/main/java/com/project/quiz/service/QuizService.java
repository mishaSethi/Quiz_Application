package com.project.quiz.service;

import com.project.quiz.model.Quiz;
import com.project.quiz.model.User;
import com.project.quiz.repository.QuizRepository;
import com.project.quiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepo;
    private final UserRepository userRepo;

    public QuizService(QuizRepository quizRepo, UserRepository userRepo) {
        this.quizRepo = quizRepo;
        this.userRepo = userRepo;
    }

    public Quiz createQuiz(Integer userId, Quiz quiz) {
        Optional<User> user = userRepo.findById(userId);
        user.ifPresent(quiz::setCreatedBy);
        return quizRepo.save(quiz);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepo.findAll();
    }

    public Optional<Quiz> getQuizById(Integer quizId) {
        return quizRepo.findById(quizId);
    }
}
