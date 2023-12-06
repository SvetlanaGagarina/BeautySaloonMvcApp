package org.top.beautysaloonmvcapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.top.beautysaloonmvcapp.entity.Review;
import org.top.beautysaloonmvcapp.entity.Specialist;
import org.top.beautysaloonmvcapp.service.ReviewService;
import org.top.beautysaloonmvcapp.service.SpecialistService;

import java.util.Optional;

@Controller
@RequestMapping("review")
public class ReviewController {
    // Сервисы
    private final SpecialistService specialistService;
    private final ReviewService reviewService;

    public ReviewController(SpecialistService specialistService, ReviewService reviewService) {
        this.specialistService = specialistService;
        this.reviewService = reviewService;
    }

    @GetMapping("new")
    public String getAddForm(Model model) {
        Review review = new Review();
        Iterable<Specialist> specialists = specialistService.findAll();
        model.addAttribute("review", review);
        model.addAttribute("specialists", specialists);
        return "review/add-review-form";
    }

    @GetMapping("new/{id}")
    public String getAddForm(@PathVariable Integer id, Model model) {
        Review review = new Review();
        Optional<Specialist> specialist = specialistService.findById(id);
        model.addAttribute("review", review);
        model.addAttribute("specialists", new Specialist[]{specialist.get()});
        return "review/add-review-form";
    }

    @PostMapping("new")
    public String postAddForm(Review review) {
        reviewService.save(review);
        return "redirect:/specialist/" + review.getSpecialist().getId();
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        Optional<Review> deleted = reviewService.deleteById(id);
        // TODO: обработка ошибки
        return deleted
                .map(review -> "redirect:/specialist/" + review.getSpecialist().getId())
                .orElse("redirect:/specialist");
    }
}
