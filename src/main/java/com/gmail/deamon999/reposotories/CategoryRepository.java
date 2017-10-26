package com.gmail.deamon999.reposotories;


import com.gmail.deamon999.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
