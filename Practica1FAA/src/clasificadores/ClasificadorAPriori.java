/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificadores;

import datos.Datos;
import java.util.ArrayList;

public class ClasificadorAPriori extends Clasificador {

    @Override
    public void entrenamiento(Datos datostrain) {
        // Busco la clase mayoritaria de los datos y la guardo
    }

    @Override
    public ArrayList<Integer> clasifica(Datos datos) {
        // Asigno la clase mayoritaria a todos los datos
        return null;
    }
}
