package com.example.nutrilac.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrilac.R;
import com.example.nutrilac.model.Lote;
import com.example.nutrilac.model.PacoteLotes;
import com.example.nutrilac.ui.activity.CriarDieta;
import com.example.nutrilac.ui.activity.EscolheAlimentosActivity;
import com.example.nutrilac.ui.activity.ListaLotesActivity;
import com.example.nutrilac.ui.activity.MenuAlimentoSelecionadoActivity;

import java.util.List;

public class ListaPacotesLotesAdapter extends BaseAdapter {
    private final List<Lote> pacotes;
    private final Context context;

    public ListaPacotesLotesAdapter(List<Lote> pacotes, Context context) {
        this.pacotes = pacotes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return pacotes.size();
    }

    @Override
    public Lote getItem(int posicao) {
        return pacotes.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return 0;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_lote, parent, false);
        final Lote pacote = pacotes.get(posicao);

        TextView nome = viewCriada.findViewById(R.id.item_lote_nome_resposta);
        nome.setText(pacote.getNome());

        TextView nAnimais = viewCriada.findViewById(R.id.item_lote_quantidade_resposta);
        String formataEmTexto = String.valueOf(pacote.getQuantidade_animais());
        nAnimais.setText(formataEmTexto);

        TextView producao = viewCriada.findViewById(R.id.item_lote_producao_resposta);
        String formataProducaoEmTexto = pacote.getProducao() + " L";
        producao.setText(formataProducaoEmTexto);

        TextView peso = viewCriada.findViewById(R.id.item_lote_peso_resposta);
        formataEmTexto = pacote.getPeso() + " KG";
        peso.setText(formataEmTexto);

        TextView raca = viewCriada.findViewById(R.id.item_lote_raca_resposta);
        formataEmTexto = pacote.getRaca();
        raca.setText(formataEmTexto);

        TextView sistema_producao = viewCriada.findViewById(R.id.item_lote_sistema_producao_resposta);
        formataEmTexto = pacote.getSistema_producao();
        sistema_producao.setText(formataEmTexto);

        TextView numero_gestacoes = viewCriada.findViewById(R.id.item_lote_primeira_gestacao_resposta);
        formataEmTexto = String.valueOf(pacote.getnumero_gestacoes());
        numero_gestacoes.setText(formataEmTexto);

        TextView dias_lactacao = viewCriada.findViewById(R.id.item_lote_lactacao_resposta);
        formataEmTexto = String.valueOf(pacote.getDias_lactacao());
        dias_lactacao.setText(formataEmTexto);

        TextView dias_gestacao = viewCriada.findViewById(R.id.item_lote_gestacao_resposta);
        formataEmTexto = String.valueOf(pacote.getDias_gestacao());
        dias_gestacao.setText(formataEmTexto);

        TextView botaoCriarDieta = viewCriada.findViewById(R.id.botao_nova_dieta_lista_lotes);
        botaoCriarDieta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CriarDieta.class);
                context.startActivity(intent);
            }
        });

        TextView botaoEditarLote = viewCriada.findViewById(R.id.botao_editar_lote_lista_lotes);
        botaoEditarLote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Essa tela ainda será desenvolvida",Toast.LENGTH_LONG).show();
            }
        });

        TextView botaoExcluirLote = viewCriada.findViewById(R.id.botao_excluir_lote_lista_lotes);
        botaoExcluirLote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Essa tela ainda será desenvolvida",Toast.LENGTH_LONG).show();
            }
        });

        return viewCriada;
    }
}
