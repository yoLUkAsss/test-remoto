public abstract class CuentaGeneral {
  private long numeroCuenta;
  private String titular;
  private long saldo;

  public long getSaldo() {
    return saldo;
  }

  public CuentaGeneral(long nCuenta, String titular) {
    this.numeroCuenta = nCuenta;
    this.titular = titular;
    this.saldo = 0;
  }

  protected void incrementar(long monto) {
    saldo += monto;
  }

  protected void decrementar(long monto) {
    saldo -= monto;
  }

  protected boolean haySaldoSuficiente(long montoAExtraer) {
    return this.saldo >= montoAExtraer;
  }

  public void depositar(long monto) {
    if (monto < 0) {
      throw new RuntimeException("No se puede depositar negativo");
    }
    this.incrementar(monto);
  }

  public void extraer(long monto) {
    if (!this.extracionValida(monto)) {
      throw new RuntimeException("No hay dinero suficiente");
    }
    this.decrementar(monto);
  }

  public abstract boolean extracionValida(long monto);
}