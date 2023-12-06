package org.top.beautysaloonmvcapp.rdb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.top.beautysaloonmvcapp.entity.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
}
