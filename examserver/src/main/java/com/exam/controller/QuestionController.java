package com.exam.controller;

import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;


    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody  Question question){
        System.out.println(question);
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody  Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestions(@PathVariable("qid") Long qid){
//        Quiz quiz = new Quiz();
//        quiz.setQid(qid);
//        Set<Question> questionsofQuiz = this.questionService.getQuestionsofQuiz(quiz);
//        return ResponseEntity.ok(questionsofQuiz);
        Quiz quiz = this.quizService.getQuiz(qid);  
        Set<Question> questions = quiz.getQuestions();
        for(Question q:questions){
            q.setAnswer(null);
        }
        List list = new ArrayList(questions);
        if(list.size()>Integer.parseInt(quiz.getNumberofQuestions())){
            list = list.subList(0,Integer.parseInt(quiz.getNumberofQuestions()+1));
        }
        Collections.shuffle(list);
//        list.forEach((l)->{
//            System.out.println(l.g);
//        });
        return ResponseEntity.ok(list);
    }
    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsAll(@PathVariable("qid") Long qid){
//        Quiz quiz = new Quiz();
//        quiz.setQid(qid);
//        Set<Question> questionsofQuiz = this.questionService.getQuestionsofQuiz(quiz);
//        return ResponseEntity.ok(questionsofQuiz);
        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();  
        List list = new ArrayList(questions);
//        if(list.size()>Integer.parseInt(quiz.getNumberofQuestions())){
//            list = list.subList(0,Integer.parseInt(quiz.getNumberofQuestions()+1));
//        }
//        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") Long quesId){
        return this.questionService.getQuestion(quesId);
    }

    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> EvalQuiz(@RequestBody List<Question> questions){
        System.out.println(questions);
        Double getMarks= (double) 0;
        Integer Corrected=0;
        Integer Attempted=0;
        for(Question q:questions){
            System.out.println(q.getGivenAnswer());
            Question q1 = this.questionService.get(q.getQuesId());
            if(q1.getAnswer().equals(q.getGivenAnswer())){
                Corrected++;
                double marks = Double.parseDouble(q.getQuiz().getMaxMarks())/(questions.size());
                getMarks+=marks;
            }
            if(q.getGivenAnswer()!=null && !(q.getGivenAnswer().trim().equals(""))){
                Attempted++;
            }
        };
        Map<String, Object> m = Map.of("marksGot",getMarks,"correctAnswer",Corrected,"attempted",Attempted);
        return ResponseEntity.ok(m);
    }
}
