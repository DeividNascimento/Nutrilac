package com.example.nutrilac.model;

import java.io.Serializable;

public class PacoteLotes implements Serializable {
    private final String nome;
    private final float peso;
    private final String raca;
    private final int nAnimais;
    private final float producao;
    private final boolean tipo_engorda;
    private final boolean primeira_gestacao;

    public PacoteLotes(String nome, float peso, String raca, int nAnimais, float producao, boolean tipo_engorda, boolean primeira_gestacao) {
        this.nome = nome;
        this.peso = peso;
        this.raca = raca;
        this.nAnimais = nAnimais;
        this.producao = producao;
        this.tipo_engorda = tipo_engorda;
        this.primeira_gestacao = primeira_gestacao;
    }

    public String getNome() {
        return nome;
    }

    public int getNAnimais() {
        return nAnimais;
    }

    public float getProducao() {
        return producao;
    }

    public String getRaca() {
        return raca;
    }

    public boolean isTipo_engorda() {
        return tipo_engorda;
    }

    public boolean isPrimeira_gestacao() {
        return primeira_gestacao;
    }

    public float getPeso() {
        return peso;
    }
}
