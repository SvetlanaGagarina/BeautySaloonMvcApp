package org.top.beautysaloonmvcapp.rdb.service;

import org.springframework.stereotype.Service;
import org.top.beautysaloonmvcapp.entity.Procedure;
import org.top.beautysaloonmvcapp.rdb.repository.ProcedureRepository;
import org.top.beautysaloonmvcapp.service.ProcedureService;

import java.util.Optional;

// RdbProcedureService - имплементация ProcedureService, работающая с СУБД
@Service
public class RdbProcedureService implements ProcedureService {

    // репозиторий для выполнения операций
    private final ProcedureRepository procedureRepository;

    public RdbProcedureService(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
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
        return Optional.empty();
    }

    @Override
    public Optional<Procedure> update(Procedure procedure) {
        return Optional.empty();
    }
}
