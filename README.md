# ecommerce-store

API de productos de una tienda en línea. La API proporciona una lista de productos con información como el nombre, precio, descuento y categoría de cada producto.

## Funcionalidades

- Búsqueda de productos por nombre, precio, descuento y categoría.
- Ordenación de los resultados de búsqueda por cualquier columna de la tabla de productos.
- Paginación de los resultados de búsqueda.
- Despliegue de la aplicación en un contenedor Docker.

## Estructura del proyecto

- `src/main/java/cl/store/ecomerce`: Contiene el código fuente de la aplicación.
- `src/main/java/cl/store/ecomerce/controllers`: Contiene los controladores de la aplicación que son las llamadas a la API por URL.
- `src/main/java/cl/store/ecomerce/model`: Contiene el modelo de la aplicación que permite mapear la base de datos e identificar las identidades.
- `src/main/java/cl/store/ecomerce/config`: Contiene la configuración de la aplicación y clases utils.
- `src/main/java/cl/store/ecomerce/repositories`: Contiene los repositorios de la aplicación que permiten realizar operaciones de lectura y escritura en la base de datos.
- `src/main/java/cl/store/ecomerce/services`: Contiene los servicios de la aplicación que permiten realizar operaciones de negocio.
- `src/main/resources`: Contiene los archivos de configuración de la aplicación.
- `Dockerfile`: Especifica que la aplicación se debe ejecutar en una VM de Java.
- `docker-compose.yml`: Configura el puerto en el que se debe ejecutar el servidor.

## Despliegue

Para desplegar la aplicación, se debe construir y ejecutar el contenedor Docker con los siguientes comandos:

```bash
docker-compose build
docker-compose up