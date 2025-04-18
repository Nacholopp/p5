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
| POST   | /api/users/me/session    | Inicia sesión a un usuario y crea y devuelve la cookie de sesion                 | 200 OK , 401 Unauthorized                 |
| POST   | /api/logout   | Cierra sesion y elimina la cookie (es post porque cambia el estado de la sesion) | 200 OK  , 401 Unauthorized                |
| GET    | /api/user     | Obtiene los datos del usuario autenticado                                        | 200 OK  , 401 Unauthorized                |
| PUT    | /api/user     | Modifica los datos de un usuario                                                 | 200 OK, 400 Bad Request ,401 Unauthorized |
| DELETE | /api/user     | Borra sus datos                                                                  | 204 No Content , 401 Unauthorized         |


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
