package com.gmail.deamon999.controllers;


import com.gmail.deamon999.entities.Category;
import com.gmail.deamon999.servicies.CategoryService;
import com.gmail.deamon999.servicies.CustomUsersService;
import com.gmail.deamon999.servicies.MessageService;
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
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomUsersService customUsersService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/")
    public String home(Model model) {
        List<Category> categories = categoryService.findAll();
        long categoryCount = categoryService.categoryCount();
        long userCount = customUsersService.userCount();
        long messageCount = messageService.messageCount();
        long subjectCount = subjectService.subjectCount();
        model.addAttribute("category", categories);
        model.addAttribute("categoryCount", categoryCount);
        model.addAttribute("userCount", userCount);
        model.addAttribute("messageCount", messageCount);
        model.addAttribute("subjectCount", subjectCount);
        return "category";
    }

    @RequestMapping("/createCategory")
    public String newCategory(Model model) {
        return "createCategory";
    }

    @RequestMapping(value = "/newCategory", method = RequestMethod.POST)
    public String addCategory(@RequestParam String name) {
        categoryService.addCategory(new Category(name));
        return "redirect:/";
    }

    @RequestMapping(value = "/category/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            categoryService.deleteCategory(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
