/**
 *
 * @author Daniel Garnacho y Diego Castaño
 */

package datos;

public class ElementoFactory {
    public static Elemento crear(TiposDeAtributos tipo, String valor) {
        Elemento e;
        switch (tipo) {
            case Continuo: 
                e = new ElementoContinuo(Double.parseDouble(valor));
                break;
            default: 
                e = new ElementoNominal(valor);
                break;
        }
        return e;
    }
}
