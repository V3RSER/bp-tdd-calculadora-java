package co.com.sofka.calculadora;

import java.util.Arrays;

public class CalculadoraCadena {
    public int suma(String cadena) {
        if (cadena.length() > 0) {
            int[] valores = toArrayInt(cadena.split("[,|\n]"));
            return Arrays.stream(valores).reduce(Integer::sum).getAsInt();
        }
        return 0;
    }

    private static int[] toArrayInt(String[] arrayString) {
        int[] arrayInt = new int[arrayString.length];
        for (int i = 0; i < arrayString.length; i++) {
            arrayInt[i] = Integer.parseInt(arrayString[i]);
        }
        return arrayInt;
    }
}
