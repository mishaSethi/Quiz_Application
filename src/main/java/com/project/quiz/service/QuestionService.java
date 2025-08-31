package com.project.quiz.service;

import com.project.quiz.model.Question;
import com.project.quiz.model.Quiz;
import com.project.quiz.repository.QuestionRepository;
import com.project.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepo;
    private final QuizRepository quizRepo;

    public QuestionService(QuestionRepository questionRepo, QuizRepository quizRepo) {
        this.questionRepo = questionRepo;
        this.quizRepo = quizRepo;
    }

    public Question addQuestion(Integer quizId, Question question) {
        Optional<Quiz> quiz = quizRepo.findById(quizId);
        quiz.ifPresent(question::setQuiz);
        return questionRepo.save(question);
    }

    public List<Question> getQuestionsByQuiz(Integer quizId) {
        return questionRepo.findByQuizQuizId(quizId);
    }
}
