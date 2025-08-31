package com.project.quiz.service;

import com.project.quiz.model.Option;
import com.project.quiz.model.Question;
import com.project.quiz.repository.OptionRepository;
import com.project.quiz.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionService {

    private final OptionRepository optionRepo;
    private final QuestionRepository questionRepo;

    public OptionService(OptionRepository optionRepo, QuestionRepository questionRepo) {
        this.optionRepo = optionRepo;
        this.questionRepo = questionRepo;
    }

    public Option addOption(Integer questionId, Option option) {
        Optional<Question> question = questionRepo.findById(questionId);
        question.ifPresent(option::setQuestion);
        return optionRepo.save(option);
    }

    public List<Option> getOptionsByQuestion(Integer questionId) {
        return optionRepo.findByQuestionQuestionId(questionId);
    }
}
