package com.example.sse;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarClientes extends AppCompatActivity {
    private Button bVolver;
    ActivityResultLauncher<Intent> activityResultLauncher;

    EditText editTextId, editTextNombre, editTextTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_clientes);
        Toolbar topAppBar=(Toolbar) findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTextTelefono = (EditText) findViewById(R.id.editTextTelefono);

        bVolver = findViewById(R.id.button2);

        bVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        loadPref();
                    }
                });
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
    public void onClick(View view) {

        registrarClientes();
    }

    private void registrarClientes() {
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this, "bd_usuarios",null,1);

        SQLiteDatabase db=con.getWritableDatabase();

        //Insert into usuario(id, nombre,telefono) values (123,'Jordi','666666666');

        String insert="INSERT INTO "+Utilidades.TABLA_USUARIO
                +" ( "
                +Utilidades.CAMPO_ID+","
                +Utilidades.CAMPO_NOMBRE+","
                +Utilidades.CAMPO_TELEFONO+")"+
                "VALUES ("+editTextId.getText().toString()+", '"
                +editTextNombre.getText().toString()+"','"
                +editTextTelefono.getText().toString()+"')";

        db.execSQL(insert);

        db.close();
    }
    public void loadPref(){
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String prefijoPreference;

        prefijoPreference = mySharedPreferences.getString("prefijo", "+34");

        editTextTelefono.setText(prefijoPreference);
    }
    public void abrirPreferencias(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        activityResultLauncher.launch(intent);
    }
}