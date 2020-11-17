package com.example.nutrilac.model;

import java.util.List;

public class Dieta {
    String nome;
    //List<Alimento> listaAlimentos;
    Integer id_lote;
    String nomeLote;

    public Dieta(String nome, Integer id_lote, String nomeLote) {
        this.nome = nome;
        this.id_lote = id_lote;
        this.nomeLote = nomeLote;
    }

    public Dieta() {}

    public String getNomeLote() {
        return nomeLote;
    }


    public void setNomeLote(String nomeLote) {
        this.nomeLote = nomeLote;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId_lote(int id_lote) {
        this.id_lote = id_lote;
    }

    public int getId_lote() {
        return id_lote;
    }
}
