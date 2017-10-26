package com.gmail.deamon999.controllers;

import com.gmail.deamon999.entities.CustomUser;
import com.gmail.deamon999.entities.PrivateMessage;
import com.gmail.deamon999.entities.Role;
import com.gmail.deamon999.entities.Subject;
import com.gmail.deamon999.servicies.CustomUsersService;
import com.gmail.deamon999.servicies.PrivateMessageService;
import com.gmail.deamon999.servicies.RoleService;
import com.gmail.deamon999.servicies.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MyController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private CustomUsersService customUsersService;

    @Autowired
    private PrivateMessageService privateMessageService;
    @Autowired
    private RoleService roleService;


    @RequestMapping("/search")
    public String search(@RequestParam String pattern, Model model) {
        List<Subject> subject = subjectService.findByPattern(pattern);
        model.addAttribute("subject", subject);
        return "search";
    }

    @RequestMapping("/cabinet")
    public String cabinet(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        CustomUser customUser = customUsersService.getUserByLogin(login);
        List<PrivateMessage> message = privateMessageService.findByRecipient(login);
        model.addAttribute("customUser", customUser);
        model.addAttribute("login", login);
        model.addAttribute("message", message);
        return "cabinet";
    }


    @RequestMapping("/roleAdmin")
    public String roleAdmin(@RequestParam long id, Model model) {
        Role admin = roleService.findByName(new String("ADMIN"));
        CustomUser customUser = customUsersService.findOne(id);
        customUser.setRole(admin);
        customUsersService.addUser(customUser);
        return "redirect:/userDetails";
    }

    @RequestMapping("/roleUser")
    public String roleUser(@RequestParam long id, Model model) {
        Role user = roleService.findByName(new String("USER"));
        CustomUser customUser = customUsersService.findOne(id);
        customUser.setRole(user);
        customUsersService.addUser(customUser);
        return "redirect:/userDetails";
    }

    @RequestMapping("/userDetails")
    public String userDetails(Model model) {
        List<CustomUser> list = customUsersService.getAllUsers();
        model.addAttribute("list", list);
        return "userDetails";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @RequestMapping(value = "/newuser", method = RequestMethod.POST)
    public String update(@RequestParam String login,
                         @RequestParam String password,
                         @RequestParam(required = false) String email,
                         @RequestParam(required = false) String phone,
                         Model model) {

        return customUsersService.addUserIfNotExists(login, password, email, phone, model);
    }

    @RequestMapping("/change")
    public String change(@RequestParam String login, Model model) {
        model.addAttribute("login", login);
        return "change";
    }

    @RequestMapping("/details")
    public String change(@RequestParam long id, Model model) {
        model.addAttribute("id", id);
        return "details";
    }

    @RequestMapping("/changeEmail")
    public String changeEmail(@RequestParam String login, @RequestParam String email, @RequestParam String phone, Model model) {
        customUsersService.getByLoginAndUpdate(login, email, phone);
        CustomUser customUser = customUsersService.getUserByLogin(login);
        model.addAttribute("customUser", customUser);
        model.addAttribute("login", login);
        return "redirect:/cabinet";
    }

    @RequestMapping("/changeDetails")
    public String changeDetails(@RequestParam long id, @RequestParam String login, @RequestParam String password, Model model) {
        customUsersService.updateUser(id, login, password);
        return "redirect:/logout";
    }

    @RequestMapping(value = "/privateMessage/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete) {
        if (toDelete != null && toDelete.length > 0)
            privateMessageService.deleteMessage(toDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping("/createPrivateMessage")
    public String createPrivateMessage(@RequestParam String recipientName, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        model.addAttribute("login", login);
        model.addAttribute("recipientName", recipientName);
        return "createPrivateMessage";
    }

    @RequestMapping("/newPrivateMessage")
    public String newPrivateMessage(@RequestParam String text, @RequestParam String subject, @RequestParam String login, @RequestParam String recipientName, Model model) {
        Date date = new Date();
        PrivateMessage message = new PrivateMessage(recipientName, login, subject, text, date.toString());
        privateMessageService.addMessage(message);
        return "redirect:/cabinet";
    }


    @RequestMapping("/createPrMessage")
    public String createPrMessage(@RequestParam String recipientName, @RequestParam long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        model.addAttribute("login", login);
        model.addAttribute("recipientName", recipientName);
        model.addAttribute("subjectId", id);
        return "createPrMessage";
    }

    @RequestMapping("/newPrMessage")
    public String newPrMessage(@RequestParam String text, @RequestParam String subject, @RequestParam String login, @RequestParam String recipientName, @RequestParam long subjectId, Model model) {
        Date date = new Date();
        PrivateMessage message = new PrivateMessage(recipientName, login, subject, text, date.toString());
        privateMessageService.addMessage(message);
        return "redirect:/message/" + "?id=" + subjectId;
    }


}
