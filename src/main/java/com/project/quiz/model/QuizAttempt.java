package com.project.quiz.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_attempt")
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attemptId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private LocalDateTime startedAt = LocalDateTime.now();
    private LocalDateTime completedAt;
    private Integer score = 0;

    public QuizAttempt() {}
    public QuizAttempt(Integer attemptId, User user, Quiz quiz, LocalDateTime startedAt, LocalDateTime completedAt, Integer score) {
        this.attemptId = attemptId;
        this.user = user;
        this.quiz = quiz;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.score = score;
    }

    // Getters and Setters
    public Integer getAttemptId() { return attemptId; }
    public void setAttemptId(Integer attemptId) { this.attemptId = attemptId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }
    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
}
