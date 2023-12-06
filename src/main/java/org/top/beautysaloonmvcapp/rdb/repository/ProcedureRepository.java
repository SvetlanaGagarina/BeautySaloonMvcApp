package org.top.beautysaloonmvcapp.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.beautysaloonmvcapp.entity.Procedure;

// ProcedureRepository - репозиторий для работы с услугами
@Repository
public interface ProcedureRepository extends CrudRepository<Procedure, Integer> {
}
