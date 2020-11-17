package com.example.nutrilac.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrilac.R;
import com.example.nutrilac.database.DadosOpenHelper;
import com.example.nutrilac.model.Lote;
import com.example.nutrilac.ui.adapter.ListaPacotesLotesAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class CriarDieta extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Selecionar lote";
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;
    private String nomeLoteSelecionado;
    private boolean clicou = false;
    private EditText nome;
    boolean res = false;
    private boolean verificaNome = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_dieta);
        setTitle(TITLE_APPBAR);

        configuraMenu();

        DadosOpenHelper meuBD = new DadosOpenHelper(CriarDieta.this);
        List<Lote> listaLotes = meuBD.listarLotes();
        ListView listaDePacotesLotesParaCriarDieta = findViewById(R.id.lista_lotes_criar_dieta);
        listaDePacotesLotesParaCriarDieta.setAdapter(new ListaLotesParaCriarDietaAdapter(listaLotes,this));

        nome = findViewById(R.id.criar_dieta_nome_da_dieta);

        TextView botaoCriarLote = findViewById(R.id.selecionaBtn_confirmar_lote_criar_dieta);
        botaoCriarLote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaCampo();
                if(verificaNome == false){
                    Intent intent = new Intent(CriarDieta.this, MenuAlimentoSelecionadoActivity.class);
                    intent.putExtra("nome",nome.getText().toString());
                    intent.putExtra("lote",nomeLoteSelecionado);
                    startActivity(intent);
                }
            }
        });

    }

    private void validaCampo() {
        verificaNome = false;
        String nomeDieta = nome.getText().toString();
        if (res = isCampoVazio(nomeDieta)) {
            nome.requestFocus();
            verificaNome = true;
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Defina um nome para a sua dieta");
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }
    }

    private boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    private void configuraMenu() {
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open,R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigatView = (NavigationView) findViewById(R.id.navView);
        navigatView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_inicio:
                        Intent intentInicio = new Intent(CriarDieta.this, HomeActivity.class);
                        startActivity(intentInicio);
                        break;
                    case R.id.menu_relatorio:
                    case R.id.menu_minhaconta:
                    case R.id.menu_contato:
                    case R.id.menu_ajuda:
                        Toast.makeText(CriarDieta.this,"Essa tela ainda será desenvolvida",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_sairDaConta:
                        Intent intent = new Intent(CriarDieta.this, TelaPrimeiroAcessoActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class ListaLotesParaCriarDietaAdapter extends BaseAdapter {
        private final List<Lote> pacotes;
        private final Context context;
        Integer selectedPosition = 0;

        public ListaLotesParaCriarDietaAdapter(List<Lote> pacotes, Context context) {
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
            View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_lista_lote_criar_dieta, parent, false);
            Lote pacote = pacotes.get(position);

            TextView nome = viewCriada.findViewById(R.id.radio_button_lista_lote);
            nome.setText(pacote.getNome());

            final RadioButton radio = viewCriada.findViewById(R.id.radio_button_lista_lote);
            final CardView cardView = viewCriada.findViewById(R.id.card_view_item_lote);

            radio.setChecked(position == selectedPosition);
            radio.setTag(position);
            cardView.setTag(position);
            if(!clicou){ // se o usuário não clicar em nenhum button, então precisamos pegar o lote que já está selecionado e salvá-lo.
                nomeLoteSelecionado = radio.getText().toString();
            }
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedPosition = (Integer) v.getTag();
                    nomeLoteSelecionado = radio.getText().toString();
                    clicou = true;
                    notifyDataSetChanged();
                }
            });

            radio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedPosition = (Integer) view.getTag();
                    nomeLoteSelecionado = radio.getText().toString();
                    clicou = true;
                    notifyDataSetChanged();
                }
            });

            return viewCriada;
        }
    }
}
