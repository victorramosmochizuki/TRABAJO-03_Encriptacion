package com.ms_collaborator_type.repository;


import com.ms_collaborator_type.model.Collaborator_Type;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface Collaborator_TypeRepository extends ReactiveCrudRepository<Collaborator_Type, Long> {

    @Query("SELECT * FROM COLLABORATOR_TYPE WHERE active = :active ORDER BY id DESC")
    Flux<Collaborator_Type> findByStatus(@Param("active") boolean active);
}

