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

import static com.cidead.proyecto.electronicplay.R.raw.right_answer;
import static com.cidead.proyecto.electronicplay.R.raw.wrong_answer;

/**
 * Clase SimbologiaActivity , clase que muestra simbologia electrónica de la aplicación hereda de AppCompatActivity
 * @author Paula Salicio
 */
public class SimbologiaActivity extends AppCompatActivity {

    ImageView iv1; //variable ImageView para mostrar la imagen de la simbología
    TextView tvPuntuacion; //TextView para mostrar la puntuacion obtenida
    Button b1; //variable Button
    RadioGroup rg1; //RadioGroup para escoger un valor entre 4 opciones
    MediaPlayer mp1,mp2; //variables Mediaplayer para reproducir audio según la respuesta sea correcta o incorrecta

    int ids_respuestas[]={R.id.rbRespuesta1,R.id.rbRespuesta2,R.id.rbRespuesta3,R.id.rbRespuesta4}; //array de ints con los ids de los RadioButtons de las respuestas
    int respuesta_correcta; //entero para indicar cual es la respuesta correcta
    int contador=0; //entero que sirve de contador
    int puntuacion=0; //entero que acumula la puntuación

    @Override
    /**
     * Método onCreate se inicia al crear el activity
     * @param savedInstanceState parametro de tipo Bundle del método onCreate
     */
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //establecemos la orientación vertical retrato
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simbologia); //establecemos el layout para el activity
        iv1=(ImageView)findViewById(R.id.imageSimbologia); //establecemos la variable con su id del layout
        tvPuntuacion=(TextView)findViewById((R.id.tvPuntuacion)); //establecemos la variable con su id del layout
        b1=(Button)findViewById(R.id.bSiguiente); //establecemos la variable con su id del layout
        rg1=(RadioGroup)findViewById(R.id.radioGroup); //establecemos la variable con su id del layout
        String []respuestas=getResources().getStringArray(R.array.respuestas); //obtenemos en un array de string las respuestas escritas en el fichero strings.xml

        establecerImagenRespuesta(contador+1,respuestas[contador]);  //las imagenes de la simbologia estan nombradas como "simbologia1" "simbologia2" así que para identificar
        //cada imagen con la respuesta adecuada usamos la variable contador añadiendo 1 , ya que el valor inicial de contador es 0
        mp1=MediaPlayer.create(this, right_answer); //establecemos mp1 el sonido de respuesta correcta
        mp2=MediaPlayer.create(this, wrong_answer); //establecemos mp2 el sonido de respuesta incorrecta

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            /** Método onClick para b1 sirve para pasar a la siguiente imagen de simbologia
             * @param View v
             */

            public void onClick(View v) {
                int opcion_elegida=rg1.getCheckedRadioButtonId(); //obtenemos la opción elegida del radioGroup
                int respuesta=-1;

                rg1.check(-1); //inicialmente establecemos que ningún boton del radioGroup esté marcadado

                for(int w=0;w<ids_respuestas.length;w++) //recorremos con un for las 4 opciones para comprobar que respuesta ha escogido el usuario
                {
                    if(ids_respuestas[w]==opcion_elegida)
                        respuesta=w;
                }
                if(respuesta==respuesta_correcta) //si la respuesta escogida es al respuesta correcta
                {
                    Toast.makeText(SimbologiaActivity.this,"Respuesta correcta",Toast.LENGTH_SHORT).show(); //mostramos con un Toast la respuesta correcta
                    mp1.start(); //reproducimos el sonido de respuesta correcta
                    puntuacion+=100; //aumentamos la puntuacion en 100 puntos
                    tvPuntuacion.setText("Puntuacion="+puntuacion); //escribimos la puntuación en el editText
                }
                else //si la respuesta es incorrecta
                {
                    Toast.makeText(SimbologiaActivity.this,"Respuesta incorrecta",Toast.LENGTH_SHORT).show(); //mostramos con un Toast que la opción elegida es incorrecta
                    mp2.start(); //reproducimos el sonido de respuesta incorrecta
                    puntuacion-=50; //restamos 50 puntos al acumulado de puntuacion
                    if(puntuacion<50) //en el caso de que la puntuacion sea menos de 50 no restaremos más
                        puntuacion=0;
                    tvPuntuacion.setText("Puntuacion="+puntuacion);
                }
                contador++; //aumentamos el valor de contador para las siguientes imagenes
                if(contador<10) //si el contador es menor que 10 establecemos nueva imagen y nuevas respuestas
                    establecerImagenRespuesta(contador+1,respuestas[contador]);
                else //si hemos llegado a 10 es que ya no hay más imágenes ni respuestas y salimos del activity, y vamos al activity anterior
                {
                    b1.setText("Salir");
                    Intent intent=new Intent(SimbologiaActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });


    }
    /** Método establecerImagenRespuesta sirve para establecer una nueva imagen de simbología y sus correspondientes respuestas
     * @param  i entero que identifica el número de la imagen de simbologia
     * @param respuestas String con las respuestas de cada simbologia
     */
    public void establecerImagenRespuesta(int i, String respuestas)
    {
        int id = getResources().getIdentifier("simbologia"+i, "drawable", getPackageName()); //obtenemos el id e la imagen de simbologia correspondiente

        for(int z=0;z<ids_respuestas.length;z++) //recorremos con un for y establecemos para cada radiobutton las respuestas que correspondan
        {
            RadioButton rb=(RadioButton)findViewById(ids_respuestas[z]);
            String []resp=respuestas.split(";"); //obtenemos en un array de string, las respuestas que estan escritas co un ; de separación en el archivo strings.xml

            if(resp[z].charAt(0)=='*') //la respuesta que tenga un asterisco como carácter inicial es la respuesta correcta
            {
                respuesta_correcta=z;
                rb.setText(resp[z].substring(1)); //quitamos el asterisco de la respuesta correcta
            }
            else
            {
                rb.setText(resp[z]); //escribimos la respuesta en los radiobuttons
            }

        }

        iv1.setImageResource(id); //establecemos la imagen para el ImageView

    }
}