package com.cidead.proyecto.electronicplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

/**
 * Clase CalculoOhmActivity , clase que calculo las magnitudes aplicando la ley de Ohm hereda de AppCompatActivity
 * @author Paula Salicio
 */
public class CalculoOhmActivity extends AppCompatActivity {
    TextView tvMagn1,tvMagn2,tvResult; //variables TextView para la magnitud 1 y magnitud 2, y para mostrar el resultado del calculo
    EditText etnMag1,etnMag2; //variables EditText donde el usuario va a introducir los valores de las 2 magnitudes
    Button bCalcular,bSalir; //variables boton para calcular y salir del activity
    /** Método onCreate del Activity
     * @param savedInstanceState parametro de tipo Bundle del método onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_ohm);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        bCalcular=(Button)findViewById(R.id.bCalcular); //asignamos a las variables el id del layout
        etnMag1=(EditText)findViewById(R.id.etnMagnitud1);
        etnMag2=(EditText)findViewById(R.id.etnMagnitud2);
        tvResult=(TextView)findViewById(R.id.tvResultadoOhm);
        bSalir=(Button)findViewById(R.id.bSalirOhm);

        tvMagn1=(TextView)findViewById(R.id.tvMagnitud1);
        tvMagn2=(TextView)findViewById(R.id.tvMagnitud2);


        etnMag1.setText("0"); //establecemos inicialmente los valores de las magnitudes a 0
        etnMag2.setText("0");



        int valor= getIntent().getIntExtra("magnitudDesconocida",-1); //obtenemos en un entero el valor de la clave magnitudDesconocida
        // que nos permite identificar que variable deseamos calcular

        switch(valor) //hacemos un switch dependiendo del valor, si es 0 queremos conocer la intensidad
        {
            case 0:
                tvMagn1.setText(getResources().getString(R.string.textoVoltajeOhm)); //establecemos el texto dependiendo de la magnitud a buscar para los TextViews
                tvMagn2.setText(getResources().getString(R.string.textoResistenciaOhm));
                break;
            case 1: //si es 1 queremos identificar el voltaje
                tvMagn1.setText(getResources().getString(R.string.textoIntensidadOhm));
                tvMagn2.setText(getResources().getString(R.string.textoResistenciaOhm));
                break;
            case 2: //si es 2 queremos identificar la resistencia
                tvMagn1.setText(getResources().getString(R.string.textoIntensidadOhm));
                tvMagn2.setText(getResources().getString(R.string.textoVoltajeOhm));
                break;

        }
        bCalcular.setOnClickListener(new View.OnClickListener() { //cuando el usuario pulse el boton Calcular
            @Override
            /** Método onClick para bCalcular sirve para calcular los valores de la ley de Ohm cuando el usuario pulsa el botón Calcular
             * @param View v
             */
            public void onClick(View v) {
                DecimalFormat df = new DecimalFormat("#.00"); //establecemos el formato a dos decimales para mostrar el resultado
                if(valor==0) //si la intensidad es la buscada, obtenemos el valor dividiendo mag1 entre mag2
                {
                    float mag1=Float.parseFloat(String.valueOf(etnMag1.getText())); //obtenemos los valores introducidos por usuario para mag1 y mag2
                    float mag2=Float.parseFloat(String.valueOf(etnMag2.getText()));

                    tvResult.setText("El valor de la intensidad es: "+df.format(mag1/mag2)+"A");
                }
                if(valor==1) //si el voltaje es el buscado, obtenemos el valor al multiplicar mag1*mag2
                {
                    float mag1=Float.parseFloat(String.valueOf(etnMag1.getText())); //obtenemos los valores introducidos por usuario para mag1 y mag2
                    float mag2=Float.parseFloat(String.valueOf(etnMag2.getText()));

                    tvResult.setText("El valor del voltaje es: "+df.format(mag1*mag2)+"V");
                }

                if(valor==2) //si la resistencia es la buscada obtenemos el valor al dividir mag2/mag1
                {
                    float mag1=Float.parseFloat(String.valueOf(etnMag1.getText())); //obtenemos los valores introducidos por usuario para mag1 y mag2
                    float mag2=Float.parseFloat(String.valueOf(etnMag2.getText()));

                    tvResult.setText("El valor de la resistencia es: "+df.format(mag2/mag1)+" \u2126");
                }

            }
        });

        bSalir.setOnClickListener(new View.OnClickListener() { //si el usuario pulsa el boton salir
            @Override
            /** Método onClick para bSalir sirve para volver al activity anterior
             * @param View v
             */
            public void onClick(View v) {
                Intent intent=new Intent(CalculoOhmActivity.this,OhmActivity.class);
                startActivity(intent);
            }
        });

    }
}