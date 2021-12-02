package com.example.sse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar topAppBar=(Toolbar) findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);
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

    public void accWeb(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.energiaclm.com"));
        startActivity(intent);
    }
    public void accTw(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/SSE_Energia"));
        startActivity(intent);
    }
    public void accFb(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/SSE.Energia"));
        startActivity(intent);
    }
    public void lanzarLogin(View view){
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
    }
    public void lanzarContacto(View view){
        Intent i=new Intent(this,ContactoActivity.class);
        startActivity(i);
    }
    public void lanzarAcercaDe(View view){
        Intent i=new Intent(this,AcercaDeActivity.class);
        startActivity(i);
    }
}