package com.example.nutrilac.model;

import java.io.Serializable;

public class TempoAlimento implements Serializable {
    private String nome;
    private int id;
    public TempoAlimento(String nome, int id){
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
