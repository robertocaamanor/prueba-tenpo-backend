# Prueba Tenpo

## Descripción

Esta es una aplicación Spring Boot que utiliza una base de datos PostgreSQL.

## Requisitos

- Java 17
- Maven
- Docker (para despliegue con Docker)

## Despliegue Local

### Configuración de la Base de Datos

1. Asegúrate de tener PostgreSQL instalado y en ejecución.
2. Crea una base de datos llamada `transacciones_db`.
3. Configura las credenciales de la base de datos en el archivo `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/transacciones_db
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    ```

### Construcción y Ejecución de la Aplicación

1. Clona el repositorio:

    ```sh
    git clone https://github.com/robertocaamanor/pruebatenpo.git
    cd pruebatenpo
    ```

2. Construye el proyecto con Maven:

    ```sh
    mvn clean install
    ```

3. Ejecuta la aplicación:

    ```sh
    mvn spring-boot:run
    ```

La aplicación estará disponible en `http://localhost:8080`.

## Despliegue con Docker

### Configuración de la Base de Datos

Dentro del `docker-compose.yml` se crea tanto el microservicio en Spring Boot como el servidor de Postgres, todo en un mismo container.

### Construcción y Ejecución de la Aplicación con Docker

1. Construye la imagen de Docker:

    ```sh
    docker build -t pruebatenpo .
    ```

2. Levanta los servicios definidos en `docker-compose.yml`:

    ```sh
    docker-compose up --build
    ```

La aplicación estará disponible en `http://localhost:8080`.

## Endpoints

- `GET /transaction/`: Obtiene todas las transacciones.
- `GET /transaction/{id}`: Obtiene una transacción por ID.
- `POST /transaction/`: Guarda una nueva transacción.
- `PUT /transaction/{id}`: Actualiza una transacción por ID.
- `DELETE /transaction/{id}`: Elimina una transacción por ID.

## Interacción POST Y PUT

Para interactuar con los endpoints POST /transaction/ y PUT /transaction/{id}, puedes utilizar los siguientes ejemplos de solicitudes HTTP en formato JSON basados en los datos de CrearTransaccionDTO:

### POST /transaction/

```json
{
   "montoTransaccion": 100,
   "giroComercio": "Retail",
   "nombreTenpista": "John Doe",
   "fechaTransaccion": "2023-10-01T12:00:00"
}
```

### PUT /transaction/{id}

```json
{
   "montoTransaccion": 200,
   "giroComercio": "Retail",
   "nombreTenpista": "John Doe",
   "fechaTransaccion": "2023-10-01T12:00:00"
}
```

## Notas

- Asegúrate de reemplazar las credenciales de la base de datos en `application.properties` y `docker-compose.yml` según sea necesario. Para levantar en local, debes configurar la conexión a la Base de Datos apuntando a `jdbc:postgresql://localhost:5432/transacciones_db`, y en Docker apuntando a `jdbc:postgresql://db:5432/transacciones_db`
- Para detener los contenedores Docker, ejecuta:

    ```sh
    docker-compose down
    ```