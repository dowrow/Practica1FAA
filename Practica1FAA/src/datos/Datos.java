/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import particionado.Particion;

public class Datos {

    public enum TiposDeAtributos {
        Continuo, Nominal
    };

    ArrayList<TiposDeAtributos> tipoAtributos;
    ArrayList<String> nombreCampos;
    Elemento datos[][];
    
    public Datos(int numDatos, ArrayList<TiposDeAtributos> tipos) {
        this.tipoAtributos = tipos;
    }

    public Datos extraeDatosTrain(Particion idx) {
        return null;
    }

    public Datos extraeDatosTest(Particion idx) {
        return null;
    }
    
    // 3 Filas: nº datos, nombres de campos, tipos de atributos [Nominal o Continuo]
    // Resto de filas: Conjunto de datos, uno por fila y campos separados por comas
    public static Datos cargaDeFichero(String nombreDeFichero) {
        CsvReader ficheroCSV;
        ArrayList<String> nombreAtributos;
        
        try {
            ficheroCSV = new CsvReader(nombreDeFichero);
            
            // Lee nº datos
            ficheroCSV.readRecord();
            int numFilas = Integer.parseInt(ficheroCSV.get(0));
                        
            // Lee nombres campos
            ficheroCSV.readRecord();
            nombreAtributos = Arrays.asList(ficheroCSV.getValues());
                    
            // Lee tipos de atributos
            while (ficheroCSV.readRecord()) {
               
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Datos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        
       
         
        usuarios_import.close();
        return null;
    }
}
