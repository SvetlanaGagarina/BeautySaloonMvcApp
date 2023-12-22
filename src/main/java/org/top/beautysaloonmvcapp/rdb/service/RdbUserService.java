package org.top.beautysaloonmvcapp.rdb.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.top.beautysaloonmvcapp.entity.User;
import org.top.beautysaloonmvcapp.form.UserRegistrationForm;
import org.top.beautysaloonmvcapp.rdb.repository.UserRepository;
import org.top.beautysaloonmvcapp.service.UserService;

import java.util.Optional;

// RdbUserService - бизнес логика работы с пользователями
@Service
public class RdbUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RdbUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegistrationForm userRegistrationForm) {
        // бизнес-логика регистрации пользователя
        // алгоритм:
        // 1. проверить подтверждение пароля
        // 2. проверить, уникален ли логин
        // 3. если все ок, то сохранить пользователя

        if (!userRegistrationForm.getPassword().equals(userRegistrationForm.getPasswordConfirmation())) {
            return false;
        }
        if (userRepository.findByLogin(userRegistrationForm.getLogin()).isPresent()) {
            return false;
        }
        // создаем пользователя
        User user = new User();
        user.setLogin(userRegistrationForm.getLogin());
        String passwordHash = passwordEncoder.encode(userRegistrationForm.getPassword());
        user.setPassword(passwordHash);
        user.setRole(userRegistrationForm.getRole());
        userRepository.save(user);
        return true;
    }

    @Override
    public Iterable<User> findUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }


}

