package com.example.Cp1.service;


import com.example.Cp1.model.Evento;
import com.example.Cp1.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;


    @Transactional
    public List<Evento> findAll(){
        return repository.findAll();
    }

    @Transactional
    public Evento insert(Evento evento){
        return repository.save(evento);
    }

    @Transactional(readOnly = true)
    public Evento findById(Long id){
        Evento evento = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Evento invalida - "+ id)
        );
        return evento;
    }

    @Transactional
    public Evento update(Long id, Evento entity) {
        try {
            Evento evento = repository.getReferenceById(id);
            copyToEvento(entity, evento);
            evento = repository.save(evento);
            return evento;
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Evento não encontrado");
        }
    }

    // Método para excluir categoria
    @Transactional
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Evento inválida - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Falha de integridade referencial - id: " + id);
        }
    }


    private void copyToEvento(Evento entity, Evento evento) {
        evento.setCidade(entity.getCidade());
        evento.setData(entity.getData());
        evento.setNome(entity.getNome());
        evento.setUrl(entity.getUrl());
    }

}
