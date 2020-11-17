package com.example.nutrilac.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrilac.R;
import com.example.nutrilac.database.DadosOpenHelper;
import com.example.nutrilac.model.Dieta;
import com.example.nutrilac.model.Lote;
import com.example.nutrilac.ui.adapter.ListaPacotesDietasAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class ListaDietasActivity extends AppCompatActivity {

    public static final String TITTLE_APPBAR = "Lista de dietas";
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dietas);
        setTitle(TITTLE_APPBAR);

        configuraMenu();

        configuraBotoes();

        DadosOpenHelper meuBD = new DadosOpenHelper(ListaDietasActivity.this);
        List<Dieta> listaDietas = meuBD.listarDietas();
        ListView listaDeDietas = findViewById(R.id.lista_dietas_listview);
        listaDeDietas.setAdapter(new ListaPacotesDietasAdapter(listaDietas,this));

    }

    private void configuraBotoes() {
        TextView botaoCriarDieta = findViewById(R.id.lista_dietas_button_criar_dieta);
        botaoCriarDieta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaDietasActivity.this, CriarDieta.class);
                startActivity(intent);
            }
        });
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
                        Intent intentInicio = new Intent(ListaDietasActivity.this, HomeActivity.class);
                        startActivity(intentInicio);
                        break;
                    case R.id.menu_relatorio:
                    case R.id.menu_minhaconta:
                    case R.id.menu_contato:
                    case R.id.menu_ajuda:
                        Toast.makeText(ListaDietasActivity.this,"Essa tela ainda será desenvolvida",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_sairDaConta:
                        Intent intent = new Intent(ListaDietasActivity.this, TelaPrimeiroAcessoActivity.class);
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
}
