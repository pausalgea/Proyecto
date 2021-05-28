package com.cidead.proyecto.electronicplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Clase ResistenciasActivity , clase que controla las operaciones a hacer con resistencias hereda de AppCompatActivity
 * @author Paula Salicio
 */
public class ResistenciasActivity extends AppCompatActivity {

    Button salir; //variable boton para salir
    Button b1, b2, bmultiplicador, btolerancia; //variables botones para banda1, banda2, y banda del multiplicador
    Button b1Imagen,b2Imagen,bMultiImagen,bToleranciaImagen; //botones para cambiar el color de fondo de banda1, banda2, banda multiplicador y banda tolerancia
    Spinner s1, s2, smulti, stolerancia; //Variables Spinner par mostrar un listado de valores para cada banda

    int banda1, banda2, multiplicador, tolerancia; //enteros para las bandas, multiplicador y tolerancia

    TextView resultado; //textview variable para mostrar resultado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resistencias);

        b1Imagen=(Button)findViewById(R.id.b_banda1_imagen); //establecemos para las variables sus ids del layout
        b2Imagen=(Button)findViewById(R.id.b_banda2_imagen);
        bMultiImagen=(Button)findViewById(R.id.b_multiplicador_imagen);
        bToleranciaImagen=(Button)findViewById(R.id.b_tolerancia_imagen);

        s1 = (Spinner) findViewById(R.id.spinner_banda1);
        s2 = (Spinner) findViewById(R.id.spinner_banda2);
        smulti = (Spinner) findViewById(R.id.spinner_multiplicador);
        stolerancia = (Spinner) findViewById((R.id.spinner_tolerancia));

        ArrayAdapter<CharSequence> adaptador_s1 = ArrayAdapter.createFromResource(this, R.array.colores_sin_negro, android.R.layout.simple_spinner_item); //creamos un adaptador que se mostrará en la primera banda para el spinner sin el color negro
        adaptador_s1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //ya que el color negro no puede estar en la primera banda nunca

        s1.setAdapter(adaptador_s1); //establecemos el adaptador para el spinner de la banda 1
        s1.setSelection(0); //y elegimos como valor por defecto la opción 0 , correspondiente al color marrón
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            /** Método onItemSelected para cuando seleccionadmos un item del spinner s1 de la banda1
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { // si seleccionamos una opción del spinner
                cambiarColor(b1, position+1); //cambiaremos el color del botón de la banda1 y del botón de la imagen
                cambiarColor(b1Imagen,position+1);
                banda1=position+1;
                //el resultado lo establecermos llamando al metodo calculo con los valores correpondientes en ese momento y mostramos el resultado en ohmios y el valor de la tolerancia
                resultado.setText(new CalculoResistencia().calculo(banda1,banda2,multiplicador)+" \u2126"+ " y tolerancia: "+(stolerancia.getSelectedItem()).toString().substring(0,3));
            }
            /** Método onNothingSelected para cuando no seleccionadmos ningún item
             * @param parent
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) { //no hacemos nada

            }
        });

        //hacemos lo mismo para la banda 2 , multiplicador y tolerancia, solo que aquí si tenemos en cuenta el color negro
        ArrayAdapter<CharSequence> adaptador_s2 = ArrayAdapter.createFromResource(this, R.array.colores, android.R.layout.simple_spinner_item);
        adaptador_s2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s2.setAdapter(adaptador_s2);
        s2.setSelection(0);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            /** Método onItemSelected para cuando seleccionadmos un item del spinner s2 de la banda2
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiarColor(b2, position);
                cambiarColor(b2Imagen,position);
                banda2=position;
                resultado.setText(new CalculoResistencia().calculo(banda1,banda2,multiplicador)+" \u2126"+ " y tolerancia: "+(stolerancia.getSelectedItem()).toString().substring(0,3));            }

            @Override
            /** Método onNothingSelected para cuando no seleccionadmos ningún item
             * @param parent
             */
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adaptador_smultiplicador = ArrayAdapter.createFromResource(this, R.array.colores, android.R.layout.simple_spinner_item);
        adaptador_smultiplicador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        smulti.setAdapter(adaptador_smultiplicador);
        smulti.setSelection(0);
        smulti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            /** Método onItemSelected para cuando seleccionadmos un item del spinner smulti del multiplicador
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiarColor(bmultiplicador, position);
                cambiarColor(bMultiImagen,position);
                multiplicador=position;
                resultado.setText(new CalculoResistencia().calculo(banda1,banda2,multiplicador)+" \u2126"+ " y tolerancia: "+(stolerancia.getSelectedItem()).toString().substring(0,3));            }

            @Override
            /** Método onNothingSelected para cuando no seleccionadmos ningún item
             * @param parent
             */
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //creamos un adaptador para la tolerancia que solo tiene 3 valores: rojo, dorado y plata
        ArrayAdapter<CharSequence> adaptador_tolerancia = ArrayAdapter.createFromResource(this, R.array.colores_tolerancia, android.R.layout.simple_spinner_item);
        adaptador_tolerancia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        stolerancia.setAdapter(adaptador_tolerancia);
        stolerancia.setSelection(1);
        stolerancia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            /** Método onItemSelected para cuando seleccionadmos un item del spinner stolerancia de la banda de la tolerancia
             * @param parent
             * @param view
             * @param position
             * @param id
             */
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiarColorTolerancia(btolerancia, position);
                cambiarColorTolerancia(bToleranciaImagen,position);
                tolerancia=position;
                resultado.setText(new CalculoResistencia().calculo(banda1,banda2,multiplicador)+" \u2126"+ " y tolerancia: "+(stolerancia.getSelectedItem()).toString().substring(0,3));            }

            @Override
            /** Método onNothingSelected para cuando no seleccionadmos ningún item
             * @param parent
             */
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        salir = (Button) findViewById(R.id.b_salir); //botón Salir
        salir.setOnClickListener(new View.OnClickListener() { //si pulsamos el botón salir, vamos al activity anterior
            @Override
            /** Método onClick para salir sirve para volver al activity anterior
             * @param View v
             */
            public void onClick(View v) {
                Intent intent = new Intent(ResistenciasActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        b1 = (Button) findViewById(R.id.b_banda1); //en el caso de que el usuario pulse el boton 1 se abrirá el spinner de la banda 1 para escoger color
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            /** Método onClick para b1 sirve para abrir el spinner correspondiente
             * @param View v
             */
            public void onClick(View v) {
                s1.performClick();
            }
        });

        b2 = (Button) findViewById(R.id.b_banda2); //en el caso de que el usuario pulse el boton 2 se abrirá el spinner de la banda 1 para escoger color
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            /** Método onClick para b2 sirve para abrir el spinner correspondiente
             * @param View v
             */
            public void onClick(View v) {
                s2.performClick();
            }
        });
        bmultiplicador = (Button) findViewById(R.id.b_multiplicador); //en el caso de que el usuario pulse el boton del multiplicador se abrirá el spinner para escoger color
        bmultiplicador.setOnClickListener(new View.OnClickListener() {
            @Override
            /** Método onClick para bmultiplicador sirve para abrir el spinner correspondiente
             * @param View v
             */
            public void onClick(View v) {
                smulti.performClick();
            }
        });
        btolerancia = (Button) findViewById(R.id.b_tolerancia); //en el caso de que el usuario pulse el boton de la tolerancia se abrirá el spinner de la tolerancia para escoger color
        btolerancia.setOnClickListener(new View.OnClickListener() {
            @Override
            /** Método onClick para btolerancia sirve para abrir el spinner correspondiente
             * @param View v
             */
            public void onClick(View v) {
                stolerancia.performClick();
            }
        });
        resultado=(TextView)findViewById(R.id.tvResultado);  //establecemos el id de resultado con el id del layout



    }
    /**
     * Método cambiarColor metodo para cambiar el color de un botón sabiendo la opción escogida
     * @param button botón ue vamos a cambiar el color de fondo
     * @param position opción escogida por el usuario que establece que color hemos de poner
     *
     */

    public void cambiarColor(Button button, int position) {
        switch (position) {
            case 0: //si posicion elegida es 0, color negro
                button.setBackgroundColor(getResources().getColor(R.color.negro));
                break;
            case 1: //si posicion elegida es 1, color marrón
                button.setBackgroundColor(getResources().getColor(R.color.marron));
                break;
            case 2: //si posicion elegida es 2, color rojo
                button.setBackgroundColor(getResources().getColor(R.color.rojo));
                break;
            case 3: //si posicion elegida es 3, color naranja
                button.setBackgroundColor(getResources().getColor(R.color.naranja));
                break;
            case 4: //si posicion elegida es 4, color amarillo
                button.setBackgroundColor(getResources().getColor(R.color.amarillo));
                break;
            case 5: //si posicion elegida es 5, color verde
                button.setBackgroundColor(getResources().getColor(R.color.verde));
                break;
            case 6: //si posicion elegida es 6, color azul
                button.setBackgroundColor(getResources().getColor(R.color.azul));
                break;
            case 7: //si posicion elegida es 7, color morado
                button.setBackgroundColor(getResources().getColor(R.color.morado));
                break;
            case 8: //si posicion elegida es 8, color gris
                button.setBackgroundColor(getResources().getColor(R.color.gris));
                break;
            case 9: //si posicion elegida es 9, color blanco
                button.setBackgroundColor(getResources().getColor(R.color.blanco));

        }
    }
    /**
     * Método cambiarColor metodo para cambiar el color del botón de la tolerancia sabiendo la opción escogida
     * @param button botón ue vamos a cambiar el color de fondo
     * @param position opción escogida por el usuario que establece que color hemos de poner
     *
     */

    public void cambiarColorTolerancia(Button button, int position) {
        switch (position) {
            case 0: //si opción elegido es 0, color rojo
                button.setBackgroundColor(getResources().getColor(R.color.rojo));
                break;
            case 1: //si la opción elegida es 1, color dorado
                button.setBackgroundColor(getResources().getColor(R.color.dorado));
                break;
            case 2: //si la opción elegida es 2, color plata
                button.setBackgroundColor(getResources().getColor(R.color.plata));
                break;

        }
    }

}