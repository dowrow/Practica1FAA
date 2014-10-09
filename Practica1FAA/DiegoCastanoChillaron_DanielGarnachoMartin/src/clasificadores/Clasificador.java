/**
 *
 * @author Daniel Garnacho y Diego Castaño
 */

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
        error = (double)fallos / (double)clases.size();
        return error;
    }

    // Realiza una clasificacion utilizando una estrategia de particionado determinada
    public static ArrayList<Double> validacion(EstrategiaParticionado part, Datos datos,
            Clasificador clas) {
        ArrayList<Double> errores = new ArrayList<>();
        
        //Creamos las particiones siguiendo la estrategia llamando a datos.creaParticiones
        ArrayList<Particion> particiones = part.crearParticiones(datos.getDatos().length, 5);

        //Para validación cruzada: En un bucle hasta nv entrenamos el clasf con la particion de train i(extraerDatosTrain)
                // y obtenemos el error en la particion test de i (extraerDatosTest)

        for (Particion particion : particiones) {
            Datos datosTrain = datos.extraeDatosTrain(particion);
            Datos datosTest = datos.extraeDatosTest(particion);
            clas.entrenamiento(datosTrain);
            errores.add(clas.error(datosTest,clas));
        }
        
        return errores;
    }
    private static double calculateAverage(ArrayList <Double> marks) {
        Double sum = 0.0;
        if(!marks.isEmpty()) {
          for (Double mark : marks) {
              sum += mark;
          }
          return sum / marks.size();
        }
        return sum;
      }

    public static void main(String[] args) {
        Datos datos;
        
        if(args.length < 1){
            System.out.println("Se debe pasar el fichero de los datos como parametro del programa.");
            return; 
        }
        
        datos = Datos.cargaDeFichero(args[0]);
        
        //EstrategiaParticionado estrategia = new DivisionPorcentual();
        EstrategiaParticionado estrategia = new ValidacionCruzada();
        Clasificador clasificador = new ClasificadorNaiveBayes();
        Clasificador clasificador2 = new ClasificadorNaiveBayesLaplace();
        Clasificador clasificador3 = new ClasificadorAPriori();
        
        ArrayList<Double> errores = Clasificador.validacion(estrategia, datos, clasificador);
        System.out.println("Los errores con NB son: " + errores);
        System.out.println("Error medio con NB: " + calculateAverage(errores));
        
        ArrayList<Double> errores2= Clasificador.validacion(estrategia, datos, clasificador2);
        System.out.println("Los errores con NB + Laplace son: " + errores2);
        System.out.println("Error medio con NB + Laplace: " + calculateAverage(errores2));
        
        ArrayList<Double> errores3= Clasificador.validacion(estrategia, datos, clasificador3);
        System.out.println("Los errores con clasif. a priori son: " + errores3);
        System.out.println("Error medio con clasif. a priori: " + calculateAverage(errores3));
    }
}
