package com.project.quiz.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_response")
public class UserResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer responseId;

    @ManyToOne
    @JoinColumn(name = "attempt_id")
    private QuizAttempt attempt;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "selected_option_id")
    private Option selectedOption;

    private Boolean isCorrect;
    private LocalDateTime answeredAt = LocalDateTime.now();

    public UserResponse() {}
    public UserResponse(Integer responseId, QuizAttempt attempt, Question question, Option selectedOption, Boolean isCorrect, LocalDateTime answeredAt) {
        this.responseId = responseId;
        this.attempt = attempt;
        this.question = question;
        this.selectedOption = selectedOption;
        this.isCorrect = isCorrect;
        this.answeredAt = answeredAt;
    }

    // Getters and Setters
    public Integer getResponseId() { return responseId; }
    public void setResponseId(Integer responseId) { this.responseId = responseId; }
    public QuizAttempt getAttempt() { return attempt; }
    public void setAttempt(QuizAttempt attempt) { this.attempt = attempt; }
    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
    public Option getSelectedOption() { return selectedOption; }
    public void setSelectedOption(Option selectedOption) { this.selectedOption = selectedOption; }
    public Boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; }
    public LocalDateTime getAnsweredAt() { return answeredAt; }
    public void setAnsweredAt(LocalDateTime answeredAt) { this.answeredAt = answeredAt; }
}
