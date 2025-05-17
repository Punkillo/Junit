package src;

/**
 * Clase que representa un producto sobre el que se puede aplicar un descuento.
 */
public class Producto {
    /**
     * Categoría del producto (ej: "electronica", "ropa", etc.).
     */
    public String categoria;

    /**
     * Precio unitario del producto.
     */
    public double precio;

    /**
     * Cantidad de unidades del producto.
     */
    public int cantidad;

    /**
     * Constructor de Producto.
     *
     * @param categoria Categoría del producto. Debe ser una de las categorías aceptadas por el sistema.
     * @param precio Precio unitario. Debe ser mayor o igual a cero.
     * @param cantidad Número de unidades. Debe ser mayor o igual a cero.
     * @throws IllegalArgumentException Si el precio o la cantidad son negativos.
     */
    public Producto(String categoria, double precio, int cantidad) {
        if (precio < 0 || cantidad < 0) {
            throw new IllegalArgumentException("Precio o cantidad no pueden ser negativos");
        }
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
    }
}
