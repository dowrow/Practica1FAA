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

/**
 *
 * @author dani
 */
public class ClasificadorNaiveBayes extends Clasificador{
    ArrayList<HashMap<Elemento, Integer>> incidencias;
    

    @Override
    public void entrenamiento(Datos datosTrain) {
        
        for(Elemento fila[] : datosTrain.getDatos()){
            
            
        }
    }

    @Override
    public ArrayList<Elemento> clasifica(Datos datosTest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
