package com.exam.service;

import com.exam.model.Category;

import java.util.Set;

public interface CategoryService {
    public Category addcategory(Category category);
    public Category updatecatgeory(Category category);
    public Set<Category> getCategories();
    public Category getCategory(Long categoryId);
    public void deleteCategory(Long categoryId);
}
