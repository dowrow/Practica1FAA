package clasificadores;

import datos.Datos;
import datos.Elemento;
import java.util.ArrayList;
import particionado.EstrategiaParticionado;
import particionado.Particion;
import particionado.ValidacionCruzada;

abstract public class Clasificador {

    //Métodos abstractos que se implementan en cada clasificador concreto
    abstract public void entrenamiento(Datos datosTrain);

    abstract public ArrayList<Elemento> clasifica(Datos datosTest);

    // Obtiene el numero de aciertos y errores para calcular la tasa de fallo
    public double error(Datos datos, Clasificador clas) {
        int fallos = 0;
        double error;
        Elemento matriz[][] = datos.getDatos();
        ArrayList<Elemento> clases = clas.clasifica(datos);
        
        //Aqui se compara con clases reales y se calcula el error
        for (int i = 0; i < matriz.length; i++) {
            Elemento claseReal = matriz[i][datos.getTamColumn() - 1];
            Elemento claseInferida = clases.get(i);
            if (!claseInferida.equals(claseReal)) {
                fallos++;
            }
        }
        error = fallos / clases.size();
        return error;
    }

    // Realiza una clasificacion utilizando una estrategia de particionado determinada
    public static ArrayList<Double> validacion(EstrategiaParticionado part, Datos datos,
            Clasificador clas) {

        //Creamos las particiones siguiendo la estrategia llamando a datos.creaParticiones
        //Para validación cruzada: En un bucle hasta nv entrenamos el clasf con la particion de train i(extraerDatosTrain) 
        // y obtenemos el error en la particion test de i (extraerDatosTest)
        //Para validación porcentual entrenamos el clasf con la partición de train (extraerDatosTrain) y 
        // obtenemos el error en la particion test (extraerDatosTest)
        return null;
    }

    public static void main(String[] args) {
        Datos d;
        if(args.length < 1){
            /*debug*/
            d = Datos.cargaDeFichero("./src/car.data");
        }else{
            d = Datos.cargaDeFichero(args[0]);
        }
        
        EstrategiaParticionado estrategia = new ValidacionCruzada();
        ArrayList<Particion> particiones = estrategia.crearParticiones(d.getDatos().length, 10);
        Clasificador c = new ClasificadorNaiveBayes();
        
        for (Particion p : particiones) {
            
            Datos datosTrain = d.extraeDatosTrain(p);
            Datos datosTest = d.extraeDatosTrain(p);

            c.entrenamiento(datosTrain);
            double error = c.error(datosTest, c);
            System.out.println("El error es: " + error);
        }

    }
}
