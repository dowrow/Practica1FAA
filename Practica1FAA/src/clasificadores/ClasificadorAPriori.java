/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;

import datos.Datos;
import datos.Elemento;
import java.util.ArrayList;
import java.util.HashMap;

public class ClasificadorAPriori extends Clasificador {
    String Elemento;
  
    @Override
    public void entrenamiento(Datos datostrain) {
        // Busco la clase mayoritaria de los datos y la guardo
        HashMap<Elemento, Integer> incidencia = new HashMap<Elemento, Integer>();
        Elemento[][] matriz = datostrain.getDatos();
        for (Elemento[] fila : matriz) {
            Elemento ultimoElemFila = fila[fila.length-1];
            Integer nIncidencias = incidencia.get(ultimoElemFila);
            if(nIncidencias == null){
                nIncidencias = 1;
            }else{
                nIncidencias++;
            }
            incidencia.put(ultimoElemFila, nIncidencias);
        }
        
    }

    @Override
    public ArrayList<Integer> clasifica(Datos datos) {
        // Asigno la clase mayoritaria a todos los datos
        return null;
    }
}
