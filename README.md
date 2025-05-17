# Actividad de JUnit

Daniel Sáez Sáez  
1ºDAM

---

## Índice

1. [Descripción](#descripción)  
2. [ProductoTest](#productotest)  
3. [CalculadoraDescuentosUtilsTest](#calculadoradescuentosutilstest)

---

## Descripción

La actividad cuenta con dos clases: `CalculadoraDescuentosUtils` y `Producto`. Estas son clases auxiliares, lo que significa que para el correcto funcionamiento del proyecto una necesita a la otra, ya que hacen llamadas entre sí.

Para ver la **cobertura del código** (qué métodos, variables, etc. están siendo utilizados en los tests), es necesario crear **dos clases adicionales de prueba**, en las que se llame a los métodos de las clases originales y se utilice la anotación `@Test` para que JUnit las detecte.

Para que JUnit funcione correctamente en los proyectos, se debe importar su biblioteca, en concreto **JUnit 5**.

---

## ProductoTest

###  `testCreacionValida`

```java
void testCreacionValida() {
    Producto p = new Producto("ropa", 50.0, 2);
    assertEquals("ropa", p.categoria);
    assertEquals(50.0, p.precio);
    assertEquals(2, p.cantidad);
}
```

Crea un producto con valores correctos. Cada `assertEquals` compara el valor real con el esperado. Si coinciden, la prueba pasa.

---

###  `testPrecioNegativoLanzaExcepcion`

```java
void testPrecioNegativoLanzaExcepcion() {
    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
        new Producto("electronica", -10.0, 2);
    });
    assertEquals("Precio o cantidad no pueden ser negativos", ex.getMessage());
}
```

Verifica que si se pasa un precio negativo, el constructor lanza una `IllegalArgumentException`.

---

###  `testCantidadNegativaLanzaExcepcion`

```java
void testCantidadNegativaLanzaExcepcion() {
    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
        new Producto("hogar", 10.0, -1);
    });
    assertEquals("Precio o cantidad no pueden ser negativos", ex.getMessage());
}
```

Prueba equivalente al anterior, pero usando una cantidad negativa.

---

###  `testPrecioYCantidadCeroEsValido`

```java
void testPrecioYCantidadCeroEsValido() {
    Producto p = new Producto("alimentacion", 0.0, 0);
    assertEquals(0.0, p.precio);
    assertEquals(0, p.cantidad);
}
```

Verifica que un producto con precio y cantidad en 0 se considere válido.

---

## CalculadoraDescuentosUtilsTest

###  `testSinDescuentoNiVip`

```java
void testSinDescuentoNiVip() {
    List<Producto> productos = Arrays.asList(
        new Producto("hogar", 100.0, 1) // sin descuento base
    );
    double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(productos, false);
    assertEquals(100.0, total);
}
```

Verifica que sin descuentos aplicables el total sea el precio por cantidad.

---

###  `testDescuentoPorCategoria`

```java
void testDescuentoPorCategoria() {
    List<Producto> productos = Arrays.asList(
        new Producto("ropa", 50.0, 3) // 15%
    );
    double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(productos, false);
    assertEquals(127.5, total); // 150 * 0.85
}
```

Aplica un 15% de descuento para ropa con cantidad ≥ 3.  
150 × 0.85 = 127.5

---

###  `testDescuentoConVip` 

```java
void testDescuentoConVip() {
    List<Producto> productos = Arrays.asList(
        new Producto("electronica", 100.0, 5) // 20% + 5%
    );
    double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(productos, true);
    assertEquals(750.0, total); // 500 * 0.75
}
```

**Nota**: el total real debería ser **375.0**, no 750.0, ya que:  
100 × 5 = 500  
500 × 0.75 = **375.0**

---

###  `testDescuentoMaximo30`

```java
void testDescuentoMaximo30() {
    List<Producto> productos = Arrays.asList(
        new Producto("ropa", 100.0, 10) // 15% + 5% = 20% < 30
    );
    double total = CalculadoraDescuentosUtils.calcularTotalConDescuento(productos, true);
    assertEquals(800.0, total); // 1000 * 0.80
}
```

Prueba que el descuento no supere el 30%.  
100 × 10 = 1000  
1000 × 0.80 = 800

---

###  `testCategoriaInvalidaLanzaExcepcion`

```java
void testCategoriaInvalidaLanzaExcepcion() {
    List<Producto> productos = Arrays.asList(
        new Producto("juguetes", 100.0, 2)
    );
    Exception ex = assertThrows(IllegalArgumentException.class, () -> {
        CalculadoraDescuentosUtils.calcularTotalConDescuento(productos, false);
    });
    assertEquals("Categoría no válida", ex.getMessage());
}
```

Verifica que se lanza una excepción al usar una categoría no contemplada.
