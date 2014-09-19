/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package particionado;

import java.util.ArrayList;

public interface EstrategiaParticionado {

    public String getNombreEstrategiaParticionado();

    public ArrayList<Particion> crearParticiones(int numDatos, int numParticiones);
}
