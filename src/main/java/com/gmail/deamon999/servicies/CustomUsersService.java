package com.gmail.deamon999.servicies;


import com.gmail.deamon999.entities.CustomUser;
import com.gmail.deamon999.entities.Role;
import com.gmail.deamon999.reposotories.CustomUsersRepository;
import com.gmail.deamon999.reposotories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;


@Service
public class CustomUsersService {
    @Autowired
    private CustomUsersRepository usersRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Transactional
    public long userCount() {
        return usersRepository.count();
    }

    @Transactional(readOnly = true)
    public CustomUser getUserByLogin(String login) {
        return usersRepository.findByLogin(login);
    }

    @Transactional(readOnly = true)
    public boolean existsByLogin(String login) {
        return usersRepository.existsByLogin(login);
    }

    @Transactional
    public void addUser(CustomUser customCustomUser) {
        usersRepository.save(customCustomUser);
    }

    @Transactional
    public void updateUser(long id, String newName, String newPassword) {
        CustomUser customUser = usersRepository.findOne(id);
        customUser.setLogin(newName);
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String passHash = encoder.encodePassword(newPassword, null);
        customUser.setPassword(passHash);
        usersRepository.save(customUser);
    }

    @Transactional
    public CustomUser findOne(long id) {
        return usersRepository.findOne(id);
    }

    @Transactional
    public void getByLoginAndUpdate(String login, String email, String phone) {
        CustomUser customCustomUser = usersRepository.findByLogin(login);
        customCustomUser.setEmail(email);
        customCustomUser.setPhone(phone);
        usersRepository.save(customCustomUser);
    }


    @Transactional
    public String addUserIfNotExists(String login, String password, String email, String phone, Model model) {
        if (usersRepository.existsByLogin(login)) {
            model.addAttribute("exists", true);
            return "register";
        }
        Role role = roleRepository.findByRole("USER");
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String passHash = encoder.encodePassword(password, null);
        CustomUser customCustomUser = new CustomUser(login, passHash, role, email, phone);
        usersRepository.save(customCustomUser);

        return "redirect:/";
    }

    @Transactional
    public List<CustomUser> getAllUsers() {
        return usersRepository.findAll();
    }

    @Transactional
    public void deleteUser(long id[]) {
        for (long i : id) {
            usersRepository.delete(i);
        }
    }


}