/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package particionado;

import java.util.ArrayList;

public class ValidacionCruzada implements EstrategiaParticionado {

    @Override
// Devuelve el nombre de la estrategia de particionado
    public String getNombreEstrategiaParticionado() {
        return null;
    }

    @Override
// Crea particiones segun el metodo de validación cruzada. El conjunto de entrenamiento
    // se crea con las numPart-1 particiones y el de test con la partición restante
    public ArrayList<Particion> crearParticiones(int numDatos, int numParticiones) {
        return null;
    }
}
