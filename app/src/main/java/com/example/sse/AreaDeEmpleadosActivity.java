package com.example.sse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AreaDeEmpleadosActivity extends AppCompatActivity {
    private Button bVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_de_empleados);
        Toolbar topAppBar=(Toolbar) findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);
        bVolver = findViewById(R.id.button2);

        bVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bd_usuarios",null,1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        switch(id){
            case R.id.facebook:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/SSE.Energia"));
                startActivity(intent);
                return true;
            case R.id.twitter:
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/SSE_Energia"));
                startActivity(i);
            case R.id.pagWeb:
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.energiaclm.com"));
                startActivity(in);
            default:
                return false;
        }
    }




    public void accConsCliente(View view) {
        Intent i = new Intent(this, ConsultaClientes.class);
        startActivity(i);
    }
    public void accAgregarCliente(View view) {
        Intent i = new Intent(this, AgregarClientes.class);
        startActivity(i);
    }
    public void accReuniones(View view) {
        Intent i = new Intent(this, ReunionesActivity.class);
        startActivity(i);
    }
}