package org.top.beautysaloonmvcapp.rdb.service;

import org.springframework.stereotype.Service;
import org.top.beautysaloonmvcapp.entity.Procedure;
import org.top.beautysaloonmvcapp.entity.ProcedureSpecialist;
import org.top.beautysaloonmvcapp.entity.Specialist;
import org.top.beautysaloonmvcapp.rdb.repository.ProcedureRepository;
import org.top.beautysaloonmvcapp.rdb.repository.ProcedureSpecialistRepository;
import org.top.beautysaloonmvcapp.service.ProcedureService;
import org.top.beautysaloonmvcapp.service.SpecialistService;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

// RdbProcedureService - имплементация ProcedureService, работающая с СУБД
@Service
public class RdbProcedureService implements ProcedureService {

    // репозиторий для выполнения операций
    private final ProcedureRepository procedureRepository;
    private final ProcedureSpecialistRepository procedureSpecialistRepository;
    private final SpecialistService specialistService;

    public RdbProcedureService(ProcedureRepository procedureRepository, ProcedureSpecialistRepository procedureSpecialistRepository, SpecialistService specialistService) {
        this.procedureRepository = procedureRepository;
        this.procedureSpecialistRepository = procedureSpecialistRepository;
        this.specialistService = specialistService;
    }

    @Override
    public Iterable<Procedure> findAll() {
        return procedureRepository.findAll();
    }

    @Override
    public Optional<Procedure> findById(Integer id) {
        return procedureRepository.findById(id);
    }

    @Override
    public Optional<Procedure> save(Procedure procedure) {
        return Optional.of(procedureRepository.save(procedure));
    }

    @Override
    public Optional<Procedure> deleteById(Integer id) {
        Optional<Procedure> deleted = findById(id);
        if (deleted.isPresent()) {
            procedureRepository.deleteById(id);
        }
        return deleted;
    }

    @Override
    public Optional<Procedure> update(Procedure procedure) {
        // 1. проверить, есть ли объект с таким id
        Optional<Procedure> updated = findById(procedure.getId());
        if (updated.isPresent()) {
            // если есть, то обновить его
            updated = Optional.of(procedureRepository.save(procedure));
        }
        return updated;
    }

    @Override
    public boolean addSpecialist(ProcedureSpecialist procedureSpecialist) {
        // 1. проверить, есть ли такая услуга и есть ли такой специалист к ней
        Optional<Procedure> procedure = findById(procedureSpecialist.getProcedure().getId());
        if (procedure.isEmpty()) {
            return false;
        }
        Optional<Specialist> specialist = specialistService.findById(procedureSpecialist.getSpecialist().getId());
        if (specialist.isEmpty()) {
            return false;
        }
        // 2. проверить, нет ли уже такого специалиста у этой услуги
        Integer newProcedureSpecialistId = procedureSpecialist.getSpecialist().getId();
        Set<ProcedureSpecialist> procedureSpecialists = procedure.get().getProcedureSpecialistSet();
        for (ProcedureSpecialist ps : procedureSpecialists) {
            if (Objects.equals(ps.getSpecialist().getId(), newProcedureSpecialistId)) {
                return false;
            }
        }
        procedureSpecialistRepository.save(procedureSpecialist);
        return true;
    }

    @Override
    public Optional<ProcedureSpecialist> save(ProcedureSpecialist procedureSpecialist) {
        return Optional.of(procedureSpecialistRepository.save(procedureSpecialist));
    }
}
