package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO#5
 * Crea el repositorio para la entidad Token de modo que,
 * además de las operaciones CRUD, se pueda consultar el Token asociado
 * a un AppUser dado
 */
// NO haria falta ya que Spring ya se encarga de esto con CrudRepository@Repository
public interface TokenRepository extends CrudRepository<Token, String> {
    //@Quey(value = "SELECT FROM TOKEN WHERE TOKEN.appUser = :appUser", nativeQuery = true)
    public Token findByAppUser(AppUser appUser); //se genera automáticamente la query con esto
}