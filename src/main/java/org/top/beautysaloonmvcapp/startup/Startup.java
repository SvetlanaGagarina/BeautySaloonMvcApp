package org.top.beautysaloonmvcapp.startup;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.top.beautysaloonmvcapp.form.UserRegistrationForm;
import org.top.beautysaloonmvcapp.service.UserService;

@Service
public class Startup {

    private final UserService userService;

    public Startup(UserService userService) {
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        // если есть суперпользователь, то ничего не делать, иначе создать
        // TODO: вынести сущность ADMIN в конфиг
        if (userService.findUsersByRole("ADMIN").iterator().hasNext()) {
            System.out.println("Superuser is exists");
            return;
        }
        UserRegistrationForm superUserForm = new UserRegistrationForm();
        superUserForm.setLogin("admin");
        superUserForm.setPassword("admin");
        superUserForm.setPasswordConfirmation("admin");
        superUserForm.setRole("ADMIN");
        if (userService.register(superUserForm)) {
            System.out.println("Created default superuser");
        } else {
            System.out.println("Can`t create default superuser");
        }
    }
}
