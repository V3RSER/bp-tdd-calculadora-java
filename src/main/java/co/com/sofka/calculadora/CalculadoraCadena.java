package co.com.sofka.calculadora;

public class CalculadoraCadena {
    public int suma(String cadena) {
        if (cadena.length() == 1) {
            return Integer.parseInt(cadena);
        }
        return 0;
    }
}
