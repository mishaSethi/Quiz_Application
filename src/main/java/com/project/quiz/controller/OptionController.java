package com.project.quiz.controller;

import com.project.quiz.model.Option;
import com.project.quiz.model.Question;
import com.project.quiz.repository.OptionRepository;
import com.project.quiz.repository.QuestionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/options")
public class OptionController {

    private final OptionRepository optionRepo;
    private final QuestionRepository questionRepo;

    public OptionController(OptionRepository optionRepo, QuestionRepository questionRepo) {
        this.optionRepo = optionRepo;
        this.questionRepo = questionRepo;
    }

    @PostMapping("/add/{questionId}")
    public Option addOption(@PathVariable Integer questionId, @RequestBody Option option) {
        Optional<Question> question = questionRepo.findById(questionId);
        question.ifPresent(option::setQuestion);
        return optionRepo.save(option);
    }

    @GetMapping("/question/{questionId}")
    public List<Option> getOptionsByQuestion(@PathVariable Integer questionId) {
        return optionRepo.findByQuestionQuestionId(questionId);
    }
}
