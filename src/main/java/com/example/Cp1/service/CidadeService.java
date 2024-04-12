package com.example.Cp1.service;

import com.example.Cp1.model.Cidade;
import com.example.Cp1.repository.CidadeRepository;
import jakarta.persistence.EntityNotFoundException;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    @Transactional
    public List<Cidade> findAll(){
        return repository.findAll();
    }

    @Transactional
    public Cidade insert(Cidade cidade){
        return repository.save(cidade);
    }

    @Transactional(readOnly = true)
    public Cidade findById(Long id){
        Cidade cidade = repository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Cidade invalida - "+ id)
        );
       return cidade;
    }

    @Transactional
    public Cidade update(Long id, Cidade entity) {

        try {
            Cidade cidade = repository.getReferenceById(id);
            copyToCidade(entity, cidade);
            cidade = repository.save(cidade);
            return cidade;
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Cidade não encontrado");
        }
    }

    // Método para excluir cidade
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Cidade inválida - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Falha de integridade referencial - id: " + id);
        }
    }


    private void copyToCidade(Cidade entity, Cidade cidade) {
        cidade.setEstado(entity.getEstado());
        cidade.setNome(entity.getNome());
        cidade.setEvento(entity.getEvento());
    }

}
