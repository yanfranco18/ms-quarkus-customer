# ✨ Microservicio de Clientes

Este es un microservicio RESTful para la gestión de clientes, construido con **Quarkus** y **MongoDB**. El proyecto permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre los datos de clientes, tanto personales como empresariales, utilizando una arquitectura de diseño simple y eficiente.

---

## 🚀 Características Principales

- **API RESTful:** Exposición de endpoints para la gestión de clientes.
- **Operaciones CRUD:** Funcionalidades completas para administrar la información de los clientes.
- **Persistencia de Datos:** Utiliza MongoDB 🍃 como base de datos.
- **Gestión de Configuración:** Las credenciales de la base de datos se manejan de forma segura a través de variables de entorno.

---

## 🛠️ Tecnologías Utilizadas

- **Framework:** Quarkus
- **Lenguaje:** Java 21+
- **Base de Datos:** MongoDB
- **Contenedor:** Docker
- **Build Tool:** Maven

---

## 📋 Requisitos Previos

Asegúrate de tener instalado lo siguiente:

- Java Development Kit (JDK) 21 o superior
- Maven 3.8.2 o superior
- Docker

---

## 🏁 Empezando

Sigue estos pasos para levantar y ejecutar el microservicio en tu entorno local.

### 1. Iniciar la Base de Datos con Docker

Abre una terminal y ejecuta el siguiente comando para iniciar un contenedor de MongoDB.

```bash
docker run --name mongo_customer -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin -e MONGO_INITDB_DATABASE=db_customer -d mongo:latest
```

---

### 2. Configurar Variables de Entorno

Este proyecto usa variables de entorno para la conexión a la base de datos. Debes configurar la variable `MONGO_CONNECTION_STRING` en tu entorno (terminal o IDE).

**En la terminal:**

```bash
# Para Linux o macOS
export MONGO_CONNECTION_STRING="mongodb://admin:admin@localhost:27017/db_customer?authSource=admin"
# Para Windows
set MONGO_CONNECTION_STRING=mongodb://admin:admin@localhost:27017/db_customer?authSource=admin
```

**En IntelliJ IDEA:**  
Añade la variable `MONGO_CONNECTION_STRING` en la configuración de ejecución (Edit Configurations).

---

### 3. Levantar la Aplicación

Desde la raíz del proyecto, ejecuta el siguiente comando en tu terminal para iniciar la aplicación en modo de desarrollo.

```bash
mvn quarkus:dev
```

La aplicación estará disponible en [http://localhost:8080](http://localhost:8080).

---

### 4. Probar los Endpoints de la API

Una vez que el servicio esté corriendo, puedes usar **Postman** o la interfaz de **Swagger UI** para probar los endpoints:

[http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)

---

## 🗺️ Endpoints de la API

| Método | Endpoint              | Descripción                                   |
|--------|----------------------|-----------------------------------------------|
| GET    | /customers           | Obtiene la lista de todos los clientes.       |
| GET    | /customers/{id}      | Obtiene un cliente por su ID.                 |
| POST   | /customers           | Crea un nuevo cliente.                        |
| PUT    | /customers/{id}      | Actualiza la información de un cliente existente. |
| DELETE | /customers/{id}      | Elimina un cliente por su ID.                 |

---