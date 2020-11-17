package com.example.nutrilac.model;

public class Alimento {
    String nome;
    int img;
    boolean expansivel;
    boolean isChecked;
    int idRadioGp;

    public Alimento(String nome, int img, boolean expansivel) {
        this.nome = nome;
        this.img = img;
        this.expansivel = expansivel;
    }

    public Alimento(String nome){
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setExpansivel(boolean expansivel) {
        this.expansivel = expansivel;
    }

    public String getNome() {
        return nome;
    }

    public int getImg() {
        return img;
    }

    public boolean isExpansivel() {
        return expansivel;
    }

    public void setIdRadioGp(int idRadioGp) {
        this.idRadioGp = idRadioGp;
    }
}
