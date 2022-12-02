package com.ms_collaborator_type.service.impl;

import com.ms_collaborator_type.model.Collaborator_Type;
import com.ms_collaborator_type.repository.Collaborator_TypeRepository;
import com.ms_collaborator_type.service.Collaborator_TypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class Collaborator_TypeServiceImpl implements Collaborator_TypeService {

    @Autowired
    private Collaborator_TypeRepository repository;

    @Override
    public Flux<Collaborator_Type> findAll() {
        log.info("Mostrando Lista");
        return repository.findAll();
    }

    @Override
    public Mono<Collaborator_Type> findById(Long id) {
        log.info("Tipo de Colaborador encontrada con el ID = " + id);
        return repository.findById(id);
    }

    @Override
    public Flux<Collaborator_Type> findByStatus(boolean active) {
        log.info("Tipo de Colaborador filtradas por estado = " + active);
        return repository.findByStatus(active);
    }

    @Override
    public Mono<Collaborator_Type> save(Collaborator_Type collaborator_type) {
        log.info("Tipo de Colaborador = " + collaborator_type.toString());
        collaborator_type.setActive(true);
        return repository.save(collaborator_type);
    }

    @Override
    public Mono<Collaborator_Type> update(Collaborator_Type collaborator_type) {
        log.info("Tipo de Colaborador actualizado = " + collaborator_type.toString());
        collaborator_type.setActive(true);
        return repository.save(collaborator_type);
    }

    @Override
    public Mono<ResponseEntity<Collaborator_Type>> delete(Long id) {
        log.info("Tipo de Colaborador eliminado = " + id);
        return repository.findById(id).flatMap(newPerson -> {
            newPerson.setActive(false);
            return repository.save(newPerson);
        }).map(updatedDocument -> new ResponseEntity<>(updatedDocument, HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }

    @Override
    public Mono<ResponseEntity<Collaborator_Type>> restore(Long id) {
        log.info("Tipo de Colaborador restaurada = " + id);
        return repository.findById(id).flatMap(newPerson -> {
            newPerson.setActive(true);
            return repository.save(newPerson);
        }).map(updatedDocument -> new ResponseEntity<>(updatedDocument, HttpStatus.OK)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
    }
}
