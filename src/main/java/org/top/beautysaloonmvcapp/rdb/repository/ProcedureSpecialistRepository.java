package org.top.beautysaloonmvcapp.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.beautysaloonmvcapp.entity.ProcedureSpecialist;

@Repository
public interface ProcedureSpecialistRepository extends CrudRepository<ProcedureSpecialist, Integer> {
}
