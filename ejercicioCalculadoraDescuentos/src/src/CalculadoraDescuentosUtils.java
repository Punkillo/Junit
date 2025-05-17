package src;

import java.util.List;

/**
 * Clase utilitaria para calcular el total con descuentos aplicados a una lista de productos.
 */
public class CalculadoraDescuentosUtils {

    /**
     * Calcula el precio total aplicando descuentos según la categoría, cantidad y si el cliente es VIP.
     *
     * @param productos Lista de productos a procesar.
     * @param esClienteVip Indica si el cliente tiene el beneficio de cliente VIP (descuento adicional del 5%).
     * @return Total a pagar con los descuentos aplicados, redondeado a dos decimales.
     * @throws IllegalArgumentException Si alguna categoría no es válida.
     */
    public static double calcularTotalConDescuento(List<Producto> productos, boolean esClienteVip) {
        double total = 0.0;

        for (Producto p : productos) {
            double descuentoBase = calcularDescuentoBase(p.categoria, p.cantidad);
            double descuentoFinal = esClienteVip ? descuentoBase + 0.05 : descuentoBase;

            if (descuentoFinal > 0.30) {
                descuentoFinal = 0.30; // límite máximo del 30%
            }

            double subtotal = p.precio * p.cantidad;
            total += subtotal * (1 - descuentoFinal);
        }

        return redondear(total);
    }

    /**
     * Determina el descuento base según la categoría del producto y la cantidad comprada.
     *
     * @param categoria Categoría del producto (no distingue mayúsculas).
     * @param cantidad Cantidad de unidades del producto.
     * @return Porcentaje de descuento base como valor entre 0 y 1.
     * @throws IllegalArgumentException Si la categoría no está contemplada.
     */
    private static double calcularDescuentoBase(String categoria, int cantidad) {
        switch (categoria.toLowerCase()) {
            case "electronica":
                return cantidad >= 5 ? 0.20 : 0.10;
            case "ropa":
                return cantidad >= 3 ? 0.15 : 0.05;
            case "hogar":
                return cantidad >= 2 ? 0.10 : 0.0;
            case "alimentacion":
                return 0.05;
            default:
                throw new IllegalArgumentException("Categoría no válida");
        }
    }

    /**
     * Redondea un valor decimal a dos cifras decimales.
     *
     * @param valor Valor a redondear.
     * @return Valor redondeado.
     */
    private static double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
