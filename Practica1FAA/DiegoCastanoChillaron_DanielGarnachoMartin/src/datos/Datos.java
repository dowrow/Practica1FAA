/**
 *
 * @author Daniel Garnacho y Diego Castaño
 */

package datos;

import com.csvreader.CsvReader;
import java.util.ArrayList;
import java.util.Arrays;
import particionado.Particion;

public class Datos {


    /* Lista de atributos del fichero */
    private ArrayList<TiposDeAtributos> tipoAtributos;
    
    /* Lista de nombres de los campos del fichero */
    private ArrayList<String> nombreCampos;
    
    /* Datos del fichero */
    private Elemento datos[][];
    
    /* Posibles clases */
    private ArrayList<Elemento> clases = new ArrayList<>();
    
    int nDatosCopiados = 0;
    
    /*
     * Constructor
     * @param numDatos Número de filas que contiene el fichero
     * @param tipos Tipos de columnas que contiene el fichero
     */
    public Datos(int numDatos, ArrayList<TiposDeAtributos> tipos) {
        this.tipoAtributos = tipos;
        this.datos = new Elemento[numDatos][tipos.size()];
        this.clases = new ArrayList<>();
    }

    public Datos extraeDatosTrain(Particion idx) {
        
        // Crear nuevo objeto Datos
        ArrayList<Integer> indices = idx.getIndicesTrain();
        Datos datosTrain = new Datos(indices.size(), this.tipoAtributos);
        datosTrain.setClases(clases);
        // Introducir filas
        for(Integer indice : indices) {
            datosTrain.addLineaDatos(this.datos[indice]);
        }
        
        return datosTrain;
    }

    public Datos extraeDatosTest(Particion idx) {
        // Crear nuevo objeto Datos
        ArrayList<Integer> indices = idx.getIndicesTest();
        Datos datosTest = new Datos(indices.size(), this.tipoAtributos);
        datosTest.setClases(clases);
        // Introducir filas
        for(Integer indice : indices) {
            datosTest.addLineaDatos(this.datos[indice]);
        }
        
        return datosTest;
    }
    
    // 3 Filas: nº datos, nombres de campos, tipos de atributos [Nominal o Continuo]
    // Resto de filas: Conjunto de datos, uno por fila y campos separados por comas
    public static Datos cargaDeFichero(String nombreDeFichero) {
        
        CsvReader ficheroCSV;
        ArrayList<String> nombreCampos;
        ArrayList<String> tiposString;
        ArrayList<TiposDeAtributos> tipos = new ArrayList<>();
        ArrayList<String> elementosString;
        
        try {
            ficheroCSV = new CsvReader(nombreDeFichero);

            // Lee nº datos
            ficheroCSV.readRecord();
            int numFilas = Integer.parseInt(ficheroCSV.get(0));

            // Lee nombres campos
            ficheroCSV.readRecord();
            nombreCampos = new ArrayList<>(Arrays.asList(ficheroCSV.getValues()));

            // Lee tipos de atributos como cadenas
            ficheroCSV.readRecord();
            tiposString = new ArrayList<>(Arrays.asList(ficheroCSV.getValues()));

            // Pasa cadenas a elementos de la enumeración
            for (String cadena: tiposString) {
                tipos.add(TiposDeAtributos.valueOf(cadena));
            }
            

            // Crea objeto a devolver
            Datos objetoDatos = new Datos(numFilas, tipos);
            objetoDatos.setNombreCampos(nombreCampos);
            
            // Introducimos filas
            while (ficheroCSV.readRecord()) {
                
                // Leemos todos los elementos de una fila como cadenas
                elementosString = new ArrayList<>(Arrays.asList(ficheroCSV.getValues()));
                
                // Para cada cadena leída creamos un Elemento 
                // y lo introducimos en la matriz
                int i=0;
                Elemento elemCopy[] = new Elemento[objetoDatos.getTamColumn()];
                for(String elementoString: elementosString){
                    TiposDeAtributos tipo = objetoDatos.getTipoAtributosAt(i);
                    Elemento elemento = ElementoFactory.crear(tipo, elementoString);
                    elemCopy[i] = elemento;
                    i++;
                }
                objetoDatos.addLineaDatos(elemCopy);
                
                // Además, guardamos la clase (última columna)
                Elemento clase = elemCopy[elemCopy.length - 1];
                if (!objetoDatos.clases.contains(clase)) {
                    objetoDatos.clases.add(clase);
                }
            }
            
            return objetoDatos;
            
        } catch (Exception e) {
            System.out.println("No se pudo leer el fichero " + nombreDeFichero);
            return null;
        }
    }
    
    /*
     * GETTERS & SETTERS
     */
    
    
    /**
     * @return the tipoAtributos
     */
    public ArrayList<TiposDeAtributos> getTipoAtributos() {
        return this.tipoAtributos;
    }
     /**
     * @return TiposDeAtributos en la posicion pasada por parametros
     */
    public TiposDeAtributos getTipoAtributosAt(int position){
        return this.tipoAtributos.get(position);
    }

    /**
     * @param tipoAtributos the tipoAtributos to set
     */
    public void setTipoAtributos(ArrayList<TiposDeAtributos> tipoAtributos) {
        this.tipoAtributos = tipoAtributos;
    }

    /**
     * @return the nombreCampos
     */
    public ArrayList<String> getNombreCampos() {
        return nombreCampos;
    }
    
    public int getTamColumn(){
        return this.datos[0].length;
    }
    /**
     * @param nombreCampos the nombreCampos to set
     */
    public void setNombreCampos(ArrayList<String> nombreCampos) {
        this.nombreCampos = nombreCampos;
    }
    
    public void addLineaDatos(Elemento elem[]){
        int nDatos = this.nDatosCopiados;
        System.arraycopy(elem, 0, this.datos[nDatos], 0, elem.length);
        this.nDatosCopiados++;
    }

    /**
     * @return the datos
     */
    public Elemento[][] getDatos() {
        return datos;
    }

    /**
     * @return the clases
     */
    public ArrayList<Elemento> getClases() {
        return clases;
    }

    /**
     * @param clases the clases to set
     */
    public void setClases(ArrayList<Elemento> clases) {
        this.clases = clases;
    }
    
}
