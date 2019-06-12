public class CajaDeAhorro extends CuentaGeneral {

  public CajaDeAhorro(long nCuenta, String titular) {
    super(nCuenta, titular);
  }

  public boolean extracionValida(long monto) {
    return this.haySaldoSuficiente(monto);
  }
}