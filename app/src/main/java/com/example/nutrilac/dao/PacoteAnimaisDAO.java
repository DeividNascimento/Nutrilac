package com.example.nutrilac.dao;

import com.example.nutrilac.model.PacoteAnimais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacoteAnimaisDAO {
    public List<PacoteAnimais> lista() {
        List<PacoteAnimais> pacotes = new ArrayList<>(Arrays.asList(
                new PacoteAnimais("Princesa",26,720, "Filhas da Mimosa"),
                new PacoteAnimais("Safira",30,800, "Filhas da Estrela")));
        return pacotes;
    }
}
