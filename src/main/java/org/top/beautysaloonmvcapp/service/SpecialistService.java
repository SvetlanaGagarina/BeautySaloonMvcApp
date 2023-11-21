package org.top.beautysaloonmvcapp.service;

import org.springframework.stereotype.Service;
import org.top.beautysaloonmvcapp.entity.Specialist;

import java.util.Optional;

// Сервис для работы со специалистами
@Service
public interface SpecialistService {

    // получить все отели
    Iterable<Specialist> findAll();

    // получить по id
    Optional<Specialist> findById(Integer id);

    // добавить отель
    Optional<Specialist> save(Specialist specialist);

    // удалить отель
    Optional<Specialist> deleteById(Integer id);

    // обновить отель
    Optional<Specialist> update(Specialist specialist);
}
