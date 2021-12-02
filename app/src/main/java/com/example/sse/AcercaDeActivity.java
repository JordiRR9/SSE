package com.example.sse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AcercaDeActivity extends AppCompatActivity {
    private Button bVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        Toolbar topAppBar=(Toolbar) findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);
        bVolver = findViewById(R.id.button2);

        bVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
    public void googleMaps(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("geo: 38.70110, -4.10156"));
        startActivity(intent);
    }

    public void lanzarSpot(View view){
        Intent i=new Intent(this,SpotActivity.class);
        startActivity(i);
    }

    public void mandarCorreo(View view){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Solicitud de presupuesto");
        intent.putExtra(Intent.EXTRA_TEXT,"Solicito que se me realice un presupuesto. \nAdjunto mi factura a continuación.");
        intent.putExtra(Intent.EXTRA_EMAIL,new String []{"eltepno@gmail.com"});
        startActivity(intent);
    }
    public void llamadaTelefono(View view){
        Intent intent=new Intent(Intent.ACTION_DIAL,Uri.parse("tel:666 66 66 66"));
        startActivity(intent);
    }

}
