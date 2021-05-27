package com.cidead.proyecto.electronicplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class CalculoOhmActivity extends AppCompatActivity {
    TextView tvMagn1,tvMagn2,tvResult;
    EditText etnMag1,etnMag2;
    Button bCalcular,bSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_ohm);
        bCalcular=(Button)findViewById(R.id.bCalcular);
        etnMag1=(EditText)findViewById(R.id.etnMagnitud1);
        etnMag2=(EditText)findViewById(R.id.etnMagnitud2);
        tvResult=(TextView)findViewById(R.id.tvResultadoOhm);
        bSalir=(Button)findViewById(R.id.bSalirOhm);

        tvMagn1=(TextView)findViewById(R.id.tvMagnitud1);
        tvMagn2=(TextView)findViewById(R.id.tvMagnitud2);


        etnMag1.setText("0");
        etnMag2.setText("0");



        int valor= getIntent().getIntExtra("magnitudDesconocida",-1);

        switch(valor)
        {
            case 0:
                tvMagn1.setText(getResources().getString(R.string.textoVoltajeOhm));
                tvMagn2.setText(getResources().getString(R.string.textoResistenciaOhm));
                break;
            case 1:
                tvMagn1.setText(getResources().getString(R.string.textoIntensidadOhm));
                tvMagn2.setText(getResources().getString(R.string.textoResistenciaOhm));
                break;
            case 2:
                tvMagn1.setText(getResources().getString(R.string.textoIntensidadOhm));
                tvMagn2.setText(getResources().getString(R.string.textoVoltajeOhm));
                break;

        }
        bCalcular.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                DecimalFormat df = new DecimalFormat("#.00");
                if(valor==0)
                {
                    float mag1=Float.parseFloat(String.valueOf(etnMag1.getText()));
                    float mag2=Float.parseFloat(String.valueOf(etnMag2.getText()));

                    tvResult.setText("El valor de la intensidad es: "+df.format(mag1/mag2)+"A");
                }
                if(valor==1)
                {
                    float mag1=Float.parseFloat(String.valueOf(etnMag1.getText()));
                    float mag2=Float.parseFloat(String.valueOf(etnMag2.getText()));

                    tvResult.setText("El valor del voltaje es: "+df.format(mag1*mag2)+"V");
                }

                if(valor==2)
                {
                    float mag1=Float.parseFloat(String.valueOf(etnMag1.getText()));
                    float mag2=Float.parseFloat(String.valueOf(etnMag2.getText()));

                    tvResult.setText("El valor de la resistencia es: "+df.format(mag2/mag1)+" \u2126");
                }

            }
        });

        bSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CalculoOhmActivity.this,OhmActivity.class);
                startActivity(intent);
            }
        });

    }
}