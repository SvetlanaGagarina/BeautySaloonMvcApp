package org.top.beautysaloonmvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckController {

    @GetMapping("")
    public String index() {
        return "index";
    }

    @PostMapping("")
    public String postIndex() {
        return "redirect:/";
    }
}
