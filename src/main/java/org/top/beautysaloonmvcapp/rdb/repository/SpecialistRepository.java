package org.top.beautysaloonmvcapp.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.beautysaloonmvcapp.entity.Specialist;

// SpecialistRepository - репозиторий для работы со специалистами
@Repository
public interface SpecialistRepository extends CrudRepository<Specialist, Integer> {
}
