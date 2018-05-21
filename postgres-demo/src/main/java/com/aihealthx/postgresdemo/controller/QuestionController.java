package com.aihealthx.postgresdemo.controller;

import com.aihealthx.postgresdemo.exception.ResourceNotFoundException;
import com.aihealthx.postgresdemo.model.QuestionModel;
import com.aihealthx.postgresdemo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions")
    public Page<QuestionModel> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @PostMapping("/questions")
    public QuestionModel createQuestion(@Valid @RequestBody QuestionModel questionModel) {
        return questionRepository.save(questionModel);
    }

    @PutMapping("/questions/{questionId}")
    public QuestionModel updateQuestion(@PathVariable Long questionId, @Valid @RequestBody QuestionModel questionRequest) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    question.setTitle(questionRequest.getTitle());
                    question.setDescription(questionRequest.getDescription());
                    return questionRepository.save(question);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        return questionRepository.findById(questionId)
                .map(questionModel -> {
                    questionRepository.delete(questionModel);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
    }
}
