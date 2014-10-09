/**
 *
 * @author Daniel Garnacho y Diego Castaño
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
        
        int numFilasGrupo = numDatos / numParticiones;
        
        // Dividimos en grupos
        ArrayList<ArrayList<Integer>> grupos = new ArrayList<>();
        for (int i = 0; i < numParticiones; i++) {
            ArrayList<Integer> grupo = new ArrayList<>();
            for (int j = i * numFilasGrupo; j < (i+1) * numFilasGrupo; j++) {
                grupo.add(j);
            }
            grupos.add(grupo);
        }
        // Añadimos resto a ultimo grupo
        ArrayList<Integer> ultimoGrupo = grupos.get(numParticiones - 1);
        for (int i = numParticiones * numFilasGrupo; i < numDatos; i ++) {
            ultimoGrupo.add(i);
        }
        
        // Creamos particiones
        ArrayList<Particion> particiones = new ArrayList<>();
        
        for (int i = 0; i < numParticiones; i++) {
            
            // Elegir indices test
            ArrayList<Integer> indicesTest = grupos.get(i);
            
            // Juntar indices train
            ArrayList<Integer> indicesTrain = new ArrayList<>();
            for (int j = 0; j < numParticiones; j++) {
                if (j != i) {
                    indicesTrain.addAll(grupos.get(j));
                }
            }
            Particion p = new Particion(indicesTrain, indicesTest);
            particiones.add(p);
        }        
        
        return particiones;
    }
}
