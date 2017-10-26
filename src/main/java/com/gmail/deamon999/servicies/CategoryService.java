package com.gmail.deamon999.servicies;


import com.gmail.deamon999.entities.Category;
import com.gmail.deamon999.reposotories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public long categoryCount() {
        return categoryRepository.count();
    }

    @Transactional
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(long id) {
        categoryRepository.delete(id);
    }

    @Transactional
    public void renameCategory(long id, String newName) {
        Category category = categoryRepository.findOne(id);
        category.setName(newName);
        categoryRepository.save(category);
    }

    @Transactional
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void deleteCategory(long[] idList) {
        for (long id : idList)
            categoryRepository.delete(id);
    }

    @Transactional
    public Category findOne(long id) {
        return categoryRepository.findOne(id);
    }
}
