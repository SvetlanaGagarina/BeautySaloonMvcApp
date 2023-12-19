package org.top.beautysaloonmvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutCompany {

    @GetMapping("about-company")
    public String aboutCompany() {
        return "about-company";
    }
}
