/**
 *
 * @author Daniel Garnacho y Diego Castaño
 */


package clasificadores;

import datos.Datos;
import datos.Elemento;
import java.util.ArrayList;
import java.util.HashMap;

public class ClasificadorAPriori extends Clasificador {
    Elemento maxAPriori;
  
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
        Elemento masIncidencias = null;
        int nIncidencias = 0;
        for(Elemento e : incidencia.keySet()){
            int inci = incidencia.get(e);
            if(inci > nIncidencias){
                masIncidencias = e;
                nIncidencias = inci;
            }
        }
        
        this.maxAPriori = masIncidencias;
    }

    @Override
    public ArrayList<Elemento> clasifica(Datos datos) {
        // Asigno la clase mayoritaria a todos los datos
        // ArrayList de Integer?
        ArrayList<Elemento> prediccion = new ArrayList<>();
        for(Elemento e[] : datos.getDatos()){
            Elemento epredi = this.maxAPriori;
            prediccion.add(epredi);
        }
        
        return prediccion;
    }
}
