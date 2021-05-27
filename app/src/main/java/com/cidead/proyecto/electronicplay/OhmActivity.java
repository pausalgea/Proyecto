package com.cidead.proyecto.electronicplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class OhmActivity extends AppCompatActivity {

    RadioGroup rg2;
    TextView tvSubtitulo;
    Button bAceptar,bSalir;
    int ids_opciones[]={R.id.rbIntensidad,R.id.rbVoltaje,R.id.rbResistencia};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ohm);

        rg2=(RadioGroup)findViewById(R.id.rgOhm);
        tvSubtitulo=(TextView)findViewById(R.id.subtitulo_ohm);
        bAceptar=(Button)findViewById(R.id.bAceptar);
        bSalir=(Button)findViewById(R.id.bSalirCalculadoraOhm);

        bAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int opcionRadioGroup=rg2.getCheckedRadioButtonId();
                if(opcionRadioGroup==-1)
                {
                    Toast.makeText(OhmActivity.this,"No se ha elegido ninguna opción", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent=new Intent(OhmActivity.this,CalculoOhmActivity.class);
                    System.out.println("Opcion elegida: "+opcionRadioGroup);
                    if(ids_opciones[0]==opcionRadioGroup)
                    {

                        intent.putExtra("magnitudDesconocida",0);
                        startActivity(intent);

                    }

                    if(ids_opciones[1]==opcionRadioGroup)
                    {
                        intent.putExtra("magnitudDesconocida",1);
                        startActivity(intent);

                    }

                    if(ids_opciones[2]==opcionRadioGroup)
                    {
                        intent.putExtra("magnitudDesconocida",2);
                        startActivity(intent);

                    }
                }
            }
        });
        bSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OhmActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}