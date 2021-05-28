package com.cidead.proyecto.electronicplay;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Clase SplashScreenActivity , clase que muestra la imagen inicial de la aplicación
 * @author Paula Salicio
 */
public class SplashScreenActivity extends AppCompatActivity {
    /**
     * Método onCreate para la clase SplashScreenActivity
     * @param savedInstanceState parametro de tipo Bundle del método onCreate
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //establecemos para el SplashScreen orientación de retrato
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //ocultamos la barra de título
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash_screen);

        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                //llamamos a la siguiente actividad
                Intent mainIntent=new Intent().setClass(SplashScreenActivity.this,MainActivity.class);
                startActivity(mainIntent);

                //cerramos el splashscreen
                finish();
            }
        };

        //simulamos un tiempo de proceso que se muestre la pantalla del screensplash
        Timer timer=new Timer();
        timer.schedule(task,4000); //tiempo de espera en milisegundos
    }
}
