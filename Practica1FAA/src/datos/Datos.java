/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.util.ArrayList;
import particionado.Particion;

public class Datos {

    public enum TiposDeAtributos {

        Continuo, Nominal
    };

    ArrayList<TiposDeAtributos> tipoAtributos;

    double[][] datos;

    public Datos(int numDatos, ArrayList<TiposDeAtributos> tipos) {
    }

    public Datos extraeDatosTrain(Particion idx) {
        return null;
    }

    public Datos extraeDatosTest(Particion idx) {
        return null;
    }

    public static Datos cargaDeFichero(String nombreDeFichero) {
        return null;
    }
}
