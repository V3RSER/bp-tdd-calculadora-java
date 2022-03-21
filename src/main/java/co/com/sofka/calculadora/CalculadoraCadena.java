package co.com.sofka.calculadora;

import java.util.Arrays;

public class CalculadoraCadena {
    public int suma(String cadena) {
        if (cadena.length() == 1) {
            return Integer.parseInt(cadena);
        }
        if (cadena.split(",").length >= 2) {
            return Arrays.stream(toArrayInt(cadena.split(",")))
                    .reduce(Integer::sum).getAsInt();
        }
        if (cadena.split("\n").length >= 2) {
            return Arrays.stream(toArrayInt(cadena.split("\n")))
                    .reduce(Integer::sum).getAsInt();
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
