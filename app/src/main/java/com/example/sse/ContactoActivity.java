package com.example.sse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactoActivity extends AppCompatActivity {
    private Button bVolver;

    private ValueEventListener eventListener;
    private DatabaseReference dbPrediccion;

    private static String TAGLOG = "firebase-db";

    TextView lblHighDat, lblLowDat, lblAvgDat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        Toolbar topAppBar=(Toolbar) findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);
        bVolver = findViewById(R.id.button2);

        dbPrediccion = FirebaseDatabase.getInstance("https://sse-energia-default-rtdb.europe-west1.firebasedatabase.app").getReference().child("Precios");

        lblHighDat = (TextView) findViewById(R.id.lblHighDat);
        lblLowDat = (TextView) findViewById(R.id.lblLowDat);
        lblAvgDat = (TextView) findViewById(R.id.lblAvgDat);

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                lblAvgDat.setText("");
                lblHighDat.setText("");
                lblLowDat.setText("");

                if (dataSnapshot.child("Average").exists()){
                    lblAvgDat.setText(dataSnapshot.child("Average").getValue().toString());
                }
                if (dataSnapshot.child("Highest").exists()){
                    lblHighDat.setText(dataSnapshot.child("Highest").getValue().toString());
                }
                if (dataSnapshot.child("Lowest").exists()){
                    lblLowDat.setText(dataSnapshot.child("Lowest").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAGLOG, "ERROR", databaseError.toException());
            }
        };
        dbPrediccion.addValueEventListener(eventListener);


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
