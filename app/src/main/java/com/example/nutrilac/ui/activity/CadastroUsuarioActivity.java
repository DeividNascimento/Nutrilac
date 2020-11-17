package com.example.nutrilac.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutrilac.R;
import com.example.nutrilac.database.DadosOpenHelper;
import com.example.nutrilac.model.Usuario;
import com.google.android.material.navigation.NavigationView;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private DrawerLayout mdrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigatView;

    private EditText cadastro_nome;
    private EditText cadastro_email;
    private EditText cadastro_telefone;
    private EditText cadastro_senha;
    private EditText cadastro_confirma_senha;
    private boolean verificaCampos;

    public static final String CADASTRO_DE_USUÁRIO = "Cadastrar usuário";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        setTitle(CADASTRO_DE_USUÁRIO);

        configuraMenu();

        cadastro_nome = findViewById(R.id.cadastro_nome);
        cadastro_email = findViewById(R.id.cadastro_email);
        cadastro_telefone = findViewById(R.id.cadastro_telefone);
        cadastro_senha = findViewById(R.id.cadastro_senha);
        cadastro_confirma_senha = findViewById(R.id.cadastro_confirma_senha);

        final TextView textCriarConta = findViewById(R.id.cadastro_botao_confirmar);
        textCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaCampos();
                if (verificaCampos == false) {
                    salvar();
                    Intent intent = new Intent(CadastroUsuarioActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void salvar(){
        Usuario a = new Usuario();
        a.setNome(cadastro_nome.getText().toString().trim());
        a.setEmail(cadastro_email.getText().toString().trim());
        a.setTelefone(cadastro_telefone.getText().toString().trim());
        a.setSenha(cadastro_senha.getText().toString().trim());
        DadosOpenHelper meuBD = new DadosOpenHelper(CadastroUsuarioActivity.this);
        meuBD.adicionarUsuario(a);
    }

    private void validaCampos() {
        boolean res = false;
        this.verificaCampos = false;
        String nome = cadastro_nome.getText().toString();
        String email = cadastro_email.getText().toString();
        String senha = cadastro_senha.getText().toString();
        String confirmaSenha = cadastro_confirma_senha.getText().toString();

        if (res = isCampoVazio(nome)) {
            cadastro_nome.requestFocus();
        } else if (res = isCampoVazio(email)) {
            cadastro_email.requestFocus();
        } else if (isEmailValido(email)) {
            cadastro_email.requestFocus();
        } else if (res = isCampoVazio(senha)) {
            cadastro_senha.requestFocus();
        } else if (res = isCampoVazio(confirmaSenha)) {
            cadastro_confirma_senha.requestFocus();
        } else if (funcVerificaSenha(senha, confirmaSenha)) {
            cadastro_senha.requestFocus();
        }
        if (res == true || isEmailValido(email) == true || funcVerificaSenha(senha, confirmaSenha) == true) {
            this.verificaCampos = true;
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Atenção");
            if(res){
                dlg.setMessage("Existem campos em branco");
            }else if(isEmailValido(email)){
                dlg.setMessage("O e-mail informado não é válido");
            }else if(funcVerificaSenha(senha, confirmaSenha)){
                dlg.setMessage("As senhas informadas são diferentes");
            }
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }

    }

    private boolean isEmailValido(String email) {
        boolean resultado = (!Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    private boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    boolean funcVerificaSenha(String senhaDigitada1, String senhaDigitada2) {
        if (senhaDigitada1.equals(senhaDigitada2)) {
            return false;
        } else {
            return true;
        }
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
                    case R.id.menu_contato:
                        Toast.makeText(CadastroUsuarioActivity.this,"Essa tela ainda será desenvolvida",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_ajuda:
                        Toast.makeText(CadastroUsuarioActivity.this,"Essa tela ainda será desenvolvida",Toast.LENGTH_LONG).show();
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