package com.cidead.proyecto.electronicplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Clase OhmActivity , clase que controla las operaciones a realizar para aplicar la ley de ohm hereda de AppCompatActivity
 * @author Paula Salicio
 */
public class OhmActivity extends AppCompatActivity {

    RadioGroup rg2; //variable radiogroup para controlar la opción escogida
    TextView tvSubtitulo; //texview para el subtitulo
    Button bAceptar,bSalir; //boton aceptar y salir
    int ids_opciones[]={R.id.rbIntensidad,R.id.rbVoltaje,R.id.rbResistencia}; //array con los ids del layout del radiobutton de intensidad, voltaje y resistencia

    @Override
    /**
     * Método onCreate se inicia al crear el activity
     * @param savedInstanceState parametro de tipo Bundle del método onCreate
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ohm);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //establecemos orientación vertical


        rg2=(RadioGroup)findViewById(R.id.rgOhm); //asociamos a las variables sus ids del layout
        tvSubtitulo=(TextView)findViewById(R.id.subtitulo_ohm);
        bAceptar=(Button)findViewById(R.id.bAceptar);
        bSalir=(Button)findViewById(R.id.bSalirCalculadoraOhm);

        bAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            /** Método onClick para bAceptar sirve para calular la magnitud desconocida mediante la ley de Ohm
             * @param View v
             */
            public void onClick(View v) {
                int opcionRadioGroup=rg2.getCheckedRadioButtonId(); //obtenemos la opción escogida por el usuario
                if(opcionRadioGroup==-1) //no se ha elegida ninguna opción
                {
                    Toast.makeText(OhmActivity.this,"No se ha elegido ninguna opción", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent=new Intent(OhmActivity.this,CalculoOhmActivity.class); //creamos un intent para pasar a la siguiente activity de calculo
                    if(ids_opciones[0]==opcionRadioGroup) //si se ha elegido la opción 0, la magnitud desconocida es la intensidad
                    {

                        intent.putExtra("magnitudDesconocida",0); //mandamos al siguiente activity la información de la opción escogida
                        startActivity(intent);

                    }

                    if(ids_opciones[1]==opcionRadioGroup) //si se ha elegido la opción 1, la magnitud desconocida es el voltaje
                    {
                        intent.putExtra("magnitudDesconocida",1); //mandamos al siguiente activity la información de la opción escogida
                        startActivity(intent);

                    }

                    if(ids_opciones[2]==opcionRadioGroup) //si se ha elegido la opción 2, la magnitud desconocida es la resistencia
                    {
                        intent.putExtra("magnitudDesconocida",2); //mandamos al siguiente activity la información de la opción escogida
                        startActivity(intent);

                    }
                }
            }
        });
        bSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            /** Método onClick para bSalir para volver a la activity anterior
             * @param View v
             */
            public void onClick(View v) { //si pusalmos el botón Salir volvemos a la activity anterior
                Intent intent=new Intent(OhmActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}