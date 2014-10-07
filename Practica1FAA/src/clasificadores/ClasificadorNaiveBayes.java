package clasificadores;

import datos.Datos;
import datos.Elemento;
import datos.ElementoFactory;
import datos.TiposDeAtributos;
import java.util.ArrayList;
import java.util.HashMap;



/**
 *
 * @author dani
 */
public class ClasificadorNaiveBayes extends Clasificador{
    // El primer Array nos dice la columna
    // El primer HashMap nos dice el valor de la columna
    // El segundo hashmap cuenta por cada valor de la columna cuantas incidencias 
    // Tiene la clase, para hacer p(D|H)
    ArrayList<HashMap<Elemento, HashMap<Elemento, Integer>>> incidencias;
    
    // Cuenta las incidencias totales de cada clase
    HashMap<Elemento, Integer> incidenciaClaseTotal;
    
    // Filas totales de train
    int filasTrain = 0;
    
    // Medias y varianzas de los atributos continuos dadas las clases
    // Primer HashMap = Clase
    // Primer ArrayList = Columna
    // Segundo ArrayList = [Media, Varianza]
    HashMap<Elemento, ArrayList<ArrayList<Double>>> tablaNormal;
    
    private HashMap<Elemento, Integer> generarTablaCeros (Datos d) {
        ArrayList<Elemento> clases = d.getClases();
        HashMap<Elemento, Integer> tabla = new HashMap<>();
        for (Elemento clase : clases) {
            tabla.put(clase, 0);
        }
        return tabla;
    }
    
    @Override
    public void entrenamiento(Datos datosTrain) {
        this.incidencias = new ArrayList<>();
        this.incidenciaClaseTotal = this.generarTablaCeros(datosTrain);
        
        ArrayList<Double> mediaVarianza = new ArrayList<>();
        // Inicialmente media = 0
        mediaVarianza.add(0.0);
        // Inicialmente varianza = 0
        mediaVarianza.add(0.0);
        
        // Crear hashmap de clases
        /*
        for (Elemento clase : datosTrain.getClases()) {
            ArrayList<ArrayList<Double>> columnas = new ArrayList<>();
            for (String campo : datosTrain.getNombreCampos()) {
                columnas.add(mediaVarianza);
            }
            this.tablaNormal.put(clase, null);
        }
         */
        // Crea un hashmap de hashmaps para cada columna
        for(Elemento e: datosTrain.getDatos()[0]){
            HashMap<Elemento, HashMap<Elemento, Integer>> incidenciaColumna = new HashMap<>();
            this.incidencias.add(incidenciaColumna);
        }
       
        for(Elemento fila[] : datosTrain.getDatos()){
            this.filasTrain++;
            Elemento clase = fila[fila.length-1];
            for(int i = 0; i < (fila.length - 1); i++){
                HashMap<Elemento, HashMap<Elemento, Integer>> incidenciaColumna  = this.incidencias.get(i);
                // Si el valor de la columna ya está en el hashmap
                if(incidenciaColumna.containsKey(fila[i])){
                    //se ha encontrado el elemento
                    HashMap<Elemento, Integer> incidenciaClase = incidenciaColumna.get(fila[i]);
                    if(incidenciaClase.containsKey(clase)){
                        Integer contador = incidenciaClase.get(clase);
                        contador++;
                        incidenciaClase.put(clase, contador);
                    }
                    
                }else{
                    //no se encontraba el elemento en la base de datos
                    HashMap<Elemento, Integer> incidenciaClase = this.generarTablaCeros(datosTrain);
                    Integer contador = incidenciaClase.get(clase);
                    contador++;
                    incidenciaClase.put(clase, contador);
                    incidenciaColumna.put(fila[i], incidenciaClase);
                    
                    //para hacer laplace, cada vez que generas la tabla de unos
                    //sumas uno a las incidencias de clases totales
                    for(Elemento claseAux : datosTrain.getClases()){
                        Integer nIncidencias = this.incidenciaClaseTotal.get(claseAux);
                        nIncidencias++;
                        this.incidenciaClaseTotal.put(claseAux, nIncidencias);
                    }
                }
            }
            //sumar las incidencias de cada clase
            Integer nIncidencias = this.incidenciaClaseTotal.get(clase);
            nIncidencias++;
            this.incidenciaClaseTotal.put(clase, nIncidencias);
           
        }
        
    }

    @Override
    public ArrayList<Elemento> clasifica(Datos datosTest) {
        ArrayList<Elemento> clasificado = new ArrayList<>();
        
        
        for(Elemento fila[] : datosTest.getDatos()){
            Elemento mejorClase = null;
            double mejorProb = -1;
            for(Elemento claseTest : datosTest.getClases()){
                
                // Simulacion de la clase, decimos, si fuera esta clase, que prob da
               
                double prob = 0;
                for(int i = 0; i < (fila.length - 1); i++){
                    /*
                        prob de el dato dada la hipotesis
                        MULi (p(Di|H))
                    */
                    double probAux;
                    
                    if (fila[i].getTipo().equals(TiposDeAtributos.Continuo)) {
                        /* Si el dato es continuo */
                        // Sacar la media y varianza del atributo dada la clase claseTest0;
                        // double media = 
                        // double varianza = 
                        // Calcular P(D|H) según func. de dist. de una normal
                        // probAux = (e ^ ((fila[i].getValorContinuo() - media) ^ 2) / (2 * varianza))) / (double) sqrt(2 * PI * varianza);
                        
                    } else {
                        /* Si el dato es nominal */
                        try{
                            probAux = this.incidencias.get(i).get(fila[i]).get(claseTest);
                        }catch(Exception e){
                            probAux = 0;
                        }
                        probAux = probAux/this.incidenciaClaseTotal.get(claseTest);
                        if(i == 0){
                            prob = probAux;
                        }else{
                            prob = prob*probAux;
                        }
                    }
                                        
                }
                /*
                    prob de la hipotesis
                */
                double probAux = this.incidenciaClaseTotal.get(claseTest);
                probAux = probAux/this.filasTrain;
                prob = prob*probAux;
                if(prob > mejorProb){
                    mejorProb = prob;
                    mejorClase = claseTest;
                }
            }
            /*fin del iterador de las clases*/
            clasificado.add(mejorClase);
        }
        /*fin del iterador de las filas*/
        return clasificado;
    }
    
}
