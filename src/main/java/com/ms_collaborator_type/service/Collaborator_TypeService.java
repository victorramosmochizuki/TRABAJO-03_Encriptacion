package com.ms_collaborator_type.service;

import com.ms_collaborator_type.model.Collaborator_Type;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Collaborator_TypeService {

    Flux<Collaborator_Type> findAll();

    Mono<Collaborator_Type> findById(Long id);

    Flux<Collaborator_Type> findByStatus(boolean active);

    Mono<Collaborator_Type> save(Collaborator_Type collaborator_type);

    Mono<Collaborator_Type> update(Collaborator_Type collaborator_type);

    Mono<ResponseEntity<Collaborator_Type>> delete(Long id);

    Mono<ResponseEntity<Collaborator_Type>> restore(Long id);

}
