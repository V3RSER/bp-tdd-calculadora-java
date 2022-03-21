package co.com.sofka.calculadora;

import java.util.Arrays;

public class CalculadoraCadena {
    public int suma(String cadena) {
        if (cadena.length() > 0) {
            String[] valoresCadena = cadena.split("[,|\n]");
            int[] valores = new int[valoresCadena.length];

            for (int i = 0; i < valoresCadena.length; i++) {
                valores[i] = Integer.parseInt(valoresCadena[i]);
                if (valores[i] < 0) {
                    throw new IllegalArgumentException("No se permiten números negativos");
                }
            }
            return Arrays.stream(valores)
                    .map(valor -> valor <= 1000 ? valor : 0)
                    .reduce(Integer::sum).getAsInt();
        }
        return 0;
    }
}
