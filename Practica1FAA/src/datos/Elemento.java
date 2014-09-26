/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Diego
 */
public  abstract class Elemento{
  public abstract String getValorNominal();
  public abstract double getValorContinuo();
  @Override
  public abstract int hashCode();

  @Override
  public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Elemento other = (Elemento) obj;
        return true;
    }
}
