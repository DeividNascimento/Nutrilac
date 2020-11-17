package com.example.nutrilac.ui.adapter;
/*
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrilac.R;
import com.example.nutrilac.model.Alimento;
import com.example.nutrilac.model.Lote;
import com.example.nutrilac.ui.activity.MenuAlimentoSelecionadoActivity;

import java.util.List;

public class ListaAlimentosTratorzinhoAdapter extends BaseAdapter {
    private final List<Alimento> nomes;
    private final Context context;

    public ListaAlimentosTratorzinhoAdapter(List<Alimento> nomes, Context context) {
        this.nomes = nomes;
        this.context = context;
    }
    @Override
    public int getCount() {
        return nomes.size();
    }

    @Override
    public Object getItem(int position) {
        return nomes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_tratorzinho, parent, false);
        final Alimento nome = nomes.get(position);

        ImageView deletarAlimento = viewCriada.findViewById(R.id.icone_excluir_item_tratorzinho);
        deletarAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Essa tela ainda será desenvolvida", Toast.LENGTH_LONG).show();
                delete(position);
            }
        });

        TextView nomeAlimento = viewCriada.findViewById(R.id.nome_alimento_tratorzinho);
        nomeAlimento.setText(nome.getNome());

        return viewCriada;
    }

    public void delete(int i){
        nomes.remove(i);
        notifyDataSetChanged();
    }
}*/
