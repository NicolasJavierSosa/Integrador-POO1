# Especificaciones
---
## Diagrama de clases
![IntegradorPOO1](https://github.com/user-attachments/assets/fdaadb0d-1e4e-40c2-887d-e4966723e64a)

En este diagrama de clases se modelan las entidades del sistema de gestión de biblioteca. Estas incluyen:

- **Miembro**: Representa a los miembros de la biblioteca, ya sean usuarios o bibliotecarios.
- **Usuario**:*(hereda de Miembro)* Representa a los usuarios de la biblioteca.
- **Bibliotecario**:*(hereda de Miembro)* Representa a los bibliotecarios, encargados de gestionar los libros.
- **Libro**: Detalla la información de los libros registrados en el sistema.
- **Copia**: Representa cada copia física o digital de un libro.
- **Rack**: Ubicación física de las copias de los libros en la biblioteca.
- **Préstamo**: Gestiona la información sobre los préstamos realizados por un miembro.
- **DetallePrestamo**: Representa los detalles de un préstamo, incluyendo las copias.
- **Multa**: Representa las multas generadas por retrasos en la devolución de libros.
- **Autor**: Representa a los autores de los libros.
- **Categoría**: Representa la clasificación temática de los libros.
- **Tipo**: Enumeración que representa los tipos de copias: tapa dura, libro en rústica, audiolibro, libro electrónico.
