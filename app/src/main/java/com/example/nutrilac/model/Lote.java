package com.example.nutrilac.model;

public class Lote {
    private Integer idLote;
    private String nome;
    private String raca;
    private Integer quantidade_animais;
    private Float peso;
    private Float producao;
    private Integer dias_lactacao;
    private Integer dias_gestacao;
    private String sistema_producao;
    private Integer numero_gestacoes;

    public Lote(){};

    public Lote(String nome, String raca, Integer quantidade_animais, Float peso, Float producao, Integer dias_lactacao, Integer dias_gestacao, String sistema_producao, Integer numero_gestacoes) {
        this.nome = nome;
        this.raca = raca;
        this.quantidade_animais = quantidade_animais;
        this.peso = peso;
        this.producao = producao;
        this.dias_lactacao = dias_lactacao;
        this.dias_gestacao = dias_gestacao;
        this.sistema_producao = sistema_producao;
        this.numero_gestacoes = numero_gestacoes;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setQuantidade_animais(Integer quantidade_animais) {
        this.quantidade_animais = quantidade_animais;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public void setProducao(Float producao) {
        this.producao = producao;
    }

    public void setDias_lactacao(Integer dias_lactacao) {
        this.dias_lactacao = dias_lactacao;
    }

    public void setDias_gestacao(Integer dias_gestacao) {
        this.dias_gestacao = dias_gestacao;
    }

    public void setSistema_producao(String sistema_producao) {
        this.sistema_producao = sistema_producao;
    }

    public void setNumero_gestacoes(Integer numero_gestacoes) {
        this.numero_gestacoes = numero_gestacoes;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public Integer getQuantidade_animais() {
        return quantidade_animais;
    }

    public Float getPeso() {
        return peso;
    }

    public Float getProducao() {
        return producao;
    }

    public Integer getDias_lactacao() {
        return dias_lactacao;
    }

    public Integer getDias_gestacao() {
        return dias_gestacao;
    }

    public String getSistema_producao() {
        return sistema_producao;
    }

    public Integer getnumero_gestacoes() {
        return numero_gestacoes;
    }
}
