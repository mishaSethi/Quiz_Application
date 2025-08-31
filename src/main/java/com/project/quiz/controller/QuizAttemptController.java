package com.project.quiz.controller;

import com.project.quiz.model.*;
import com.project.quiz.repository.*;
import com.project.quiz.service.QuizAttemptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attempts")
public class QuizAttemptController {

    private final QuizAttemptRepository attemptRepo;
    private final UserRepository userRepo;
    private final QuizRepository quizRepo;
    private final QuizAttemptService attemptService;

    public QuizAttemptController(QuizAttemptRepository attemptRepo, UserRepository userRepo,
                                 QuizRepository quizRepo, QuizAttemptService attemptService) {
        this.attemptRepo = attemptRepo;
        this.userRepo = userRepo;
        this.quizRepo = quizRepo;
        this.attemptService = attemptService;
    }

    @PostMapping("/start/{userId}/{quizId}")
    public QuizAttempt startQuiz(@PathVariable Integer userId, @PathVariable Integer quizId) {
        QuizAttempt attempt = new QuizAttempt();
        userRepo.findById(userId).ifPresent(attempt::setUser);
        quizRepo.findById(quizId).ifPresent(attempt::setQuiz);
        return attemptRepo.save(attempt);
    }

    @PostMapping("/submit/{attemptId}")
    public QuizAttempt submitQuiz(@PathVariable Integer attemptId, @RequestBody List<UserResponse> responses) {
        Optional<QuizAttempt> attemptOpt = attemptRepo.findById(attemptId);
        if (attemptOpt.isPresent()) {
            QuizAttempt attempt = attemptOpt.get();
            attempt.setCompletedAt(java.time.LocalDateTime.now());
            return attemptService.submitQuizAttempt(attempt, responses);
        }
        return null;
    }
}
