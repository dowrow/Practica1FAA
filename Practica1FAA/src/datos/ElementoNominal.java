/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Diego
 */
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
}
