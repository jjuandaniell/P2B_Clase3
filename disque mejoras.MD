#Mejora #1:
Ubicación: InventarioService.java
Descripción del cambio: Se incorporó una validación previa al registro de productos para impedir la adición de productos con nombres repetidos, sin distinguir entre mayúsculas y minúsculas.
Justificación: Esto asegura la integridad del inventario y evita confusiones al vender productos con nombres similares pero diferentes identificadores.

#Mejora #2:
Ubicación: Pedido.java y DatabaseManager.java
Descripción del cambio: Se añadió el atributo fecha de tipo LocalDateTime en la clase Pedido y se actualizó la tabla pedidos en la base de datos para incluir el campo fecha_hora.
Justificación: Permite registrar el momento exacto en que se realiza cada pedido, facilitando la trazabilidad y la generación de reportes por fecha.

#Mejora #3:
Ubicación: Producto.java
Descripción del cambio: Se fortalecieron las validaciones en los métodos reducirStock y setStock para evitar que el stock pueda ser negativo.
Justificación: Esto previene errores lógicos y mantiene la consistencia del inventario, evitando valores inválidos.

#Mejora #4:
Ubicación: Producto.java e InventarioService.java
Descripción del cambio: Se implementó la validación para que el precio siempre sea mayor que cero tanto en el constructor como en el setter, y se verificó que la cantidad vendida sea positiva.
Justificación: Protege el sistema de datos incorrectos que podrían generar errores en los cálculos o permitir manipulaciones indebidas.

#Mejora #5:
Ubicación: Main.java
Descripción del cambio: Se añadió una opción en el menú principal para mostrar un resumen del inventario, incluyendo nombre, precio y stock de cada producto.
Justificación: Facilita al usuario la consulta rápida del estado del inventario desde el menú.

#Mejora #6:
Ubicación: Main.java e InventarioService.java
Descripción del cambio: Se agregó una opción en el menú para eliminar productos por ID, solicitando confirmación antes de proceder con la eliminación.
Justificación: Previene eliminaciones accidentales y brinda mayor seguridad y control al usuario sobre el inventario.
