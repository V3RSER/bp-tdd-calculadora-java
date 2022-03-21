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
    void testDosNumerosComas(String cadena, int resultadoEsperado) {
        assertEquals(resultadoEsperado, calculadora.suma(cadena),
                () -> cadena + " debería devolver " + resultadoEsperado);
    }
}
