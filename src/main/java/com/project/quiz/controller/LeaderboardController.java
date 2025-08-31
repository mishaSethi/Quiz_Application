package com.project.quiz.controller;

import com.project.quiz.service.LeaderboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/quiz/{quizId}")
    public List<Map<String, Object>> getLeaderboard(@PathVariable Integer quizId) {
        return leaderboardService.getLeaderboardByQuiz(quizId);
    }
}
