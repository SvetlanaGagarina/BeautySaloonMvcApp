package org.top.beautysaloonmvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.beautysaloonmvcapp.entity.Procedure;
import org.top.beautysaloonmvcapp.entity.ProcedureSpecialist;
import org.top.beautysaloonmvcapp.service.ProcedureService;
import org.top.beautysaloonmvcapp.service.SpecialistService;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

// ProcedureController - контроллер для работы с услугами
@Controller
@RequestMapping("procedure")
public class ProcedureController {

    // сервис для работы со списком услуг
    private final ProcedureService procedureService;
    private final SpecialistService specialistService;

    public ProcedureController(ProcedureService procedureService, SpecialistService specialistService) {

        this.procedureService = procedureService;
        this.specialistService = specialistService;

    }
    // 1. получить все услуги
    // http://localhost:8080/procedure
    @GetMapping("")
    public String getAll(Model model) {
        Iterable<Procedure> procedures = procedureService.findAll();
        model.addAttribute("procedures", procedures);
        return "procedure/procedure-list";
    }

    // 2. Обработчики добавление объекта
    // первый получает форму, второй обрабатывает
    // http://localhost:8080/procedure/new
    @GetMapping("new")
    public String getAddForm(Model model) {
        Procedure procedure = new Procedure(); // объект для заполнения в форме
        model.addAttribute("procedure", procedure);
        return "procedure/add-procedure-form";
    }

    @PostMapping("new")
    public String postAddForm(Procedure procedure, @RequestParam MultipartFile previewImage, RedirectAttributes redirectAttributes) throws IOException {
       // перед добавление декодировать данные из формы и записать в данные объекта
        String previewImageData = Base64.getEncoder().encodeToString(previewImage.getBytes());
        procedure.setPreviewImageData(previewImageData);
        Optional<Procedure> saved = procedureService.save(procedure);
        saved.ifPresent(value -> redirectAttributes.addFlashAttribute(// автозамена среды
                "successMessage",
                "Услуга " + value + " успешно добавлена"));
        // перенаправление запроса
        return "redirect:/procedure";
    }

    // 3. обновить услугу
    // Обработчики редактирования объекта
    // Первый возвращает форму, второй обрабатывает
    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Procedure> updated = procedureService.findById(id); // получение по id
        if (updated.isPresent()) {
            model.addAttribute("procedure", updated.get());
            model.addAttribute("procedureSpecialist", new ProcedureSpecialist());
            model.addAttribute("specialists", specialistService.findAll());
            return "procedure/update-procedure-form";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Услуга с id " + id + " не найдена");
            return "redirect:/procedure";
        }
    }

    @PostMapping("/update")
    public String postUpdateForm(Procedure procedure,@RequestParam MultipartFile previewImage, RedirectAttributes redirectAttributes) throws IOException {
        // перед добавление декодировать данные из формы и записать в данные объекта
        String previewImageData = Base64.getEncoder().encodeToString(previewImage.getBytes());
        procedure.setPreviewImageData(previewImageData);
        Optional<Procedure> updated = procedureService.update(procedure);
        if (updated.isPresent()) {
            // запишем сообщение что успешно обновлена
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Услуга " + updated.get() + " успешно обновлена");
        } else {
            // запишем сообщение что не получилось обновить
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Не получилось выполнить обновление"
            );
        }
        return "redirect:/procedure";
    }

    // 4. Обработчик удаления объекта
    // http://localhost:8080/procedure/delete/{id}
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Procedure> deleted = procedureService.deleteById(id); // получение по id
        if (deleted.isPresent()) {
            // запишем сообщение что успешно удален
            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "Услуга " + deleted.get() + " успешно удалена");
        } else {
            // запишем сообщение что не найдена такая услуга
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Услуга с id " + id + " не найдена"
            );
        }
        return "redirect:/procedure";
    }
    // 5. Вывод информации об одной услуге (подробной)
    @GetMapping("{id}")
    public String details(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Procedure> procedure = procedureService.findById(id);
        if (procedure.isPresent()) {
            model.addAttribute("procedure", procedure.get());
            return "procedure/procedure-details";
        } else {
            redirectAttributes.addFlashAttribute(
                    "dangerMessage",
                    "Услуга с id " + id + " не найдена");
            return "redirect:/procedure";
        }
    }

        @PostMapping("{id}/add-specialist")
        public String addSpecialist(@PathVariable Integer id, ProcedureSpecialist procedureSpecialist, RedirectAttributes redirectAttributes) {
            Optional<Procedure> procedure = procedureService.findById(id);
            if (procedure.isPresent()) {
                procedureSpecialist.setProcedure(procedure.get());
                if (procedureService.addSpecialist(procedureSpecialist)) {
                    redirectAttributes.addFlashAttribute(
                            "successMessage",
                            "Специалист к услуге " + procedure.get() + " успешно добавлен");
                } else {
                    redirectAttributes.addFlashAttribute("dangerMessage",
                            "Специалист к услуге добавиться не смог");
                }
                return "redirect:/procedure/" + procedureSpecialist.getProcedure().getId();
            } else {
                return "redirect:/procedure"; // не найдена
            }
    }

}
