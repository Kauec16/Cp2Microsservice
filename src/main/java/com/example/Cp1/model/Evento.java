package com.example.Cp1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "tb_evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, message = "O nome deve ter no mínimo 3 carateres")
    private String nome;

    @NotBlank
    private String data;

    @NotBlank
    @Size(min = 3, message = "O nome deve ter no mínimo 3 carateres")
    private String url;

    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false) //PK
    private Cidade cidade;


    public Evento() {
    }

    public Evento(Long id, String nome, String data, String url, Cidade cidade) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.url = url;
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id) && Objects.equals(nome, evento.nome) && Objects.equals(data, evento.data) && Objects.equals(url, evento.url) && Objects.equals(cidade, evento.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, data, url, cidade);
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
