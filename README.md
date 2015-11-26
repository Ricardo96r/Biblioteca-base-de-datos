# Base de datos de una biblioteca usando Hashing y indexing
Proyecto de organizacion del computador

## Preview
![alt tag](https://github.com/Ricardo96r/Biblioteca-base-de-datos/blob/master/imagenes/imagen.png)

## Especificaciones del proyecto
  Se debe crear una pequeña base de datos (pequeña matriz)  para una pequeña biblioteca, que contenga inicialmente 20 registros  que estarán compuestos  por:  nombre (un solo nombre), ISBN, fecha de compra y estado (presente o prestado; se deben ingresar los registros a la base de datos por medio de un hashing usando el ISBN de 10 caracteres, y guardarlos en un archivo, adicionalmente se creará un índice por nombre que también será guardado en un archivo y deberá ser leído cada vez que se desee hacer una consulta, la aplicación debe poder generar unas búsqueda por nombre, por ISBN, y debe poder eliminarse y agregarse libros. El espacio primario debe ser de 11 grupos, y cada uno contiene un libro. La dimensiòn de filas de la matriz es de 100.
  En las búsquedas por ISBN se debe usar el hashing y en las búsquedas por nombre se debe usar búsqueda binaria sobre el indice que a su vez reporta la clave primaria (Nº de ISBN). Recuerden que no se pueden hacer búsquedas secuenciales
