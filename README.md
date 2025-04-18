# P5
Aplicación web que usa Spring JPA para persistir los datos de un API REST de gestión de usuarios.
El API permite el registro de nuevos usuarios y su identificación mediante email y password.
Una vez identificados, se emplea una cookie de sesión para autenticar las peticiones que permiten 
a los usuarios leer, modificar y borrar sus datos. También existe un endpoint para cerrar la sesión.  

## Endpoints

// TODO#1: rellena la tabla siguiente analizando el código del proyecto

| Método | Ruta          | Descripción                                                                      | Respuestas                                |
|--------|---------------|----------------------------------------------------------------------------------|-------------------------------------------|
| POST   | /api/users    | Registrar un nuevo User                                                          | 201 created , 400 Bad Request             |
| POST   | /api/users/me/session    | Inicia sesión a un usuario y crea la cookie de sesion  y devuelve el status              | 200 OK , 401 Unauthorized                 |
| DELETE   | /api/users/me/session  | Cierra sesion y elimina la cookie (token) de sesión | 200 OK  , 401 Unauthorized                |
| GET    | /api/users/me     | Obtiene los datos del usuario autenticado                                        | 200 OK  , 401 Unauthorized                |
| PUT    | /api/users/me     | Modifica los datos de un usuario autenticado                                                 | 200 OK, 400 Bad Request ,401 Unauthorized |
| DELETE | /api/user/me    | Borra sus datos y en cascada la cookie de session                                                              | 204 No Content , 401 Unauthorized         |


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
