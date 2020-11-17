package com.example.nutrilac.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.nutrilac.R;
import com.example.nutrilac.model.Dieta;
import com.example.nutrilac.model.Lote;

import java.util.List;

public class ListaPacotesDietasAdapter extends BaseAdapter{
    private final List<Dieta> pacotes;
    private final Context context;

    public ListaPacotesDietasAdapter(List<Dieta> pacotes, Context context) {
        this.pacotes = pacotes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pacotes.size();
    }

    @Override
    public Object getItem(int position) {
        return pacotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_dieta, parent, false);
        Dieta pacote = pacotes.get(position);

        TextView nomeDieta = viewCriada.findViewById(R.id.item_dieta_nomedadieta_resposta);
        nomeDieta.setText(pacote.getNome());

        TextView nomeLote = viewCriada.findViewById(R.id.item_dieta_nomedolote_respota);
        nomeLote.setText(pacote.getNomeLote());

        TextView botaoVerDetalhesDieta = viewCriada.findViewById(R.id.item_dieta_botao_ver_detalhes);
        botaoVerDetalhesDieta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Essa tela ainda será desenvolvida",Toast.LENGTH_LONG).show();
            }
        });

        return viewCriada;
    }
}
