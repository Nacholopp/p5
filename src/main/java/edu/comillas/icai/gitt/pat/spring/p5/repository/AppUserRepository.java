package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO#4
 * Crea el repositorio para la entidad AppUser de modo que,
 * además de las operaciones CRUD, se pueda consultar el AppUser asociado
 * a un email dado
 */

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    //Las operaciones CRUD se puedes hacer de por sí al extender CrudRepository
    //@Query(value = "SELECT * FROM APP_USER WHERE APP_USER.email = :email", nativeQuery = true) sería asi la query
    public AppUser findByEmail(String email);
     //solo devuelve uno porque el email es único
}