package com.cidead.proyecto.electronicplay;

import android.widget.Button;

public class CalculoResistencia {

    int resultado;
    public int calculo (int banda1, int banda2, int multi)
    {
        String posiciones=String.valueOf(banda1)+String.valueOf(banda2);
        int multiplicador= (int) Math.pow(10,multi);
        String posicionesMultiplicador=posiciones+(String.valueOf(multiplicador).substring(1));

        int resultado=Integer.parseInt(posicionesMultiplicador);

    return resultado;
    }
}
