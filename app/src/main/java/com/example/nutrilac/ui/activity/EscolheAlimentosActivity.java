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

public class EscolheAlimentosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Alimentos";
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolhe_alimentos);
        setTitle(TITULO_APPBAR);

        configuraMenu();
        configuraBotoes();
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
                        Intent intentInicio = new Intent(EscolheAlimentosActivity.this, HomeActivity.class);
                        startActivity(intentInicio);
                        break;
                    case R.id.menu_relatorio:
                    case R.id.menu_minhaconta:
                    case R.id.menu_contato:
                    case R.id.menu_ajuda:
                        Toast.makeText(EscolheAlimentosActivity.this,"Essa tela ainda será desenvolvida",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_sairDaConta:
                        Intent intent = new Intent(EscolheAlimentosActivity.this, TelaPrimeiroAcessoActivity.class);
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

    private void configuraBotoes() {
        TextView botaoVolumoso = findViewById(R.id.volumosos_botao_capins);
        botaoVolumoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolheAlimentosActivity.this, EscolheVolumososActivity.class);
                startActivity(intent);
            }
        });

        TextView botaoConcentrado = findViewById(R.id.volumosos_botao_fenos);
        botaoConcentrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolheAlimentosActivity.this, ListagemConcentradosActivity.class);
                startActivity(intent);
            }
        });

        TextView botaoSuplemento = findViewById(R.id.volumosos_botao_silagens);
        botaoSuplemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EscolheAlimentosActivity.this, ListagemMineraisActivity.class);
                startActivity(intent);
            }
        });
    }
}
