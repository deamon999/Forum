package com.gmail.deamon999.controllers;



import com.gmail.deamon999.entities.Category;
import com.gmail.deamon999.entities.Subject;
import com.gmail.deamon999.servicies.CategoryService;
import com.gmail.deamon999.servicies.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/subject")
    public String subject(@RequestParam long id, Model model) {
        Category category = categoryService.findOne(id);
        List<Subject> subjects = subjectService.findByCategory(category);
        model.addAttribute("subject", subjects);
        model.addAttribute("category", category);
        return "subject";
    }

    @RequestMapping("/newSubject")
    public String addSubject(@RequestParam String name, @RequestParam long id, Model model) {
        Category category = categoryService.findOne(id);
        subjectService.addSubject(new Subject(name, category));
        return "redirect:/subject" + "?id=" + id;
    }

    @RequestMapping("/createSubject")
    public String createSubject(@RequestParam long id, Model model) {
        model.addAttribute("id", id);
        return "createSubject";
    }

    @RequestMapping(value = "/subject/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            subjectService.deleteSubject(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
