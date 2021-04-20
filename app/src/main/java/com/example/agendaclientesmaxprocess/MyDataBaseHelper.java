package com.example.agendaclientesmaxprocess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private  static  final String DATABASE_NAME = "AgendaClientes.db";
    public  static final  int DATABASE_VERSION = 1;

    private   static  final String TABLE_NAME = "agendaClientes";
    private   static  final String COLUMN_ID = "_id";
    private   static  final String COLUMN_NOME = "Nome";
    private   static  final String COLUMN_CPF = "CPF";
    private   static  final String COLUMN_DATAN = "Data_Nascimento";
    private   static  final String COLUMN_uf = "uf";
    private   static  final String COLUMN_TELEFONE = "Telfone";
    private   static  final String COLUMN_DCADASTRO = "Data_Cadastro";
    private   static  final String COLUMN_HORA = "Hora_Cadastro";




    public MyDataBaseHelper(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_CPF + " TEXT, " +
                COLUMN_DATAN + " TEXT, " +
                COLUMN_uf + " TEXT, " +
                COLUMN_TELEFONE + " TEXT, " +
                COLUMN_DCADASTRO + " TEXT, " +
                COLUMN_HORA + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

//Adiciona cliente

    void  addCliente (String nome, String cpf, String datan, String uf,  String telefone, String datac, String hora){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

                cv.put(COLUMN_NOME, nome);
                cv.put(COLUMN_CPF, cpf);
                cv.put(COLUMN_DATAN, datan);
                cv.put(COLUMN_uf, uf);
                cv.put(COLUMN_TELEFONE, telefone);
                cv.put(COLUMN_DCADASTRO, datac);
                cv.put(COLUMN_HORA, hora);
                long result = db.insert(TABLE_NAME, null, cv);
                if (result == -1) {
                    Toast.makeText(context, "Falha", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Sucesso", Toast.LENGTH_SHORT).show();
                }

                    }

    Cursor readAllData () {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if (db != null) {
           cursor=  db.rawQuery(query, null);
        }

        return cursor;


    }


}
