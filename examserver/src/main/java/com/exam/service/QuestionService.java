package com.exam.service;

import com.exam.model.Question;
import com.exam.model.Quiz;

import java.util.Set;

public interface QuestionService {
    public Question addQuestion(Question question);
    public Question updateQuestion(Question question);
    public Set<Question> getQuestions();
    public Question getQuestion(Long questionId);
    public Set<Question> getQuestionsofQuiz(Quiz quiz);
    public void deleteQuestion(Long qid);
    public Question get(Long questionId);

}
