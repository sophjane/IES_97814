package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    // This annotation ensures that HTTP GET requests to /greeting are mapped to the greeting() method.
    @GetMapping("/greeting")
    // @RequestParam binds the value of the query string parameter name into the name parameter of the greeting() method.
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        // The value of the name parameter is added to a Model object, ultimately making it accessible to the view template.
        model.addAttribute("name", name);
        return "greeting";
    }
}