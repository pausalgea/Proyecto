package com.cidead.proyecto.electronicplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Clase MainActivity , clase que controla el menú principal de la aplicación hereda de AppCompatActivity
 * @author Paula Salicio
 */
public class MainActivity extends AppCompatActivity  {

    Button b1,b2,b3; //b1, b2, b3 son variables de tipo Button para controlar que botón pulsa el usuario
    @Override
    /**
     * Método onCreate se inicia al crear el activity
     * @param savedInstanceState parametro de tipo Bundle del método onCreate
     */
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //establecemos la orientación a vertical
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //establecemos el layout
        b1=(Button)findViewById(R.id.b_resistencias); //asociamos variable del primer botón con el id del layout
        b1.setOnClickListener(new OnClickListener() //si el usuario pulsa el primer botón
        {

            @Override
            /** Método onClick para b1 sirve para pasar al activity de las resistencias
             * @param View v
             */
            public void onClick(View v) {
                Intent intentResistencia=new Intent(MainActivity.this,ResistenciasActivity.class); //pasamos al Activity de las resistencias
                startActivity(intentResistencia);
            }
        });

        b2=(Button)findViewById(R.id.b_simbologia); //asociamos variable del segundo botón con el id del layout
        b2.setOnClickListener(new OnClickListener() //si el usuario pulsa el primer botón
        {

            @Override
            /** Método onClick para b2 sirve para pasar al activity de la simbología
             * @param View v
             */
            public void onClick(View v) {
                Intent intentSimbologia=new Intent(MainActivity.this,SimbologiaActivity.class); //pasamos al Activity de la simbología
                startActivity(intentSimbologia);
            }
        });

        b3=(Button)findViewById(R.id.b_ohm); //asociamos variable del tercer botón con el id del layout
        b3.setOnClickListener(new OnClickListener() //si el usuario pulsa el tercer botón
        {

            @Override
            /** Método onClick para b3 sirve para pasar al activity de la ley Ohm
             * @param View v
             */
            public void onClick(View v) {
                Intent intentOhm=new Intent(MainActivity.this,OhmActivity.class); //pasamos al Activity de la ley de Ohm
                startActivity(intentOhm);
            }
        });
    }
}