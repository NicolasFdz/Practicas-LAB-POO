Estrategias de sincronización utilizadas:

1. synchronized en métodos de CuentaBancaria:
   - Evita condiciones de carrera al acceder/modificar el saldo.
   - Garantiza exclusión mutua entre hilos que depositan o retiran.

2. wait() y notifyAll() en BufferCompartido9698:
   - Controlan acceso al buffer compartido entre productores y consumidores.
   - Productores esperan si el buffer está lleno; consumidores si está vacío.

3. join() en el método main:
   - Asegura que los hilos terminen antes de continuar con el flujo principal.

Estas técnicas me ayudaron coordinar múltiples hilos sin interferencias ni inconsistencias.
