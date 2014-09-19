/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package particionado;

import java.util.ArrayList;

public class DivisionPorcentual implements EstrategiaParticionado {

    @Override
// Devuelve el nombre de la estrategia de particionado
    public String getNombreEstrategiaParticionado() {
        return "Division Porcentual";
    }

    @Override
// Crea particiones segun el metodo tradicional. El conjunto de entrenamiento
    // se crea con un porcentaje y el de test con el restante
    public ArrayList<Particion> crearParticiones(int numDatos, int numParticiones) {
        return null;
    }
}
