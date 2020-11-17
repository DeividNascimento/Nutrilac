package com.example.nutrilac.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrilac.R;
import com.example.nutrilac.database.DadosOpenHelper;
import com.example.nutrilac.model.Lote;
import com.example.nutrilac.model.Usuario;
import com.google.android.material.navigation.NavigationView;

public class CriarLoteActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Criar lote";
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;

    private EditText nome;
    private Spinner raca;
    private EditText quantidade_animais;
    private EditText peso;
    private EditText producao;
    private EditText dias_lactacao;
    private EditText dias_gestacao;
    private RadioGroup sistema_producao;
    private EditText numero_gestacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_lote);
        setTitle(TITLE_APPBAR);

        configuraMenu();

        nome = findViewById(R.id.criar_lote_nome);
        raca = findViewById(R.id.criar_lote_raca_spinner);
        quantidade_animais = findViewById(R.id.criar_lote_quantidade);
        peso = findViewById(R.id.criar_lote_peso);
        producao = findViewById(R.id.criar_lote_producao);
        dias_lactacao = findViewById(R.id.criar_lote_lactacao);
        dias_gestacao = findViewById(R.id.criar_lote_dias_gestacao);
        sistema_producao = findViewById(R.id.criar_lote_radio_group_sistema_producao);
        numero_gestacoes = findViewById(R.id.criar_lote_numero_gestacoes);

        final TextView textCriarConta = findViewById(R.id.criar_lote_botao_confirma);
        textCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
                Intent intent = new Intent(CriarLoteActivity.this, ListaLotesActivity.class);
                startActivity(intent);
            }
        });
    }

    public void salvar(){
        Lote a = new Lote();
        a.setRaca(raca.getSelectedItem().toString().trim());
        a.setNome(nome.getText().toString().trim());
        a.setQuantidade_animais(Integer.parseInt(quantidade_animais.getText().toString().trim()));
        a.setPeso(Float.parseFloat(peso.getText().toString().trim()));
        a.setProducao(Float.parseFloat(producao.getText().toString().trim()));
        a.setDias_gestacao(Integer.parseInt(dias_gestacao.getText().toString().trim()));
        a.setDias_lactacao(Integer.parseInt(dias_lactacao.getText().toString().trim()));

        // Salvando o item selecionado nas opções de sistema de produção (RadioButton)
        int radioButtonID = sistema_producao.getCheckedRadioButtonId();
        View radioButton = sistema_producao.findViewById(radioButtonID);
        int idx = sistema_producao.indexOfChild(radioButton);
        RadioButton radio = (RadioButton) sistema_producao.getChildAt(idx);
        String opcaoSelecionada = radio.getText().toString();

        a.setSistema_producao(opcaoSelecionada.trim());
        a.setNumero_gestacoes(Integer.parseInt(numero_gestacoes.getText().toString().trim()));
        DadosOpenHelper meuBD = new DadosOpenHelper(CriarLoteActivity.this);
        meuBD.adicionarLote(a);
    }

    private void configuraMenu() {
        mdrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigatView = (NavigationView) findViewById(R.id.navView);
        navigatView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_inicio:
                        Intent intentInicio = new Intent(CriarLoteActivity.this, HomeActivity.class);
                        startActivity(intentInicio);
                        break;
                    case R.id.menu_relatorio:
                    case R.id.menu_minhaconta:
                    case R.id.menu_contato:
                    case R.id.menu_ajuda:
                        Toast.makeText(CriarLoteActivity.this, "Essa tela ainda será desenvolvida", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_sairDaConta:
                        Intent intent = new Intent(CriarLoteActivity.this, TelaPrimeiroAcessoActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
