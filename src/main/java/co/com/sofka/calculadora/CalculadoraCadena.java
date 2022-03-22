package co.com.sofka.calculadora;

import java.util.Arrays;
import java.util.stream.Stream;

public class CalculadoraCadena {
    private static final char SEPARADOR_DEFECTO = ',';

    public int suma(String cadena) {
        String[] cadenaSeparada;
        try {
            if (Character.isDigit(cadena.charAt(0)) || cadena.charAt(0) == '-') {
                cadenaSeparada = cadena
                        .split("[" + SEPARADOR_DEFECTO + "|\n]");
            } else {
                String cadenaValidada = validarCaracteres(cadena);
                if (cadena.charAt(0) == '[') {
                    cadenaSeparada = cadenaValidada.substring(cadenaValidada.indexOf(']') + 1)
                            .split(cadenaValidada.substring(1, cadenaValidada.indexOf(']')));
                } else {
                    cadenaSeparada = cadenaValidada.substring(1)
                            .split(cadenaValidada.substring(0, 1));
                }
            }
        } catch (Exception e) {
            cadenaSeparada = new String[]{"0"};
        }

        return Arrays.stream(
                        Stream.of(cadenaSeparada)
                                .mapToInt(Integer::parseInt)
                                .boxed()
                                .toArray(Integer[]::new))
                .map(valor -> {
                    if (valor < 0) {
                        throw new IllegalArgumentException("No se permiten números negativos");
                    }
                    return valor <= 1000 ? valor : 0;
                })
                .reduce(Integer::sum).get();
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