package com.example.nutrilac.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.nutrilac.database.DadosOpenHelper;
import com.example.nutrilac.model.Usuario;

public class UsuarioDAO {

    private SQLiteDatabase banco;
    private DadosOpenHelper conexao;

    public UsuarioDAO(Context context){
        conexao = new DadosOpenHelper(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("telefone", usuario.getTelefone());
        values.put("senha", usuario.getSenha());
        return banco.insert("usuario", null, values);
    }

    public void excluir(int id){

    }

    public void alterar(Usuario usuario){

    }
}
