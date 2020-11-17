package com.example.nutrilac.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrilac.R;
import com.google.android.material.navigation.NavigationView;

public class TelaPrimeiroAcessoActivity extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;
    private String m_Text = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_primeiro_acesso);

        configuraMenu();
        configuraBotoes();

        TextView esqueciSenha = findViewById(R.id.primeiratela_recuperar_senha);
        esqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarSenhaDialog();
            }
        });
    }

    private void recuperarSenhaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recuperar Senha");
        builder.setMessage("Para redefinir sua senha, informe e-mail cadastrado na sua conta e lhe enviaremos o link com as instruções.");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        builder.setView(input);
        input.setHint("E-Mail");



        // Set up the buttons
        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                Toast.makeText(TelaPrimeiroAcessoActivity.this, "Em breve você receberá um e-mail com instruções", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void configuraBotoes() {
        TextView entrar = findViewById(R.id.login_botao_entrar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaPrimeiroAcessoActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        TextView criarConta = findViewById(R.id.primeiro_acesso_botao_criar_conta);
        criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaPrimeiroAcessoActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });

        TextView entrarVisitante = findViewById(R.id.primeiro_acesso_botao_entrar_visitante);
        entrarVisitante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaPrimeiroAcessoActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
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
                    case R.id.menu_contato:
                        Toast.makeText(TelaPrimeiroAcessoActivity.this, "Essa tela ainda será desenvolvida", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_ajuda:
                        Toast.makeText(TelaPrimeiroAcessoActivity.this, "Essa tela ainda será desenvolvida", Toast.LENGTH_LONG).show();
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
