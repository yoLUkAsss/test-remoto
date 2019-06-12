public class CuentaCorriente extends CuentaGeneral {

  public int descubiertoAcordado;

  public CuentaCorriente(long nCuenta, String titular, int descAcordado) {
    super(nCuenta, titular);
    this.descubiertoAcordado = descAcordado;
  }

  public boolean extracionValida(long monto) {
    /**
     * Podria usar este otro metodo, pero seria una solucion semanticamente
     * incorrecta, a pesar de que la matematica concuerde
     * 
     * return this.haySaldoSuficiente(monto - this.descubiertoAcordado);
     */

    return this.getSaldo() > monto + this.descubiertoAcordado;
  }
}