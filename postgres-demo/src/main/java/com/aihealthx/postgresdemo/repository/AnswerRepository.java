package com.aihealthx.postgresdemo.repository;

import com.aihealthx.postgresdemo.model.AnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerModel,Long> {
    List<AnswerModel> findByQuestionId(Long questionId);
}
