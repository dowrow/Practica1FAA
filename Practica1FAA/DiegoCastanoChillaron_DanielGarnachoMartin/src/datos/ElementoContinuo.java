/**
 *
 * @author Daniel Garnacho y Diego Castaño
 */

package datos;

public class ElementoContinuo extends Elemento {
    private double valor;
    public ElementoContinuo (double valor) {
       this.valor = valor; 
    }
    public String getValorNominal() {
        return "";
    }
    public double getValorContinuo() {
        return this.valor;
    }

    @Override
    public int hashCode() {
        return (new Double(valor)).hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.getValorContinuo() != ((ElementoContinuo)obj).getValorContinuo()) {
            return false;
        }
        return true;
    }

    @Override
    public TiposDeAtributos getTipo() {
        return TiposDeAtributos.Continuo;
    }
    
} 
