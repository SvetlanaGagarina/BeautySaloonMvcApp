package org.top.beautysaloonmvcapp.service;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientPropertiesMapper;
import org.springframework.stereotype.Service;
import org.top.beautysaloonmvcapp.entity.Procedure;
import org.top.beautysaloonmvcapp.entity.ProcedureSpecialist;

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

    // группа методов для работы со специалистами для услуг
    boolean addSpecialist(ProcedureSpecialist procedureSpecialist);
    Optional<ProcedureSpecialist> save(ProcedureSpecialist procedureSpecialist);
}
