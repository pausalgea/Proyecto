package com.cidead.proyecto.electronicplay;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.cidead.proyecto.electronicplay.R.color.fondo_app;
import static com.cidead.proyecto.electronicplay.R.raw.right_answer;
import static com.cidead.proyecto.electronicplay.R.raw.wrong_answer;

public class SimbologiaActivity extends AppCompatActivity {

    ImageView iv1;
    TextView tvPuntuacion;
    Button b1;
    RadioGroup rg1;
    MediaPlayer mp1,mp2;

    int ids_respuestas[]={R.id.rbRespuesta1,R.id.rbRespuesta2,R.id.rbRespuesta3,R.id.rbRespuesta4};
    int respuesta_correcta;
    int contador=0;
    int puntuacion=0;



    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simbologia);
        iv1=(ImageView)findViewById(R.id.imageSimbologia);
        tvPuntuacion=(TextView)findViewById((R.id.tvPuntuacion));
        b1=(Button)findViewById(R.id.bSiguiente);
        rg1=(RadioGroup)findViewById(R.id.radioGroup);
        String []respuestas=getResources().getStringArray(R.array.respuestas);

        establecerImagenRespuesta(contador+1,respuestas[contador]);
        mp1=MediaPlayer.create(this, right_answer);
        mp2=MediaPlayer.create(this, wrong_answer);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int opcion_elegida=rg1.getCheckedRadioButtonId();
                int respuesta=-1;

                rg1.check(-1);

                for(int w=0;w<ids_respuestas.length;w++)
                {
                    if(ids_respuestas[w]==opcion_elegida)
                        respuesta=w;
                }
                if(respuesta==respuesta_correcta)
                {
                    Toast.makeText(SimbologiaActivity.this,"Respuesta correcta",Toast.LENGTH_SHORT).show();
                    mp1.start();
                    puntuacion+=100;
                    tvPuntuacion.setText("Puntuacion="+puntuacion);
                }
                else
                {
                    Toast.makeText(SimbologiaActivity.this,"Respuesta incorrecta",Toast.LENGTH_SHORT).show();
                    mp2.start();
                    puntuacion-=50;
                    if(puntuacion<50)
                        puntuacion=0;
                    tvPuntuacion.setText("Puntuacion="+puntuacion);
                }
                contador++;
                if(contador<10)
                    establecerImagenRespuesta(contador+1,respuestas[contador]);
                else
                {
                    b1.setText("Salir");
                    Intent intent=new Intent(SimbologiaActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });


    }

    public void establecerImagenRespuesta(int i, String respuestas)
    {
        int id = getResources().getIdentifier("simbologia"+i, "drawable", getPackageName());

        for(int z=0;z<ids_respuestas.length;z++)
        {
            RadioButton rb=(RadioButton)findViewById(ids_respuestas[z]);
            String []resp=respuestas.split(";");

            if(resp[z].charAt(0)=='*')
            {
                respuesta_correcta=z;
                rb.setText(resp[z].substring(1));
            }
            else
            {
                rb.setText(resp[z]);
            }

        }

        iv1.setImageResource(id);

    }
}