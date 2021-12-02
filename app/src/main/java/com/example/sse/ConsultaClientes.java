package com.example.sse;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class ConsultaClientes extends AppCompatActivity {

    List<Usuario> listaUsuario;
    RecyclerView recyclerViewUsuarios;
    Button bVolver;
    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_clientes);

        con = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);

        listaUsuario = new ArrayList<>();

        recyclerViewUsuarios = (RecyclerView) findViewById(R.id.rv);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        com.example.sse.ListaPersonasAdapter adapter;
        adapter = new ListaPersonasAdapter(this,listaUsuario);
        recyclerViewUsuarios.setAdapter(adapter);

        bVolver = findViewById(R.id.button2);

        bVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void consultarListaPersonas() { //
        SQLiteDatabase db = con.getReadableDatabase();

        Usuario usuario = null;
        // listaUsuarios=new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO, null);

        while (cursor.moveToNext()) {
            usuario = new Usuario();
            usuario.setId(cursor.getString(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            listaUsuario.add(usuario);
        }
    }
}