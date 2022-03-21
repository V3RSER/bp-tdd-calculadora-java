package co.com.sofka.calculadora;

import java.util.Arrays;

public class CalculadoraCadena {
    private final char SEPARADOR_DEFECTO = ',';

    public int suma(String cadena) {
        if (cadena.length() > 0) {
            String[] valoresCadena;
            if (Character.isDigit(cadena.charAt(0)) || cadena.charAt(0) == '-') {
                valoresCadena = cadena.split("[" + SEPARADOR_DEFECTO + "|\n]");
            } else {
                valoresCadena = validarCaracteres(cadena).substring(1).split(cadena.substring(0, 1));
            }

            int[] valores = new int[valoresCadena.length];
            for (int i = 0; i < valoresCadena.length; i++) {
                valores[i] = Integer.parseInt(valoresCadena[i]);
                if (valores[i] < 0) {
                    throw new IllegalArgumentException("No se permiten números negativos");
                }
            }

            return Arrays.stream(valores)
                    .map(valor -> valor <= 1000 ? valor : 0)
                    .reduce(Integer::sum)
                    .getAsInt();
        }
        return 0;
    }

    public String validarCaracteres(String cadena) {
        return switch (cadena.charAt(0)) {
            case '^' -> cadena.replace('^', SEPARADOR_DEFECTO);
            case '$' -> cadena.replace('$', SEPARADOR_DEFECTO);
            case '.' -> cadena.replace('.', SEPARADOR_DEFECTO);
            case '|' -> cadena.replace('|', SEPARADOR_DEFECTO);
            case '?' -> cadena.replace('?', SEPARADOR_DEFECTO);
            case '*' -> cadena.replace('*', SEPARADOR_DEFECTO);
            case '+' -> cadena.replace('+', SEPARADOR_DEFECTO);
            case '(' -> cadena.replace('(', SEPARADOR_DEFECTO);
            case ')' -> cadena.replace(')', SEPARADOR_DEFECTO);
            case '{' -> cadena.replace('{', SEPARADOR_DEFECTO);
            default -> cadena;
        };
    }
}