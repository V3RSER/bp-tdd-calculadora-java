package co.com.sofka.calculadora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraCadenaTest {
    CalculadoraCadena calculadora = new CalculadoraCadena();

    @DisplayName("Una cadena vacía devuelve cero")
    @Test
    void testCadenaVacia() {
        assertEquals(0, calculadora.suma(""));
    }

    @DisplayName("Un solo número devuelve el valor")
    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource({
            "0,     0",
            "1,     1",
            "2,     2",
    })
    void testUnNumero(String cadena, int resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.suma(cadena),
                () -> cadena + " debería devolver " + resultadoEsperado);
    }

    @DisplayName("Dos números, delimitados por comas, devuelven la suma")
    @ParameterizedTest(name = "[{0}] = {1}")
    @CsvSource({
            "'0,1',     1",
            "'1,1',     2",
            "'1,2',     3",
    })
    void testDosNumerosSeparadosComas(String cadena, int resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.suma(cadena),
                () -> "[" + cadena + "]" + " debería devolver " + resultadoEsperado);
    }

    @DisplayName("Dos números, delimitados por saltos de línea, devuelve la suma")
    @ParameterizedTest(name = "[{0}] = {1}")
    @CsvSource({
            "'0\n1',     1",
            "'1\n1',     2",
            "'1\n2',     3",
    })
    void testDosNumerosSeparadosSaltosLinea(String cadena, int resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.suma(cadena),
                () -> "[" + cadena + "]" + " debería devolver " + resultadoEsperado);
    }

    @DisplayName("Tres números, delimitados de cualquier manera, devuelven la suma")
    @ParameterizedTest(name = "[{0}] = {1}")
    @CsvSource({
            "'0\n1,2',      3",
            "'1,2\n3',      6",
            "'2\n3\n4',     9",
            "'3,4,5',       12",
    })
    void testTresNumerosSeparadosCualesquiera(String cadena, int resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.suma(cadena),
                () -> "[" + cadena + "]" + " debería devolver " + resultadoEsperado);
    }

    @DisplayName("Los números negativos arrojan una excepción")
    @ParameterizedTest(name = "[{0}] = {1}")
    @CsvSource({
            "'-1',          'No se permiten números negativos'",
            "'-2,3',        'No se permiten números negativos'",
            "'-3,4\n-5',    'No se permiten números negativos'",
            "'-4\n5\n-6',   'No se permiten números negativos'",
            "'-6,-4,-5',    'No se permiten números negativos'",
    })
    void testNumeroNegativo(String cadena, String resultadoEsperado) {
        try {
            calculadora.suma(cadena);
        } catch (IllegalArgumentException resultado) {
            assertEquals(resultadoEsperado, resultado.getMessage());
        }
    }

    @DisplayName("Los números superiores a 1000 se ignoran")
    @ParameterizedTest(name = "[{0}] = {1}")
    @CsvSource({
            "'2000',            0",
            "'2000,2000',       0",
            "'2000,1',          1",
            "'2000,1\n2',       3",
            "'3\n2000\n4',      7",
            "'5,2000,2000',     5",
            "'1000,2000,6',     1006",
    })
    void testNumeroSuperior1000(String cadena, int resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.suma(cadena),
                () -> "[" + cadena + "]" + " debería devolver " + resultadoEsperado);
    }

    @DisplayName("Se puede definir un solo delimitador de caracteres en la primera línea")
    @ParameterizedTest(name = "[{0}] = {1}")
    @CsvSource({
            "'#3000',           0",
            "'|3000|3000',      0",
            "'@3000@1',         1",
            "'\n3000\n1\n2',    3",
            "'k3k3000k4',       7",
            "'$3000$3000',      0",
            "' 1000 3000 6',    1006",
    })
    void testDefinirDelimitadorUnCaracter(String cadena, int resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.suma(cadena),
                () -> "[" + cadena + "]" + " debería devolver " + resultadoEsperado);
    }

    @DisplayName("Se puede definir un delimitador de varios caracteres en la primera línea")
    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource({
            "'[###]3000',                       0",
            "'[||]3000||3000',                  0",
            "'[@@@@]3000@@@@1',                 1",
            "'[\n\n\n]3000\n\n\n1\n\n\n2',      3",
            "'[kkk]3kkk3000kkk4',               7",
            "'[$]3000$3000$5',                  5",
            "'[     ]1000     3000     6',      1006",
    })
    void testDefinirDelimitadorVariosCaracteres(String cadena, int resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.suma(cadena),
                () -> "" + cadena + "" + " debería devolver " + resultadoEsperado);
    }
}
