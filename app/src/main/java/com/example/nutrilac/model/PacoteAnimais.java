package com.example.nutrilac.model;

import java.io.Serializable;

public class PacoteAnimais implements Serializable {
    private final String nome;
    private final int registro;
    private final float peso;
    private final String lote;

    public PacoteAnimais(String nome, int registro, float peso, String lote) {
        this.nome = nome;
        this.registro = registro;
        this.peso = peso;
        this.lote = lote;
    }

    public float getPeso() {
        return peso;
    }

    public String getNome() {
        return nome;
    }

    public int getRegistro() {
        return registro;
    }

    public String getLote() {
        return lote;
    }
}
