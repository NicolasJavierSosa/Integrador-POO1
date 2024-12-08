# Trabajo Integrador 
### Consigna

Se debe implementar un sistema de gestión de biblioteca con el objetivo de manejar las funciones
principales de una biblioteca.
Se deben considerar los siguientes requisitos. El sistema debe validar el ingreso por id y clave de los miembros de la biblioteca. Los miembros pueden ser usuarios y bibliotecarios. Cualquier miembro de la biblioteca debería poder buscar libros por título, autor o categoría temática. Cada libro tendrá un número de identificación único y otros detalles como editorial, autores, categoría temática, ISBN e idioma. Podría haber más de una copia de un libro y los miembros de la biblioteca deberían poder sacar cualquier copia. Cada copia está asociada a un rack que determina dónde se encuentra ubicada una copia dentro de la biblioteca. De un rack se mantiene su id y una descripción. El sistema debería poder recuperar información de quién tomó un libro en particular o cuáles son los libros que sacó un miembro específico de la biblioteca. Debería haber un límite máximo de 5 sobre la cantidad de copias de libros que un miembro puede sacar prestado. Debería haber un límite máximo de 10 días en que un miembro puede llevar una copia de un libro. El sistema debería poder generar multas por los libros devueltos después de la fecha de vencimiento. La multa surge de multiplicar la cantidad de días por un precio estimado del libro. La gestión de multas es parte de otro sistema. 

Otras consideraciones, cada copia de libro puede ser de un determinado tipo, estas pueden ser: de tapa dura, libro en rústica, audiolibro o libro electrónico Una copia de un libro puede estar disponible, prestada o pérdida. Existen copias de libros de referencia que no se pueden prestar. El estado de un miembro puede ser de alta o de baja. Los miembros activos solamente pueden sacar libros, siempre que no tengan libros cuyo vencimiento de entrega haya sido superado.

Cualquier duda debe consultar con los integrantes de la cátedra de la asignatura.

Deben:
- Realizar un diagrama de clases del modelo.
- Codificar la solución utilizando el lenguaje de programación Java.
- Implementar los ABMs correspondientes y otras pantallas que consideren necesarias.
- Persistir los datos en una base de datos PostgreSQL, MariaDB, MySQL o SQLite utilizando un ORM.
- Realizar un acercamiento al modelo vista controlador.