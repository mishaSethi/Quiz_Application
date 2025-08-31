package com.project.quiz.service;

import com.project.quiz.model.Option;
import com.project.quiz.model.QuizAttempt;
import com.project.quiz.model.UserResponse;
import com.project.quiz.repository.OptionRepository;
import com.project.quiz.repository.QuizAttemptRepository;
import com.project.quiz.repository.UserResponseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizAttemptService {

    private final QuizAttemptRepository attemptRepo;
    private final UserResponseRepository responseRepo;
    private final OptionRepository optionRepo;

    public QuizAttemptService(QuizAttemptRepository attemptRepo, UserResponseRepository responseRepo, OptionRepository optionRepo) {
        this.attemptRepo = attemptRepo;
        this.responseRepo = responseRepo;
        this.optionRepo = optionRepo;
    }

    // Submit quiz with responses and calculate score
    public QuizAttempt submitQuizAttempt(QuizAttempt attempt, List<UserResponse> responses) {
        int score = 0;

        for (UserResponse response : responses) {
            Option selectedOption = optionRepo.findById(response.getSelectedOption().getOptionId()).orElse(null);
            response.setIsCorrect(selectedOption != null && selectedOption.getIsCorrect());
            if (response.getIsCorrect()) score++;
            response.setAttempt(attempt);
            responseRepo.save(response);
        }

        attempt.setScore(score);
        return attemptRepo.save(attempt);
    }
}
