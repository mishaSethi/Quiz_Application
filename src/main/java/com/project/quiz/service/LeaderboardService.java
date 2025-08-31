package com.project.quiz.service;

import com.project.quiz.model.QuizAttempt;
import com.project.quiz.model.User;
import com.project.quiz.repository.QuizAttemptRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LeaderboardService {

    private final QuizAttemptRepository attemptRepo;

    public LeaderboardService(QuizAttemptRepository attemptRepo) {
        this.attemptRepo = attemptRepo;
    }

    public List<Map<String, Object>> getLeaderboardByQuiz(Integer quizId) {
        // Get all attempts for the quiz
        List<QuizAttempt> attempts = attemptRepo.findByQuizQuizId(quizId);

        // Group attempts by user
        Map<User, List<QuizAttempt>> grouped = attempts.stream()
                .collect(Collectors.groupingBy(QuizAttempt::getUser));

        List<Map<String, Object>> leaderboard = new ArrayList<>();

        for (Map.Entry<User, List<QuizAttempt>> entry : grouped.entrySet()) {
            User user = entry.getKey();
            List<QuizAttempt> userAttempts = entry.getValue();

            int bestScore = userAttempts.stream().mapToInt(QuizAttempt::getScore).max().orElse(0);
            double avgScore = userAttempts.stream().mapToInt(QuizAttempt::getScore).average().orElse(0.0);
            int totalAttempts = userAttempts.size();

            Map<String, Object> userStats = new HashMap<>();
            userStats.put("userId", user.getUserId());
            userStats.put("username", user.getUsername());
            userStats.put("bestScore", bestScore);
            userStats.put("avgScore", Math.round(avgScore));
            userStats.put("attempts", totalAttempts);

            leaderboard.add(userStats);
        }

        // Sort by best score descending
        leaderboard.sort((a, b) -> ((Integer) b.get("bestScore")).compareTo((Integer) a.get("bestScore")));

        return leaderboard;
    }
}
