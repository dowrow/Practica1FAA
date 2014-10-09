/**
 *
 * @author Daniel Garnacho y Diego Castaño
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

    /**
     * @return the indicesTrain
     */
    public ArrayList<Integer> getIndicesTrain() {
        return indicesTrain;
    }

    /**
     * @return the indicesTest
     */
    public ArrayList<Integer> getIndicesTest() {
        return indicesTest;
    }
}
