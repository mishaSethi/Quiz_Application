package com.project.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer optionId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    @Column(nullable = false)
    private String optionText;

    private Boolean isCorrect = false;

    public Option() {}
    public Option(Integer optionId, Question question, String optionText, Boolean isCorrect) {
        this.optionId = optionId;
        this.question = question;
        this.optionText = optionText;
        this.isCorrect = isCorrect;
    }

    // Getters and Setters
    public Integer getOptionId() { return optionId; }
    public void setOptionId(Integer optionId) { this.optionId = optionId; }
    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }
    public String getOptionText() { return optionText; }
    public void setOptionText(String optionText) { this.optionText = optionText; }
    public Boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; }
}
