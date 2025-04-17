# P5
Aplicación web que usa Spring JPA para persistir los datos de un API REST de gestión de usuarios.
El API permite el registro de nuevos usuarios y su identificación mediante email y password.
Una vez identificados, se emplea una cookie de sesión para autenticar las peticiones que permiten 
a los usuarios leer, modificar y borrar sus datos. También existe un endpoint para cerrar la sesión.  

## Endpoints

// TODO#1: rellena la tabla siguiente analizando el código del proyecto

| Método | Ruta          | Descripción                                                      | Respuestas                    |
|--------|---------------|------------------------------------------------------------------|-------------------------------|
| POST   | /api/register | Registrar un nuevo User                                          | 201 created , 400 Bad Request |
| POST   | /api/login    | Inicia sesión a un usuario y crea y devuelve la cookie de sesion |                               |
| PUT    | /             |                                                                  |                               |
| DELETE |               |                                                                  |                               |
| POST   |               |                                                                  |                               |
|        |               |                                                                  |                               |


## Comandos 

- Construcción: 
  ```sh
  ./mvnw clean package
  ```

- Ejecución: 
  ```sh
  ./mvnw spring-boot:run
  ```

- Tests:
  ```sh
  ./mvnw test
  ```
