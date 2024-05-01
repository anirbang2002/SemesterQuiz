package com.exam.repo;

import com.exam.model.Category;
import com.exam.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    public List<Quiz> findBycategory(Category category);
}
