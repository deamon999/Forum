package com.gmail.deamon999.servicies;


import com.gmail.deamon999.entities.Category;
import com.gmail.deamon999.entities.Subject;
import com.gmail.deamon999.reposotories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Transactional
    public long subjectCount() {
        return subjectRepository.count();
    }

    @Transactional
    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    @Transactional
    public void deleteSubject(long id) {
        subjectRepository.delete(id);
    }

    @Transactional
    public void renameSubject(long id, String newName) {
        Subject subject = subjectRepository.findOne(id);
        subject.setName(newName);
        subjectRepository.save(subject);
    }

    @Transactional
    public void changeCategory(long id, Category category) {
        Subject subject = subjectRepository.findOne(id);
        subject.setCategory(category);
        subjectRepository.save(subject);
    }

    @Transactional
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Transactional
    public List<Subject> findByCategory(Category category) {
        return subjectRepository.findByCategory(category);
    }

    @Transactional
    public void deleteSubject(long[] idList) {
        for (long id : idList)
            subjectRepository.delete(id);
    }

    @Transactional
    public Subject findOne(long id) {
        return subjectRepository.findOne(id);
    }

    @Transactional
    public List<Subject> findByPattern(String pattern) {
        return subjectRepository.findByPattern(pattern);
    }
}
