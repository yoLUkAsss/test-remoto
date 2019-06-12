import java.util.Scanner;

public class EjecucionSolucionB {

  /*
   * This is my first java program. This will print 'Hello World' as the output
   */

  public static void main(String[] args) {

    CajaDeAhorro cajaComun = new CajaDeAhorro(12345678, "Casa Comun");

    System.out.println("Bienvenido al sistema de Caja de ahorro de prueba");
    System.out.println("-- Formato de la prueba");
    System.out.println("-- Ingrese `Extraer N User` para que User haga una extracion de monto N del saldo");
    System.out.println("-- Ingrese `Depositar N User` para que User haga un deposito de monto N al saldo");
    System.out.println("-- Ingrese `EXIT` para finalizar");

    System.out.println("-------------");
    System.out.println("-------------");

    System.out.println("SALDO INICIAL " + cajaComun.getSaldo());

    System.out.println("USUARIO\t\tOPERACION\t\tMONTO\t\tSALDO");

    Scanner scan = new Scanner(System.in);
    String line = scan.nextLine();

    while (!line.equals("EXIT")) {

      System.out.println("ASD " + line);

      String[] parsed = line.split(" ");

      if (parsed.length == 3) {
        int monto = Integer.parseInt(parsed[1]);
        switch (parsed[0]) {
        case "Extraer":
          if (monto >= 0) {
            try {
              cajaComun.extraer(monto);
              System.out.println(parsed[2] + "\t\textraccion\t\t" + monto + "\t\t" + cajaComun.getSaldo());
            } catch (Exception e) {
              System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            }
          }
          break;
        case "Depositar":
          cajaComun.depositar(monto);
          System.out.println(parsed[2] + "\t\tdeposito\t\t" + monto + "\t\t" + cajaComun.getSaldo());
          break;
        default:
          System.out.println("Operacion no valida");
          break;
        }
      }

      line = scan.nextLine();
    }
    scan.close();
  }
}