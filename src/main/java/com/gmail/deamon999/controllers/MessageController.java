package com.gmail.deamon999.controllers;

import com.gmail.deamon999.entities.Message;
import com.gmail.deamon999.entities.Subject;
import com.gmail.deamon999.servicies.MessageService;
import com.gmail.deamon999.servicies.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private SubjectService subjectService;

    static final int ITEMS_PER_PAGE = 3;

    private static int pageDefault = 0;

    private long getPageCount() {
        long totalCount = messageService.count();
        return (totalCount / ITEMS_PER_PAGE) + ((totalCount % ITEMS_PER_PAGE > 0) ? 1 : 0);
    }

    @RequestMapping("/next/")
    public String next(@RequestParam long id) {
        pageDefault++;
        return "redirect:/message" + "?id=" + id;
    }

    @RequestMapping("/previous/")
    public String previous(@RequestParam long id) {
        pageDefault--;
        return "redirect:/message" + "?id=" + id;
    }

    @RequestMapping("/message")
    public String message(@RequestParam long id, Model model) {
        Subject subject = subjectService.findOne(id);

        if (pageDefault > getPageCount()) {
            pageDefault = (int) getPageCount();
        }
        if (pageDefault < 0) pageDefault = 0;

        List<Message> messages = messageService.findBysubject(subject, new PageRequest(pageDefault, ITEMS_PER_PAGE, Sort.Direction.DESC, "id"));
        model.addAttribute("message", messages);
        model.addAttribute("subject", subject);
        return "message";
    }

    @RequestMapping("/createMessage")
    public String createSubject(@RequestParam long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        model.addAttribute("login", login);
        model.addAttribute("id", id);
        return "createMessage";
    }

    @RequestMapping("/newMessage")
    public String newMessage(@RequestParam String text, @RequestParam String login, @RequestParam long id, Model model) {
        Date date = new Date();
        Subject subject = subjectService.findOne(id);
        Message message = new Message(text, login, date.toString(), subject);
        messageService.addMessage(message);
        return "redirect:/message" + "?id=" + id;
    }

    @RequestMapping(value = "/message/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            messageService.deleteMessage(toDelete);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
