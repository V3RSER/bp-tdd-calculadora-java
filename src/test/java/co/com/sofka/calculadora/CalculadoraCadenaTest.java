package co.com.sofka.calculadora;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;

public class CalculadoraCadenaTest {
    @Test
    @DisplayName("Una cadena vacía devuelve cero")
    public void testCadenaVacia() {
        CalculadoraCadena calculadora = new CalculadoraCadena();
        assertEquals(0, calculadora.suma(""));
    }
}
