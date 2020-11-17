package com.example.nutrilac.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.nutrilac.model.Dieta;
import com.example.nutrilac.model.Lote;
import com.example.nutrilac.model.Usuario;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DadosOpenHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String BANCO_DE_DADOS_NOME = "Dados.db";
    private static final int BANCO_DE_DADOS_VERSAO = 1;

    public DadosOpenHelper(@Nullable Context context) {
        super(context, BANCO_DE_DADOS_NOME, null, BANCO_DE_DADOS_VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + "login" +
                " (" + "id_login" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email" + " VARCHAR(30), " +
                "senha" + " VARCHAR(8));";
        db.execSQL(query);

        query = "CREATE TABLE " + "produtor" +
                " (" + "id_produtor" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome" + " VARCHAR(100), " +
                "telefone" + " VARCHAR(20), " +
                "id_login" + " INTEGER, " + " FOREIGN KEY ("+"id_login"+") REFERENCES " +"login"+"("+"id_login"+"));";
        db.execSQL(query);

        query = "CREATE TABLE " + "lote" +
                " (" + "id_lote" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome" + " VARCHAR(40), " +
                "raca" + " VARCHAR(40), " +
                "quantidade_animais" + " INTEGER, " +
                "peso" + " FLOAT, " +
                "producao" + " FLOAT, " +
                "dias_lactacao" + " INTEGER, " +
                "dias_gestacao" + " INTEGER, " +
                "sistema_producao" + " VARCHAR(12), " +
                "numero_gestacoes" + " INTEGER, " +
                "id_produtor" + " INTEGER, " + " FOREIGN KEY ("+"id_produtor"+") REFERENCES " +"produtor"+"("+"id_produtor"+"));";
        db.execSQL(query);

        query = "CREATE TABLE " + "dieta" +
                " (" + "id_dieta" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome" + " VARCHAR(40), " +
                "nome_lote" + " VARCHAR(40), " +
                "id_lote" + " INTEGER, " + " FOREIGN KEY ("+"id_lote"+") REFERENCES " +"lote"+"("+"id_lote"+"));";
        db.execSQL(query);

        query = "INSERT INTO lote(nome, raca, quantidade_animais, peso, producao, dias_lactacao, dias_gestacao, sistema_producao, numero_gestacoes)" +
                "VALUES('Filhas da Estrela', 'Mestiço', 2, 800, 50, 180, 250, 'Pasto', 2);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "login");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + "produtor");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + "lote");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + "dieta");
        onCreate(db);
    }

    public List<Dieta> listarDietas(){
        List<Dieta> lista = new ArrayList<Dieta>();
        //String nomeLote = "lote";
        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM dieta",null);

        while (cursor.moveToNext()){
            /*String id_string = String.valueOf(cursor.getInt(2));
            Cursor cursorNomeLote = getReadableDatabase().rawQuery("SELECT nome FROM lote WHERE id_lote = ?", new String[] {id_string});
            cursorNomeLote.moveToFirst();
            if(cursorNomeLote.getCount() > 0){
                nomeLote = cursor.getString(1);
            }*/
            lista.add(new Dieta(cursor.getString(1),cursor.getInt(3),cursor.getString(2)));
        }
        return lista;
    }

    public List<Lote> listarLotes(){
        List<Lote> lista = new ArrayList<Lote>();

        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM lote",null);

        while (cursor.moveToNext()){
            lista.add(new Lote(cursor.getString(1),cursor.getString(2),cursor.getInt(3),
                    cursor.getFloat(4),cursor.getFloat(5),cursor.getInt(6),cursor.getInt(7),
                    cursor.getString(8),cursor.getInt(9)));
        }
        return lista;
    }

    public Integer retornaId_Lote(String nomeLote){
        Integer idEncontrado = 0;

        Cursor cursor = getReadableDatabase().rawQuery("SELECT nome FROM lote WHERE nome = ?", new String[] {nomeLote});
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            idEncontrado = cursor.getInt(0);
        }
        cursor.close();
        return idEncontrado;
    }

    public void adicionarDieta(Dieta dieta){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nome", dieta.getNome());
        cv.put("id_lote", dieta.getId_lote());
        cv.put("nome_lote",dieta.getNomeLote());
        db.insert("dieta",null, cv);
    }

    public void adicionarLote(Lote lote){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nome", lote.getNome());
        cv.put("raca", lote.getRaca());
        cv.put("quantidade_animais", lote.getQuantidade_animais());
        cv.put("peso", lote.getPeso());
        cv.put("producao", lote.getProducao());
        cv.put("dias_lactacao", lote.getDias_lactacao());
        cv.put("dias_gestacao", lote.getDias_gestacao());
        cv.put("sistema_producao", lote.getSistema_producao());
        cv.put("numero_gestacoes", lote.getnumero_gestacoes());
        cv.put("id_produtor",0);
        long result = db.insert("lote",null, cv);
        if(result == -1){
            Toast.makeText(context,"Erro", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Lote adicionado ao banco de dados com sucesso!", Toast.LENGTH_LONG).show();
        }
    }

    public void adicionarUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("email", usuario.getEmail());
        cv.put("senha", usuario.getSenha());
        long result = db.insert("login",null, cv);
        if(result == -1){
            Toast.makeText(context,"Erro", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Login adicionado com sucesso!", Toast.LENGTH_LONG).show();
        }

        ContentValues cv2 = new ContentValues();
        cv2.put("nome", usuario.getNome());
        cv2.put("telefone", usuario.getTelefone());
        result = db.insert("produtor",null, cv2);
        if(result == -1){
            Toast.makeText(context,"Erro", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Produtor adicionado com sucesso!", Toast.LENGTH_LONG).show();
        }
    }
}
