package com.example.sse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class SpotActivity extends AppCompatActivity implements SurfaceHolder.Callback {


    MediaPlayer mediaPlayer = null;
    SurfaceView superficie = null;
    Button botonCargar, botonPausar, botonParar, botonReproducir = null;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spot);
        Toolbar topAppBar=(Toolbar) findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        botonCargar = findViewById(R.id.botonCargar);
        botonPausar = findViewById(R.id.botonPausar);
        botonParar = findViewById(R.id.botonParar);
        botonReproducir = findViewById(R.id.botonReproducir);

        botonCargar.setEnabled(true);
        botonPausar.setEnabled(false);
        botonParar.setEnabled(false);
        botonReproducir.setEnabled(false);

        inicializarSuperficieReproductor();

        mediaPlayer = new MediaPlayer();

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                botonCargar.setEnabled(false);
                botonPausar.setEnabled(false);
                botonParar.setEnabled(false);
                botonReproducir.setEnabled(true);

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();

                botonCargar.setEnabled(true);
                botonPausar.setEnabled(false);
                botonParar.setEnabled(false);
                botonReproducir.setEnabled(false);
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

    @Override
    protected void onPause() {
        super.onPause();

        if (mediaPlayer != null) {
            mediaPlayer.pause();
            pos = mediaPlayer.getCurrentPosition();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle estadoGuardado) {
        super.onSaveInstanceState(estadoGuardado);

        estadoGuardado.putInt("posicion", pos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle estadoGuardado) {
        super.onRestoreInstanceState(estadoGuardado);

        if (estadoGuardado != null && mediaPlayer != null) {
            pos = estadoGuardado.getInt("posicion");
        }
    }

    public void cargarMultimedia(View v) {
        mediaPlayer.reset();

        try {
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reproducir(View v) {
        mediaPlayer.start();
        botonCargar.setEnabled(false);
        botonPausar.setEnabled(true);
        botonParar.setEnabled(true);
        botonReproducir.setEnabled(false);
    }

    public void paraReproduccion(View v) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            botonCargar.setEnabled(true);
            botonPausar.setEnabled(false);
            botonParar.setEnabled(false);
            botonReproducir.setEnabled(false);

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        }
    }

    public void pausarReproduccion(View v) {
        botonCargar.setEnabled(false);
        botonParar.setEnabled(true);
        botonReproducir.setEnabled(true);
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            botonPausar.setEnabled(false);
        } else {
            mediaPlayer.start();
            botonPausar.setEnabled(true);
        }
    }

    protected void inicializarSuperficieReproductor() {
        superficie = findViewById(R.id.superficie);

        SurfaceHolder holder = superficie.getHolder();

        holder.addCallback((SurfaceHolder.Callback) this);
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        mediaPlayer.release();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer.setDisplay(holder);
    }
}
