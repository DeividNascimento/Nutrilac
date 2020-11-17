package com.example.nutrilac.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrilac.R;
import com.google.android.material.navigation.NavigationView;

public class EscolheVolumososActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Volumosos";
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escolhe_volumosos_activity);
        setTitle(TITLE_APPBAR);

        configuraMenu();

        configuraBotoes();

    }

    private void configuraBotoes() {
        TextView botaoFenos = findViewById(R.id.volumosos_botao_fenos);
        botaoFenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolheVolumososActivity.this, ListagemFenosActivity.class);
                startActivity(intent);
            }
        });

        TextView botaoCapins = findViewById(R.id.volumosos_botao_capins);
        botaoCapins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolheVolumososActivity.this, ListagemCapinsActivity.class);
                startActivity(intent);
            }
        });

        TextView botaoSilagens = findViewById(R.id.volumosos_botao_silagens);
        botaoSilagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolheVolumososActivity.this, ListagemSilagensActivity.class);
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
                        Intent intentInicio = new Intent(EscolheVolumososActivity.this, HomeActivity.class);
                        startActivity(intentInicio);
                        break;
                    case R.id.menu_relatorio:
                    case R.id.menu_minhaconta:
                    case R.id.menu_contato:
                    case R.id.menu_ajuda:
                        Toast.makeText(EscolheVolumososActivity.this,"Essa tela ainda será desenvolvida",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_sairDaConta:
                        Intent intent = new Intent(EscolheVolumososActivity.this, TelaPrimeiroAcessoActivity.class);
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
