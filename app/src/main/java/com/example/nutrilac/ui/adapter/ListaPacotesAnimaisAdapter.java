package com.example.nutrilac.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nutrilac.R;
import com.example.nutrilac.model.PacoteAnimais;

import java.util.List;

public class ListaPacotesAnimaisAdapter extends BaseAdapter {
    private final List<PacoteAnimais> pacotes;
    private final Context context;

    public ListaPacotesAnimaisAdapter(List<PacoteAnimais> pacotes, Context context) {
        this.pacotes = pacotes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pacotes.size();
    }

    @Override
    public Object getItem(int posicao) {
        return pacotes.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return 0;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_animal, parent, false);
        PacoteAnimais pacote = pacotes.get(posicao);

        TextView nome = viewCriada.findViewById(R.id.item_animal_nome_resposta);
        nome.setText(pacote.getNome());

        TextView registro = viewCriada.findViewById(R.id.item_animal_registro_resposta);
        String formataEmTexto = String.valueOf(pacote.getRegistro());
        registro.setText(formataEmTexto);

        TextView peso = viewCriada.findViewById(R.id.item_animal_peso_resposta);
        String pesoFormatado = pacote.getPeso() + " kg";
        peso.setText(pesoFormatado);

        TextView lote = viewCriada.findViewById(R.id.item_animal_lote_resposta);
        lote.setText(pacote.getLote());

        return viewCriada;
    }
}
