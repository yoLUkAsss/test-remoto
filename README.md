## Proyecto Gradle

### Build

```
> gradle build
```

### Run Test

```
> gradle test
```


## Resolucion de problemas de programacion y diseño

#### Ejercicio A

```java
/**
 * 
 * El principal problema en mi opinion, es el acoplamiento de una clase
 * respondiendo a dos tipos de entidades diferentes. Es decir, dependiendo se si
 * es una caja de ahorro o cuenta corriente utiliza un atributo llamado
 * `descubiertoAcordado`. Incluso teniendo que, siendo una caja de ahorro,
 * mantener un atributo que no se usara en su ciclo de vida.
 * 
 * Ver UML Adjunto e implementacion en la carpeta SolucionPosible.
 * 
 * Sobre precauciones a tomar, quizas me esta faltando entender algo sobre el
 * dominio del problema si bien debe ser algo basico, no tengo mucho background
 * sobre entidades bancarias
 * 
 * Agregaria getters y setters al menos a los campos numeroCuenta, titular y
 * saldo. Ya que representan informacion necesaria para un posible cliente,
 * quizas minimamente el saldo deberia poder ser visible.
 * 
 * No se si llamaria patron a la solucion planteada, no es mas que desacoplar
 * responsabilidades de la clase (clase general y/o abstracta), generado clases
 * con especificaciones concretas. Quizas si tiene algo de template method, pero
 * nada muy rescatable.
 * 
 */

public class Cuenta {
  public static final int CTA_CORRIENTE = 0;
  public static final int CAJA_AHORRO = 1;
  private int tipo;
  private long numeroCuenta;
  private String titular;
  private long saldo;
  private long descubiertoAcordado;

  public Cuenta(int tipo, long nCuenta, String titular, long descAcordado) {
    this.tipo = tipo;
    this.numeroCuenta = nCuenta;
    this.titular = titular;
    if (tipo == CTA_CORRIENTE) {
      this.descubiertoAcordado = descAcordado;
    } else {
      this.descubiertoAcordado = 0;
    }
    saldo = 0;
  }

  public Cuenta(int tipo, long nCuenta, String titular) {
    this.tipo = tipo;
    this.numeroCuenta = nCuenta;
    this.titular = titular;
    this.descubiertoAcordado = 0;
    saldo = 0;
  }

  public void depositar(long monto) {
    saldo += monto;
  }

  public void extraer(long monto) {
    switch (tipo) {
    case CAJA_AHORRO:
      if (monto > saldo) {
        throw new RuntimeException("No hay dinero suficiente");
      }
      break;
    case CTA_CORRIENTE:
      if (monto > saldo + descubiertoAcordado) {
        throw new RuntimeException("No hay dinero suficiente");
      }
    default:
      break;
    }
    saldo -= monto;
  }
}
```

#### Ejercicio B

El archivo EjecicionSolucionB utiliza una caja de ahorro y se puede ejecutar y va a obtener input en la entrada estandar e ira mostrando los diferentes resultados en la salida estandar.

Tambien existe el archivo FirstTest.java el cual posee algunos tests para la Caja de Ahorro.

Para el ejercicio b del punto 1 realmente no me quedo claro cual seria la idea de la prueba...

Tenia pensado que quizas se tratase de generar una prueba tipo threads para cada usuario, con una lista precargada (y aleatoria) de operaciones diferentes y ejecutarlas en paralelo, o si era algo mas simple y estaba pensandolo demasiado complejo.

## Aspectos conceptuales

#### Ejercicio a

* Permite definir y generar logica (codigo) a partir de reglas de validacion
* Al ser automatizadas permiten ser generadas una vez y mientras la logica de negocio no cambie, serviran siempre para validar el desarrollo ante nuevas features.

#### Ejercicio b

De forma simplificada, Observer puede ser utilizado cuando se tiene un conjunto de elementos que deben modificar su estado o disparar algun evento basados en la informacion de un obejtivo observado de forma tal que no lo hagan haciendo polling, sino mas bien esperando un evento de parte del observado hacia todos los elementos Observer.

Ventajas: 

* Los observers son notificados en el momento que les corresponde, cuando su objetivo cambia o no.
* El obersvado no necesita saber quien lo observa ni con que razon, con lo cual no quedan tan acoplados en base a que propiedades se obersvan sino mas bien, a cuando el observado cambia.

#### Ejercicio c

En el patron Strategy se definen varias estrategias para resolver un problema puntual de alguna entidad y se permite la vinculacion y desvinculacion de estrategias a utilizar. Con lo cual una instancia particular de alguna clase pueda elegir en base a ciertos parametros que estrategia utilizar de forma dinamica.

Imaginemos (en pseudo codigo) una clase `Soldado` que tenga una metodo de instancia que le permite ir a atacar a un objetivo... Podemos tener dos estrategias planteadas, ser sigiloso o ser mas bien directo e ir corriendo como loco.

Definimos una interface Estrategia que define ciertos metodos respecto a movimientos basicos de algun soldado
```
interface Estrategia {
  atacarObjetivo(Objetivo: objetivoSeleccionado);
}

class EstrategiaSigiloso implements Estrategia {
  atacarObjetivo(Objetivo: objetivoSeleccionado) {
    agacharse()
    if (elCaminoEstaDespejado()) {
      correrAlObjetivo(objetivoSeleccionado)
    } else {
      caminarAlObjetivo(obejtivoSeleccionado)
    }
    atacarObjetivo(objetivoSeleccionado)
  }
}

class EstrategiaLocoDeLaGuerra implements Estrategia {
  atacarObjetivo(Objetivo: objetivoSeleccionado) {
    correrAlObjetivo(objetivoSeleccionado)
    atacarObjetivo(objetivoSeleccionado)
  }
}
```

La clase Soldado: 

```
class Soldado {

  estrategia = new EstrategiaSigiloso()

  irYAtacar(Objetivo: algunObjetivo) {
    estrategia.atacarObjetivo(algunObjetivo)
  }

  switchLocoDeGuerra() {
    estrategia = new EstrategiaLocoDeLaGuerra()
  }

  switchSigiloso() {
    estrategia = new EstrategiaSigiloso()
  }

}
```

En particular deberia utilizar siempre una unica instancia de cada estrategia que requiera usar... todo dependera de cuantas estrategias debera utilizar para diferentes objetivos a cumplir.

Algo que podria utilizarse en este tipo de patrones que cada estrategia puede redefinir diferentes partes del metodo `atacarObjetivo` usando Template Method haciendo algo parecido a el ejemplo de Cuentas Bancarias, siendo Caja de Ahorro o Cuenta Corriente.
O podria usarse Doble Dispatch para que el soldado pueda pasarse como parametro de la instancia de la estrategia y ser utilizado por algun metodo de la misma.


## Bases de datos y SQL

#### Ejercicio A

```sql
SELECT Usuario.id as id, username, password
FROM Usuario JOIN Persona
WHERE Usuario.id = Persona.idUsuario and Persona.nombre LIKE 'Jorg%'
```

#### Ejercicio B

```sql
SELECT count(EXTRACT(MONTH FROM Persona.fechaNac)) as birthdayMonth
GROUP BY birthdayMonth
HAVING birthdayMonth > 10
```

