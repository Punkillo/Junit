package src;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CalculadoraDescuentosUtilsTest {

    @Test
    void testSinDescuentoNiVip() {
        List<Producto> productos = Arrays.asList(
            new Producto("hogar", 100.0, 1)  // sin descuento base
        );
        double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(productos, false);
        assertEquals(100.0, total);
    }

    @Test
    void testDescuentoPorCategoria() {
        List<Producto> productos = Arrays.asList(
            new Producto("ropa", 50.0, 3)  // 15%
        );
        double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(productos, false);
        assertEquals(127.5, total); // 150 * 0.85
    }

    @Test
    void testDescuentoConVip() {
        List<Producto> productos = Arrays.asList(
            new Producto("electronica", 100.0, 5)  // 20% + 5%
        );
        double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(productos, true);
        assertEquals(375.0, total); // 500 * 0.75
    }

    @Test
    void testDescuentoMaximo30() {
        List<Producto> productos = Arrays.asList(
            new Producto("ropa", 100.0, 10)  // 15% + 5% = 20% < 30
        );
        double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(productos, true);
        assertEquals(800.0, total); // 1000 * 0.80
    }

    @Test
    void testCategoriaInvalidaLanzaExcepcion() {
        List<Producto> productos = Arrays.asList(
            new Producto("juguetes", 100.0, 2)
        );

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            CalculadoraDescuentosUtils.calcularTotalConDescuento(productos, false);
        });

        assertEquals("Categoría no válida", ex.getMessage());
    }
}
