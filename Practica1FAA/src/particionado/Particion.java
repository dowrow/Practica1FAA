/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package particionado;

import java.util.ArrayList;

public class Particion {

    private final ArrayList<Integer> indicesTrain;
    private final ArrayList<Integer> indicesTest;

    public Particion(ArrayList<Integer> indTrain, ArrayList<Integer> indTest) {
        this.indicesTrain = indTrain;
        this.indicesTest = indTest;
    }
}
