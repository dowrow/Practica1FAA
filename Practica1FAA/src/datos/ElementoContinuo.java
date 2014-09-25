/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Diego
 */
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
} 
