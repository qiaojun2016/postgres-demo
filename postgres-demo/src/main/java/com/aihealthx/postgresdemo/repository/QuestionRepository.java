package com.aihealthx.postgresdemo.repository;

import com.aihealthx.postgresdemo.model.QuestionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionModel,Long> {
}
