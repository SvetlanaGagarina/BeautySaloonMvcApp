package org.top.beautysaloonmvcapp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("error")// чтоб перехватывал все ошибки
    public String error(HttpServletRequest request, Model model) {
        Object reqStatus = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (reqStatus == null) {
            model.addAttribute("status", "Undefined error");
            model.addAttribute("message", "Undefined error");
            return "error-page";
        }

        int statusCode = Integer.parseInt(reqStatus.toString());
        String status = "";     // статус ошибки
        String message = "";    // сообщение к ошибке
        switch (statusCode) {
            case 404 -> {
                status = "404 Не найдено";
                message = "Эта страница не существует";
            }
            case 400 -> {
                status = "400 Неверные данные";
                message = "Неверный запрос, введите данные правильно";
            }
            case 500, 501, 502, 503, 504, 505 -> {
                status = "5xx Внутренняя ошибка сервера";
                message = "Приложение сломано ленивым разработчиком";
            }
        }
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "error-page";
    }
}

