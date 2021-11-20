package com.example.springbootcrud;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // will display the user signup form
    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    // will persist a new entity in the database after validating the constrained fields
    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        // if the entity doesn't pass the validation, the signup forme will be redisplayed
        if (result.hasErrors()) {
            return "add-user";
        }
        // else, the entity is saved  and the list of persisted entities will be updated
        userRepository.save(user);
        return "redirect:/index";
    }

    // additional CRUD methods

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    // responsible for fetching the User entity that matches the supplied id from the database
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        // if the entity exists, it will be passed on as a model attribute to the update form view
        // (the form will be populated with the values of the name and email fields)
        model.addAttribute("user", user);
        return "update-user";
    }

    // in both cases (updateUser, deleteUser) the list of persisted entities will be updated accordingly

    // will persist the updated entity in the database
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);
        return "redirect:/index";
    }

    // will remove the given entity
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/index";
    }
}