package com.example.nutrilac.dao;

import com.example.nutrilac.model.PacoteLotes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacoteLotesDAO {
    public List<PacoteLotes> lista() {
        List<PacoteLotes> pacotes = new ArrayList<>(Arrays.asList(
                new PacoteLotes("Filhas da Mimosa", 800, "Girolando", 2, 60, true, true),
                new PacoteLotes("Filhas da Estrela", 1200, "Zebu", 13, 40, true, false)));
        return pacotes;
    }

}
