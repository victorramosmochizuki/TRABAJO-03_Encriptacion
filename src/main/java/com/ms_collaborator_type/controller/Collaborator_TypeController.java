package com.ms_collaborator_type.controller;

import com.ms_collaborator_type.model.Collaborator_Type;
import com.ms_collaborator_type.service.Collaborator_TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/collaborator_type")
public class Collaborator_TypeController {

    @Autowired
    private Collaborator_TypeService service;

    @GetMapping
    public Flux<Collaborator_Type> findAll(){ return service.findAll(); }

    @GetMapping("/id/{id}")
    public Mono<Collaborator_Type> findById(@PathVariable Long id){ return service.findById(id); }

    @GetMapping("/active/{active}")
    public Flux<Collaborator_Type> findByStatus(@PathVariable boolean active) { return service.findByStatus(active); }

    @PostMapping
    public Mono<Collaborator_Type> save(@RequestBody Collaborator_Type collaborator_type){
        return service.save(collaborator_type);
    }

    @PutMapping
    public Mono<Collaborator_Type> update(@RequestBody Collaborator_Type collaborator_type){
        return service.update(collaborator_type);
    }

    @PostMapping("/delete/{id}")
    public Mono<ResponseEntity<Collaborator_Type>> delete(@PathVariable Long id) { return service.delete(id); }

    @PostMapping("/restore/{id}")
    public Mono<ResponseEntity<Collaborator_Type>> restore(@PathVariable Long id){ return service.restore(id); }
}
