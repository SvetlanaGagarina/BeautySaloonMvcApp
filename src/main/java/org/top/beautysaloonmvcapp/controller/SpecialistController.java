package org.top.beautysaloonmvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.beautysaloonmvcapp.entity.Specialist;
import org.top.beautysaloonmvcapp.service.SpecialistService;

import java.util.Optional;

// SpecialistController - контроллер для работы со специалистами
@Controller
@RequestMapping("specialist")
public class SpecialistController {

    // сервис для работы со списком специалистов
    private final SpecialistService specialistService;
    public SpecialistController(SpecialistService specialistService) {
        this.specialistService = specialistService;
    }
    // http://localhost:8080/specialist
    @GetMapping("")
    public String getAll(Model model) {
        Iterable<Specialist> specialists = specialistService.findAll();
        model.addAttribute("specialists", specialists);
        return "specialist/specialist-list";
    }

    // Обработчики добавление объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/specialist/new
    @GetMapping("new")
    public String getAddForm(Model model) {
        Specialist specialist = new Specialist(); // объект для заполнения в форме
        model.addAttribute("specialist", specialist);
        return "specialist/add-specialist-form";
    }

    @PostMapping("new")
    public String postAddForm(Specialist specialist, RedirectAttributes redirectAttributes) {
        Optional<Specialist> saved = specialistService.save(specialist);
        if (saved.isPresent()) {
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Специалист " + saved.get() + " успешно добавлен");
        }
        // перенаправление запроса
        return "redirect:/specialist";
    }

    // Обработчик удаления объекта
    // http://localhost:8080/hotel/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Specialist> deleted = specialistService.deleteById(id);
        if (deleted.isPresent()) {
            // запишем сообщение что успешно удален
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Специалист " + deleted.get() + " успешно удален");
        } else {
            // запишем сообщение что не найден такой
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Специалист с id " + id + " не найден"
            );
        }
        return "redirect:/specialist";
    }

    // Обработчики редактирования объекта
    // Первый возвращает форму, второй обрабатывает
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Specialist> updated = specialistService.findById(id);
        if (updated.isPresent()) {
            model.addAttribute("specialist", updated.get());
            return "specialist/update-specialist-form";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Специалист с id " + id + " не найден");
            return "redirect:/specialist";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(Specialist specialist, RedirectAttributes redirectAttributes) {
        Optional<Specialist> updated = specialistService.update(specialist);
        if (updated.isPresent()) {
            // запишем сообщение что успешно обновлен
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Специалист " + updated.get() + " успешно обновлен");
        } else {
            // запишем сообщение что не получилось обновить
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Не получилось выполнить обновление"
            );
        }
        return "redirect:/specialist";
    }

    // Вывод информации об одном специалисте (подробной)
    @GetMapping("{id}")
    public String details(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Specialist> specialist = specialistService.findById(id);
        if (specialist.isPresent()) {
            model.addAttribute("specialist", specialist);
            return "specialist/specialist-details";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Специалист с id " + id + " не найден");
            return "redirect:/specialist";
        }
    }
}
