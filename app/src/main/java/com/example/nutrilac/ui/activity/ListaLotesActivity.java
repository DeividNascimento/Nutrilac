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
import com.example.nutrilac.dao.PacoteLotesDAO;
import com.example.nutrilac.database.DadosOpenHelper;
import com.example.nutrilac.model.Lote;
import com.example.nutrilac.model.PacoteLotes;
import com.example.nutrilac.ui.adapter.ListaPacotesLotesAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class ListaLotesActivity extends AppCompatActivity {

    public static final String TITLE_APPBAR = "Lista de lotes";
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_lotes);
        setTitle(TITLE_APPBAR);

        configuraMenu();

        configuraBotoes();

        //configuraLista();

        DadosOpenHelper meuBD = new DadosOpenHelper(ListaLotesActivity.this);
        List<Lote> listaLotes = meuBD.listarLotes();
        ListView listaDePacotesLotes = findViewById(R.id.lista_lotes_listview);
        listaDePacotesLotes.setAdapter(new ListaPacotesLotesAdapter(listaLotes,this));

    }

    /*private void configuraLista() {
        ListView listaDePacotesLotes = findViewById(R.id.lista_lotes_listview);
        final List<PacoteLotes> pacotes = new PacoteLotesDAO().lista();
        listaDePacotesLotes.setAdapter(new ListaPacotesLotesAdapter(pacotes,this));
    }*/

    private void configuraBotoes() {
        TextView botaoCriarLote = findViewById(R.id.lista_lotes_button_criar_lote);
        botaoCriarLote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaLotesActivity.this, CriarLoteActivity.class);
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
                        Intent intentInicio = new Intent(ListaLotesActivity.this, HomeActivity.class);
                        startActivity(intentInicio);
                        break;
                    case R.id.menu_relatorio:
                    case R.id.menu_minhaconta:
                    case R.id.menu_contato:
                    case R.id.menu_ajuda:
                        Toast.makeText(ListaLotesActivity.this,"Essa tela ainda será desenvolvida",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_sairDaConta:
                        Intent intent = new Intent(ListaLotesActivity.this, TelaPrimeiroAcessoActivity.class);
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
