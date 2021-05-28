package com.cidead.proyecto.electronicplay;

import android.widget.Button;
/**
 * Clase CalculoResistencia , clase que calcula el valor de la resistencia dependiendo de los valores que reciba por parametros
 * @author Paula Salicio
 */
public class CalculoResistencia {

    int resultado;
    /**
     * Método calculo para calcular el valor de la resistencia a partir de 3 parametros
     * @param banda1 entero que representa al primer valor de la banda
     * @param banda2  entero que representa al segundo valor de la banda
     * @param multi  entero que representa al valor del multiplicador de la resistencia
     * @return resultado el valor calculado tras hacer los calculos
     */
    public int calculo (int banda1, int banda2, int multi)
    {
        String posiciones=String.valueOf(banda1)+String.valueOf(banda2); //obtenemos las dos primeras cifras de la resistencia
        int multiplicador= (int) Math.pow(10,multi); //obtenemos el numero de ceros según el valor del multiplicador
        String posicionesMultiplicador=posiciones+(String.valueOf(multiplicador).substring(1)); //contatenamos las 2 primeras posiciones y el número de 0s

        int resultado=Integer.parseInt(posicionesMultiplicador); //obtenemos el resultado

    return resultado;
    }
}
