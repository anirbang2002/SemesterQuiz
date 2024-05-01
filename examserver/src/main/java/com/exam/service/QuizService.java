package com.exam.service;

import com.exam.model.Category;
import com.exam.model.Quiz;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface QuizService {
    public Quiz addquiz(Quiz quiz);
    public Quiz updatequiz(Quiz quiz);
    public Set<Quiz> getQuizzes();
    public Quiz getQuiz(Long quizId);
    public void deleteQuiz(Long quizId);
    List<Quiz> getQuizzesOfCategory(Category category);
}
