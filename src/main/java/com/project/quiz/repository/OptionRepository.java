package com.project.quiz.repository;

import com.project.quiz.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
    List<Option> findByQuestionQuestionId(Integer questionId); // Get options of a question
}
