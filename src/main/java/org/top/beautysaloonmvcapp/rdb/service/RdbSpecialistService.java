package org.top.beautysaloonmvcapp.rdb.service;

import org.springframework.stereotype.Service;
import org.top.beautysaloonmvcapp.entity.Specialist;
import org.top.beautysaloonmvcapp.rdb.repository.SpecialistRepository;
import org.top.beautysaloonmvcapp.service.SpecialistService;

import java.util.Optional;

// RdbSpecialistService - имплементация SpecialistService, работающая с СУБД
@Service
public class RdbSpecialistService implements SpecialistService {

    // репозиторий для выполнения операций
    private final SpecialistRepository specialistRepository;

    public RdbSpecialistService(SpecialistRepository specialistRepository) {
        this.specialistRepository = specialistRepository;
    }
    @Override
    public Iterable<Specialist> findAll() {
        return specialistRepository.findAll();
    }

    @Override
    public Optional<Specialist> findById(Integer id) {
        return specialistRepository.findById(id);
    }

    @Override
    public Optional<Specialist> save(Specialist specialist) {
        return Optional.of(specialistRepository.save(specialist));
    }

    @Override
    public Optional<Specialist> deleteById(Integer id) {
        Optional<Specialist> deleted = findById(id);
        if (deleted.isPresent()) {
            specialistRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<Specialist> update(Specialist specialist) {
        // 1. проверить, есть ли объект с таким id
        Optional<Specialist> updated = findById(specialist.getId());
        if (updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(specialistRepository.save(specialist));
        }
        return updated;
    }
}
