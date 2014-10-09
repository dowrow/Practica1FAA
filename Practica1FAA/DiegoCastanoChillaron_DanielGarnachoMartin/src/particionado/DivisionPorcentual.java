/**
 *
 * @author Daniel Garnacho y Diego Castaño
 */

package particionado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DivisionPorcentual implements EstrategiaParticionado {
    
    private final double porcentajeTrain = 0.6;
    
    @Override
// Devuelve el nombre de la estrategia de particionado
    public String getNombreEstrategiaParticionado() {
        return "Division Porcentual";
    }

    @Override
    // Crea particiones segun el metodo tradicional. El conjunto de entrenamiento
    // se crea con un porcentaje y el de test con el restante
    public ArrayList<Particion> crearParticiones(int numDatos, int numParticiones) {
        
        int numDatosTrain = (int) (numDatos * this.porcentajeTrain);
        
        // Todos los indices
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < numDatos; i++) {
            indices.add(i);
        }
        
        ArrayList<Particion> particiones = new ArrayList<>();
        for (int i = 0; i < numParticiones; i++) {
            Collections.shuffle(indices);
            ArrayList<Integer> indicesTrain = new ArrayList<>(indices.subList(0, numDatosTrain - 1));
            ArrayList<Integer> indicesTest  = new ArrayList<>(indices.subList(numDatosTrain, numDatos - 1));
            Particion p = new Particion(indicesTrain, indicesTest);
            particiones.add(p);        
        }       
        
        return particiones;
    }
}
