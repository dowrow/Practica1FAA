/**
 *
 * @author Daniel Garnacho y Diego Castaño
 */

package datos;

public class ElementoNominal extends Elemento {
    private String valor;
    public ElementoNominal (String valor) {
        this.valor = valor;
    }
    
    public String getValorNominal() {
        return this.valor;
    }
    public double getValorContinuo() {
        return 0.0;
    }

    @Override
    public int hashCode() {
        return this.valor.hashCode();
    }
    
        @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.getValorNominal().equals( ((ElementoNominal)obj).getValorNominal() )) {
            return true;
        }
        return false;
    }
    
    @Override
    public TiposDeAtributos getTipo() {
        return TiposDeAtributos.Nominal;
    }
}
