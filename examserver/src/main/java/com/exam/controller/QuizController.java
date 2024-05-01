package com.exam.controller;

import com.exam.model.Category;
import com.exam.model.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.addquiz(quiz));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.updatequiz(quiz));
    }
    @GetMapping("/")
    public ResponseEntity<?> quizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }
    @GetMapping("/{qid}")
    public Quiz quiz(@PathVariable("qid") Long qid){
        return this.quizService.getQuiz(qid);
    }
    @DeleteMapping("/{quizId}")
    public void delete(@PathVariable("quizId") Long quizId){
        this.quizService.deleteQuiz(quizId);
    }

    @GetMapping("/category/{categoryId}")
    public List<Quiz> getQuizByCategory(@PathVariable("categoryId") Long categoryId){
        Category category = new Category();
        category.setCid(categoryId);
        return this.quizService.getQuizzesOfCategory(category);
    }
}
