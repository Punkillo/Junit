package src;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProductoTest {

    @Test
    void testCreacionValida() {
        Producto p = new Producto("ropa", 50.0, 2);
        assertEquals("ropa", p.categoria);
        assertEquals(50.0, p.precio);
        assertEquals(2, p.cantidad);
    }

    @Test
    void testPrecioNegativoLanzaExcepcion() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Producto("electronica", -10.0, 2);
        });
        assertEquals("Precio o cantidad no pueden ser negativos", ex.getMessage());
    }

    @Test
    void testCantidadNegativaLanzaExcepcion() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            new Producto("hogar", 10.0, -1);
        });
        assertEquals("Precio o cantidad no pueden ser negativos", ex.getMessage());
    }

    @Test
    void testPrecioYCantidadCeroEsValido() {
        Producto p = new Producto("alimentacion", 0.0, 0);
        assertEquals(0.0, p.precio);
        assertEquals(0, p.cantidad);
    }
}
