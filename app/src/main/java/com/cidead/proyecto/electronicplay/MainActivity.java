package com.cidead.proyecto.electronicplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.b_resistencias);
        b1.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intentResistencia=new Intent(MainActivity.this,ResistenciasActivity.class);
                startActivity(intentResistencia);
            }
        });

        b2=(Button)findViewById(R.id.b_simbologia);
        b2.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intentResistencia=new Intent(MainActivity.this,SimbologiaActivity.class);
                startActivity(intentResistencia);
            }
        });

        b3=(Button)findViewById(R.id.b_ohm);
        b3.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent intentResistencia=new Intent(MainActivity.this,OhmActivity.class);
                startActivity(intentResistencia);
            }
        });
    }
}