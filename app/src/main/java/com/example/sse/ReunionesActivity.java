package com.example.sse;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Locale;

public class ReunionesActivity extends AppCompatActivity {
    Button button5, bVolver;
    Cursor cursor;
    TextView documento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuniones);
        Toolbar topAppBar = (Toolbar) findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);
        button5 = (Button) findViewById(R.id.button5);
        documento = (TextView) findViewById(R.id.reuniones);
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

        int id = item.getItemId();

        switch (id) {
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

    public void llamarReuniones(View view){
        reuniones();
    }

    public void reuniones(){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(ReunionesActivity.this,
                    new String[]{Manifest.permission.READ_CALENDAR},
                    1);
            return;

        } else {
            String[] projection = new String[]{
                    CalendarContract.Events._ID,
                    CalendarContract.Events.TITLE,
                    CalendarContract.Events.DESCRIPTION};

            Uri llamadasUri = CalendarContract.Events.CONTENT_URI;

            Cursor cur;

            ContentResolver cr = getContentResolver();
            documento.setText("");

            cur = cr.query(llamadasUri, projection, null,null,null);

            if(cur.getCount() != 0){
                cur.moveToFirst();

                int ID;
                String title="";
                String descripcion="";

                int colID = cur.getColumnIndex(CalendarContract.Events._ID);
                int colTitle = cur.getColumnIndex(CalendarContract.Events.TITLE);
                int colDescription = cur.getColumnIndex(CalendarContract.Events.DESCRIPTION);

                do{
                    ID = cur.getInt(colID);
                    title = cur.getString(colTitle);
                    descripcion = cur.getString(colDescription);
                    Boolean contains;
                    if (title.contains("sse")) {
                        documento.append(ID + " - " + title + " - " + descripcion + "\n");
                    }
                } while (cur.moveToNext());

                cur.close();
            }
        }
    }
}