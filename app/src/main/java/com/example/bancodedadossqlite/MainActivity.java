package com.example.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            //Criar bando de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //Criar uma tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (nome VARCHAR, idade INT(3))");

            //Inserir os registros
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Jamilton Dasmasceno', 35)");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Bruno Rodrigues Bordin', 33)");

            //Recuperar os registros da tabela
            Cursor cursor = bancoDados.rawQuery("SELECT nome, idade  FROM pessoas", null);

            //Recuperar os indicices da tabela (as colunas do nome e idade)
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            //Iniciar cursor do zero
            cursor.moveToFirst();

            while (cursor != null){
                Log.i("RESULTADO - nome: ", cursor.getString(indiceNome));
                Log.i("RESULTADO - idade: ", cursor.getString(indiceIdade));

                //fazer o cursor mover para o proximo registro
                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}