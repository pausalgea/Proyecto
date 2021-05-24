package com.cidead.proyecto.electronicplay;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static com.cidead.proyecto.electronicplay.R.color.black;

public class ResistenciasActivity extends AppCompatActivity {

    Button salir;
    Button b1, b2, bmultiplicador, btolerancia;
    Button b1Imagen,b2Imagen,bMultiImagen,bToleranciaImagen;
    Spinner s1, s2, smulti, stolerancia;

    int banda1, banda2, multiplicador, tolerancia;

    TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resistencias);

        b1Imagen=(Button)findViewById(R.id.b_banda1_imagen);
        b2Imagen=(Button)findViewById(R.id.b_banda2_imagen);
        bMultiImagen=(Button)findViewById(R.id.b_multiplicador_imagen);
        bToleranciaImagen=(Button)findViewById(R.id.b_tolerancia_imagen);

        s1 = (Spinner) findViewById(R.id.spinner_banda1);
        s2 = (Spinner) findViewById(R.id.spinner_banda2);
        smulti = (Spinner) findViewById(R.id.spinner_multiplicador);
        stolerancia = (Spinner) findViewById((R.id.spinner_tolerancia));

        ArrayAdapter<CharSequence> adaptador_s1 = ArrayAdapter.createFromResource(this, R.array.colores_sin_negro, android.R.layout.simple_spinner_item);
        adaptador_s1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s1.setAdapter(adaptador_s1);
        s1.setSelection(0);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiarColor(b1, position+1);
                cambiarColor(b1Imagen,position+1);
                banda1=position+1;
                resultado.setText(new CalculoResistencia().calculo(banda1,banda2,multiplicador)+" \u2126"+ " y tolerancia: "+(stolerancia.getSelectedItem()).toString().substring(0,3));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adaptador_s2 = ArrayAdapter.createFromResource(this, R.array.colores, android.R.layout.simple_spinner_item);
        adaptador_s2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s2.setAdapter(adaptador_s2);
        s2.setSelection(0);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiarColor(b2, position);
                cambiarColor(b2Imagen,position);
                banda2=position;
                resultado.setText(new CalculoResistencia().calculo(banda1,banda2,multiplicador)+" \u2126"+ " y tolerancia: "+(stolerancia.getSelectedItem()).toString().substring(0,3));            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adaptador_smultiplicador = ArrayAdapter.createFromResource(this, R.array.colores, android.R.layout.simple_spinner_item);
        adaptador_smultiplicador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        smulti.setAdapter(adaptador_smultiplicador);
        smulti.setSelection(0);
        smulti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiarColor(bmultiplicador, position);
                cambiarColor(bMultiImagen,position);
                multiplicador=position;
                resultado.setText(new CalculoResistencia().calculo(banda1,banda2,multiplicador)+" \u2126"+ " y tolerancia: "+(stolerancia.getSelectedItem()).toString().substring(0,3));            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adaptador_tolerancia = ArrayAdapter.createFromResource(this, R.array.colores_tolerancia, android.R.layout.simple_spinner_item);
        adaptador_tolerancia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        stolerancia.setAdapter(adaptador_tolerancia);
        stolerancia.setSelection(1);
        stolerancia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiarColorTolerancia(btolerancia, position);
                cambiarColorTolerancia(bToleranciaImagen,position);
                tolerancia=position;
                resultado.setText(new CalculoResistencia().calculo(banda1,banda2,multiplicador)+" \u2126"+ " y tolerancia: "+(stolerancia.getSelectedItem()).toString().substring(0,3));            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        salir = (Button) findViewById(R.id.b_salir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResistenciasActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        b1 = (Button) findViewById(R.id.b_banda1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1.performClick();
            }
        });

        b2 = (Button) findViewById(R.id.b_banda2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s2.performClick();
            }
        });
        bmultiplicador = (Button) findViewById(R.id.b_multiplicador);
        bmultiplicador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smulti.performClick();
            }
        });
        btolerancia = (Button) findViewById(R.id.b_tolerancia);
        btolerancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stolerancia.performClick();
            }
        });
        resultado=(TextView)findViewById(R.id.tvResultado);



    }


    public void cambiarColor(Button button, int position) {
        switch (position) {
            case 0:
                button.setBackgroundColor(getResources().getColor(R.color.negro));
                break;
            case 1:
                button.setBackgroundColor(getResources().getColor(R.color.marron));
                break;
            case 2:
                button.setBackgroundColor(getResources().getColor(R.color.rojo));
                break;
            case 3:
                button.setBackgroundColor(getResources().getColor(R.color.naranja));
                break;
            case 4:
                button.setBackgroundColor(getResources().getColor(R.color.amarillo));
                break;
            case 5:
                button.setBackgroundColor(getResources().getColor(R.color.verde));
                break;
            case 6:
                button.setBackgroundColor(getResources().getColor(R.color.azul));
                break;
            case 7:
                button.setBackgroundColor(getResources().getColor(R.color.morado));
                break;
            case 8:
                button.setBackgroundColor(getResources().getColor(R.color.gris));
                break;
            case 9:
                button.setBackgroundColor(getResources().getColor(R.color.blanco));

        }
    }

    public void cambiarColorTolerancia(Button button, int position) {
        switch (position) {
            case 0:
                button.setBackgroundColor(getResources().getColor(R.color.rojo));
                break;
            case 1:
                button.setBackgroundColor(getResources().getColor(R.color.dorado));
                break;
            case 2:
                button.setBackgroundColor(getResources().getColor(R.color.plata));
                break;

        }
    }

}