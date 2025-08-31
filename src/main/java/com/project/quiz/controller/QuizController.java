package com.project.quiz.controller;

import com.project.quiz.model.Quiz;
import com.project.quiz.model.User;
import com.project.quiz.repository.QuizRepository;
import com.project.quiz.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizRepository quizRepo;
    private final UserRepository userRepo;

    public QuizController(QuizRepository quizRepo, UserRepository userRepo) {
        this.quizRepo = quizRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/create/{userId}")
    public Quiz createQuiz(@PathVariable Integer userId, @RequestBody Quiz quiz) {
        Optional<User> user = userRepo.findById(userId);
        user.ifPresent(quiz::setCreatedBy);
        return quizRepo.save(quiz);
    }

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizRepo.findAll();
    }

    @PutMapping("/{quizId}")
    public Quiz updateQuiz(@PathVariable Integer quizId, @RequestBody Quiz quizDetails) {
        return quizRepo.findById(quizId).map(quiz -> {
            quiz.setTitle(quizDetails.getTitle());
            quiz.setDescription(quizDetails.getDescription());
            return quizRepo.save(quiz);
        }).orElse(null);
    }

    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable Integer quizId) {
        quizRepo.deleteById(quizId);
    }

    @GetMapping("/{quizId}")
    public Quiz getQuiz(@PathVariable Integer quizId) {
        return quizRepo.findById(quizId).orElse(null);
    }
}
