package com.example.Cp1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, message = "O nome deve ter no mínimo 3 carateres")
    private String nome;

    @Size(min = 2, message = "O nome deve ter no mínimo 2 carateres")
    private String estado;

    public Cidade() {
    }

    public Cidade(Long id, String nome, String estado, List<Evento> evento) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
        this.evento = evento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(id, cidade.id) && Objects.equals(nome, cidade.nome) && Objects.equals(estado, cidade.estado) && Objects.equals(evento, cidade.evento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, estado, evento);
    }

    @OneToMany
    private List<Evento> evento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Evento> getEvento() {
        return evento;
    }

    public void setEvento(List<Evento> evento) {
        this.evento = evento;
    }
}
