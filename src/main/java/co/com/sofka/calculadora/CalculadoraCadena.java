package co.com.sofka.calculadora;

import java.util.Arrays;

public class CalculadoraCadena {
    private final char SEPARADOR_DEFECTO = ',';

    public int suma(String cadena) {
        if (cadena.length() > 0) {
            String[] cadenaSeparada;
            if (Character.isDigit(cadena.charAt(0)) || cadena.charAt(0) == '-') {
                cadenaSeparada = cadena.split("[" + SEPARADOR_DEFECTO + "|\n]");
            } else {
                String cadenaValidada = validarCaracteres(cadena);
                if (cadena.charAt(0) == '[') {
                    cadenaSeparada = cadenaValidada.substring(cadenaValidada.indexOf(']') + 1)
                            .split(cadenaValidada.substring(1, cadenaValidada.indexOf(']')));
                } else {
                    cadenaSeparada = cadenaValidada.substring(1).split(cadenaValidada.substring(0, 1));
                }
            }

            int[] valores = new int[cadenaSeparada.length];
            for (int i = 0; i < cadenaSeparada.length; i++) {
                valores[i] = Integer.parseInt(cadenaSeparada[i]);
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
        return switch (cadena.charAt(0) == '[' ? cadena.charAt(1) : cadena.charAt(0)) {
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