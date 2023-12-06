package org.top.beautysaloonmvcapp.service;

import org.springframework.stereotype.Service;
import org.top.beautysaloonmvcapp.entity.Procedure;

import java.util.Optional;

// Сервис для работы с услугами
@Service
public interface ProcedureService {

    // получить все услуги
    Iterable<Procedure> findAll();

    // получить по id
    Optional<Procedure> findById(Integer id);

    // добавить услугу
    Optional<Procedure> save(Procedure procedure);

    // удалить услугу
    Optional<Procedure> deleteById(Integer id);

    // обновить услугу
    Optional<Procedure> update(Procedure procedure);
}
