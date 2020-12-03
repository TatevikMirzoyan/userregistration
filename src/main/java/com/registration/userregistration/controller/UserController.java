package com.registration.userregistration.controller;

import com.registration.userregistration.model.User;
import com.registration.userregistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Tatevik Mirzoyan
 * Created on 03-Dec-20
 */
@Controller
public class UserController {
    @Autowired
    UserService service;

    @GetMapping(path = "/users")
    public String getAllUsers(Model model) {
        List<User> users = service.getAll();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping(path = "/addUser")
    public String showAddPage(User user, Model model) {
        model.addAttribute("user", user);
        return "add_user";
    }

    @GetMapping(path = "/get/{id}")
    public String getUserById(@Valid @PathVariable long id, Model model) {
        try{
            User user = service.getById(id);
            model.addAttribute("user", user);
            return "user_info";
        }catch (IllegalArgumentException e) {
            //todo     immediately redirects to the next page, the exception message is not shown,
            //todo     it is visible only in the IDEA Console
            System.out.println(e.getMessage());
            return "redirect:/users";
        }
    }

    @PostMapping(path = "/add")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "add_user";
        } else {
            service.addUser(user);
            return "redirect:/users";
        }
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User user = service.getById(id);
        service.deleteUser(user);
        return "redirect:/users";
    }

    @GetMapping(path = "/edit/{id}")
    public String showUpdatePage(@PathVariable long id, Model model) {
        User user = service.getById(id);
        model.addAttribute("user", user);
        return "update_user";
    }

    @PostMapping(path = "/update/{id}")
    public String updateUser(@PathVariable long id, @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "update_user";
        }
        service.updateUser(id, user);
        return "redirect:/users";

    }


}
