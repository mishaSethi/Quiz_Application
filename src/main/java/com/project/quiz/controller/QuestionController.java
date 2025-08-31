package com.project.quiz.controller;

import com.project.quiz.model.Question;
import com.project.quiz.model.Quiz;
import com.project.quiz.repository.QuestionRepository;
import com.project.quiz.repository.QuizRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionRepository questionRepo;
    private final QuizRepository quizRepo;

    public QuestionController(QuestionRepository questionRepo, QuizRepository quizRepo) {
        this.questionRepo = questionRepo;
        this.quizRepo = quizRepo;
    }

    @PostMapping("/add/{quizId}")
    public Question addQuestion(@PathVariable Integer quizId, @RequestBody Question question) {
        Optional<Quiz> quiz = quizRepo.findById(quizId);
        quiz.ifPresent(question::setQuiz);
        return questionRepo.save(question);
    }

    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsByQuiz(@PathVariable Integer quizId) {
        return questionRepo.findByQuizQuizId(quizId);
    }
}
