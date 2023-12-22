package org.top.beautysaloonmvcapp.controller.dev;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.top.beautysaloonmvcapp.form.UserRegistrationForm;
import org.top.beautysaloonmvcapp.service.UserService;

@RestController
@RequestMapping("dev")
public class UserDevController {

    private final UserService userService;

    public UserDevController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("user")
    public boolean registerUser(@RequestBody UserRegistrationForm userRegistrationForm) {
       return userService.register(userRegistrationForm);
    }
}
